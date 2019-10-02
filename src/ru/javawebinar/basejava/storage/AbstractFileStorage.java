package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

    public AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        this.directory = directory;
    }

    public abstract void doWrite(Resume resume, File file);

    public abstract Resume doRead(File file);

    @Override
    protected List<Resume> getListResumes() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Directory is empty: ", directory.getName());
        } else {
            List<Resume> list = new ArrayList<>();
            for (File f : files) {
                list.add(getFromStorage(f));
            }
            return list;
        }
    }

    @Override
    protected File getSearchKey(String uuid) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        return new File(directory, uuid);
    }

    @Override
    protected boolean isExist(File file) {
        if (!file.exists()) {
            throw new StorageException("File is not exist: ", file.getName());
        } else {
            return file.exists();
        }
    }

    @Override
    protected void updateInStorage(Resume resume, File file) {
        try {
            doWrite(resume, file);
        } catch (Exception e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected void saveInStorage(Resume resume, File file) {
        try {
            file.createNewFile();
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected Resume getFromStorage(File file) {
        return doRead(file);
    }

    @Override
    protected void deleteFromStorage(File file) {
        if (!file.delete()) {
            throw new StorageException("Can`t delete this file: ", file.getName());
        }
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File f : files) {
                deleteFromStorage(f);
            }
        }
    }

    @Override
    public int size() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("This directory has null size: ", directory.getName());
        } else {
            return files.length;
        }
    }
}
