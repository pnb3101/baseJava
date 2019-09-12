package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected List<Resume> storageList = new ArrayList<>(Arrays.asList(storage));

    @Override
    public void update(Resume resume) {
        if (!storageList.contains(resume)) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            storageList.add(storageList.indexOf(resume), resume);
        }
    }

    @Override
    public void clear() {
        storageList.clear();
    }

    @Override
    public void save(Resume resume) {
        if (storageList.contains(resume)) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            storageList.add(resume);
        }
    }

    @Override
    public Resume get(String uuid) {
        Resume resume = new Resume(uuid);
        if (!storageList.contains(resume)) {
            throw new NotExistStorageException(uuid);
        } else {
            return storageList.get(storageList.indexOf(resume));
        }
    }

    @Override
    public void delete(String uuid) {
        Resume resume = new Resume(uuid);
        if (!storageList.contains(resume)) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            storageList.remove(resume);
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
}
