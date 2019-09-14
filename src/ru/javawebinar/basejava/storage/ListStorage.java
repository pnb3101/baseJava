package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected List<Resume> storageList = new ArrayList<>(Arrays.asList(storage));

    @Override
    public void clear() {
        storageList.clear();
    }

    @Override
    public Resume[] getAll() {
        return (Resume[]) storageList.toArray();
    }

    @Override
    public int size() {
        return storageList.size();
    }

    @Override
    protected int getIndex(String uuid) {
        Iterator<Resume> iterator = storageList.iterator();
        while (iterator.hasNext()) {
            Resume r = iterator.next();
            if (r.getUuid().equals(uuid)) {
                return storageList.indexOf(r);
            }
        }
        return -1;
    }

    @Override
    protected void updateStorage(Resume resume) {
        storageList.set(getIndex(resume.getUuid()), resume);
    }

    @Override
    protected void saveStorage(Resume resume) {
        storageList.add(resume);
    }

    @Override
    protected Resume getStorage(String uuid) {
        return storageList.get(getIndex(uuid));
    }

    @Override
    protected void deleteStorage(String uuid) {
        storageList.remove(getIndex(uuid));
    }
}

