package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private Path directory;

    public AbstractPathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }
    
    abstract void doWrite(Resume resume, OutputStream os) throws IOException;

    abstract Resume doRead(InputStream is) throws IOException, ClassNotFoundException;

    @Override
    protected List<Resume> getListResumes() {
        File[] files = directory.toFile().listFiles();
        if (files == null) {
            throw new StorageException("Directory is empty: ", directory.toString());
        } else {
            List<Resume> list = new ArrayList<>();
            for (File f : files) {
                list.add(getFromStorage(f.toPath()));
            }
            return list;
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.isRegularFile(path);
    }

    @Override
    protected void updateInStorage(Resume resume, Path path) {
        try {
            doWrite(resume, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (Exception e) {
            throw new StorageException("IO error", path.toString(), e);
        }
    }

    @Override
    protected void saveInStorage(Resume resume, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Error with creation of new file", path.toString(), e);
        }
        updateInStorage(resume, path);
    }

    @Override
    protected Resume getFromStorage(Path path) {
        try {
            return doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (ClassNotFoundException e) {
            throw new StorageException("Error read resume", null, e);
        } catch (IOException e) {
            throw new StorageException("Not found path", null, e);
        }
    }

    @Override
    protected void deleteFromStorage(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Error delete resume", null, e);        }
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::deleteFromStorage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int size() throws IOException {
            return (int)Files.list(directory).count();
    }
}
