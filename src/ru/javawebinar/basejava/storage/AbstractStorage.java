package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume resume) {
        Object searchKey = getSearchKey(resume.getUuid());
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            updateStorage(resume, searchKey);
        }
    }

    @Override
    public void save(Resume resume) {
        Object index = getSearchKey(resume.getUuid());
        if (isExist(index)) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            saveStorage(resume, index);
        }
    }

    @Override
    public Resume get(String uuid) {
        Object index = getSearchKey(uuid);
        if (!isExist(index)) {
            throw new NotExistStorageException(uuid);
        } else {
            return getStorage(index);
        }
    }

    @Override
    public void delete(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteStorage(searchKey);
        }
    }

    protected abstract Object getSearchKey(String searchKey);

    protected abstract boolean isExist(Object index);

    protected abstract void updateStorage(Resume resume, Object index);

    protected abstract void saveStorage(Resume resume, Object index);

    protected abstract Resume getStorage(Object index);

    protected abstract void deleteStorage(Object index);
}
