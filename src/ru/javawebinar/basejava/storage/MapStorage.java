package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class MapStorage extends AbstractStorage {
    @Override
    protected int getIndex(String uuid) {
        return 0;
    }

    @Override
    protected void updateStorage(Resume resume, int index) {

    }

    @Override
    protected void saveStorage(Resume resume, int index) {

    }

    @Override
    protected Resume getStorage(String uuid, int index) {
        return null;
    }

    @Override
    protected void deleteStorage(String uuid, int index) {

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
}
