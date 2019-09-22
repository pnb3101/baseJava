package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class ListStorage extends AbstractStorage {

    private List<Resume> storageList = new ArrayList<>();

    @Override
    public void clear() {
        storageList.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        Collections.sort(storageList);
        return storageList;
    }

    @Override
    public int size() {
        return storageList.size();
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < storageList.size(); i++) {
            if (storageList.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    @Override
    protected void updateStorage(Resume resume, Object index) {
        storageList.set((Integer) index, resume);
    }

    @Override
    protected void saveStorage(Resume resume, Object index) {
        storageList.add(resume);
    }

    @Override
    protected Resume getStorage(Object index) {
        return storageList.get((Integer) index);
    }

    @Override
    protected void deleteStorage(Object index) {
        storageList.remove(((Integer) index).intValue());
    }
}

