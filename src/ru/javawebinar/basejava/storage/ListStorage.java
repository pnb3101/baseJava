package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> storageList = new ArrayList<>();

    @Override
    public void clear() {
        storageList.clear();
    }

    @Override
    public Resume[] getAll() {
        return storageList.toArray(new Resume[storageList.size()]);
    }

    @Override
    public int size() {
        return storageList.size();
    }

    @Override
    protected int getIndex(String uuid) {
        for(int i = 0; i < storageList.size(); i++){
            if(storageList.get(i).getUuid().equals(uuid)){
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void updateStorage(Resume resume, int index) {
        storageList.set(index, resume);
    }

    @Override
    protected void saveStorage(Resume resume, int index) {
        storageList.add(resume);
    }

    @Override
    protected Resume getStorage(String uuid, int index) {
        return storageList.get(index);
    }

    @Override
    protected void deleteStorage(String uuid, int index) {
        storageList.remove(index);
    }
}

