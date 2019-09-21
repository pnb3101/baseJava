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
    protected Integer getSearchKey(String searchKey) {
        for (int i = 0; i < storageList.size(); i++) {
            if (storageList.get(i).getUuid().equals(searchKey)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (Integer) searchKey >= 0;
    }

    @Override
    protected void updateStorage(Resume resume, Object searchKey) {
        storageList.set((Integer) searchKey, resume);
    }

    @Override
    protected void saveStorage(Resume resume, Object searchKey) {
        storageList.add(resume);
    }

    @Override
    protected Resume getStorage(Object searchKey) {
        return storageList.get((Integer) searchKey);
    }

    @Override
    protected void deleteStorage(Object searchKey) {
        storageList.remove(((Integer) searchKey).intValue());
    }
}

