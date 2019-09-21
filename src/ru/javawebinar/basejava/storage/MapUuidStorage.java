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
    protected boolean isExist(Object uuid) {
        return mapUuidStorage.containsKey(uuid);
    }

    @Override
    protected void updateStorage(Resume resume, Object uuid) {
        mapUuidStorage.put((String) uuid, resume);
    }

    @Override
    protected void saveStorage(Resume resume, Object uuid) {
        mapUuidStorage.put((String) uuid, resume);
    }

    @Override
    protected Resume getStorage(Object uuid) {
        return mapUuidStorage.get(uuid);
    }

    @Override
    protected void deleteStorage(Object uuid) {
        mapUuidStorage.remove(uuid);
    }

    @Override
    public void clear() {
        mapUuidStorage.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> resumesList = new ArrayList<>(mapUuidStorage.values());
        Collections.sort(resumesList);
        return resumesList;
    }

    @Override
    public int size() {
        return mapUuidStorage.size();
    }
}
