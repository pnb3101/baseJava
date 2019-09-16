package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            updateStorage(resume, index);
        }
    }

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > 0) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            saveStorage(resume, index);
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            return getStorage(index);
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteStorage(index);
        }
    }

    protected abstract int getIndex(String uuid);

    protected abstract void updateStorage(Resume resume, int index);

    protected abstract void saveStorage(Resume resume, int index);

    protected abstract Resume getStorage(int index);

    protected abstract void deleteStorage(int index);
}
