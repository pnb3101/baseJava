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
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            storageList.add(index, resume);
        }
    }

    @Override
    public void clear() {
        storageList.clear();
    }

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > 0) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            storageList.add(resume);
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            return storageList.get(index);
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            storageList.remove(index);
        }
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
}

