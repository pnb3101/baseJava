package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractStorage implements Storage {
    protected Resume[] storage = new Resume[]{};

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            updateStorage(resume);
        }
    }

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > 0) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            saveStorage(resume);
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            return getStorage(uuid);
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteStorage(uuid);
        }
    }

    @Override
    public void clear() {
    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public int size() {
        return 0;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void updateStorage(Resume resume);

    protected abstract void saveStorage(Resume resume);

    protected abstract Resume getStorage(String uuid);

    protected abstract void deleteStorage(String uuid);
}
