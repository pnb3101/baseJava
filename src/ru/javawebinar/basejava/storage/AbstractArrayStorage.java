package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;


public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected void updateStorage(Resume resume, Object index) {
        storage[(Integer) index] = resume;
    }

    @Override
    protected void saveStorage(Resume resume, Object index) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage is full.", resume.getUuid());
        } else {
            insertResume(resume, (Integer) index);
            size++;
        }
    }

    @Override
    protected Resume getStorage(Object index) {
        return storage[(Integer) index];
    }

    @Override
    protected void deleteStorage(Object index) {
        fillDeletedResume((Integer) index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    protected abstract Object getIndex(String uuid);

    protected abstract void insertResume(Resume resume, int index);

    protected abstract void fillDeletedResume(int index);
}
