package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> storageMap = new HashMap<>();

    @Override
    protected Resume getIndex(String uuid) {
        return storageMap.get(uuid);
    }

    @Override
    protected boolean isExist(Object resume) {
        return resume != null;
    }

    @Override
    protected void updateStorage(Resume resume, Object index) {
        storageMap.put(resume.getUuid(), resume);
    }

    @Override
    protected void saveStorage(Resume resume, Object index) {
        storageMap.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getStorage(Object index) {
        return (Resume) index;
    }

    @Override
    protected void deleteStorage(Object index) {
        storageMap.remove(((Resume) index).getUuid());
    }

    @Override
    public void clear() {
        storageMap.clear();
    }

    @Override
    public Resume[] getAll() {
        return storageMap.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storageMap.size();
    }
}
