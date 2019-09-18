package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume resume) {
        Object index = getIndex(resume.getUuid());
        if (!isExist(index)) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            updateStorage(resume, index);
        }
    }

    @Override
    public void save(Resume resume) {
        Object index = getIndex(resume.getUuid());
        if (isExist(index)) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            saveStorage(resume, index);
        }
    }

    @Override
    public Resume get(String uuid) {
        Object index = getIndex(uuid);
        if (!isExist(index)) {
            throw new NotExistStorageException(uuid);
        } else {
            return getStorage(index);
        }
    }

    @Override
    public void delete(String uuid) {
        Object index = getIndex(uuid);
        if (!isExist(index)) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteStorage(index);
        }
    }

    protected abstract Object getIndex(String uuid);

    protected abstract boolean isExist(Object index);

    protected abstract void updateStorage(Resume resume, Object index);

    protected abstract void saveStorage(Resume resume, Object index);

    protected abstract Resume getStorage(Object index);

    protected abstract void deleteStorage(Object index);
}
