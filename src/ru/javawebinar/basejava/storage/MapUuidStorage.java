package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage {
    private Map<String, Resume> mapUuidStorage = new HashMap<>();

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return mapUuidStorage.containsKey(searchKey);
    }

    @Override
    protected void updateInStorage(Resume resume, Object uuid) {
        mapUuidStorage.put((String) uuid, resume);
    }

    @Override
    protected void saveInStorage(Resume resume, Object uuid) {
        mapUuidStorage.put((String) uuid, resume);
    }

    @Override
    protected Resume getFromStorage(Object uuid) {
        return mapUuidStorage.get(uuid);
    }

    @Override
    protected void deleteFromStorage(Object uuid) {
        mapUuidStorage.remove(uuid);
    }

    @Override
    public void clear() {
        mapUuidStorage.clear();
    }

    @Override
    protected List<Resume> getListResumes() {
        return new ArrayList<>(mapUuidStorage.values());
    }

    @Override
    public int size() {
        return mapUuidStorage.size();
    }
}
