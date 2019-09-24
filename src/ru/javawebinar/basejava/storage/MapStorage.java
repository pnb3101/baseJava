package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> storageMap = new HashMap<>();

    @Override
    protected Resume getSearchKey(String uuid) {
        return storageMap.get(uuid);
    }

    @Override
    protected boolean isExist(Object resume) {
        return resume != null;
    }

    @Override
    protected void updateInStorage(Resume resume, Object searchKey) {
        storageMap.put(resume.getUuid(), resume);
    }

    @Override
    protected void saveInStorage(Resume resume, Object searchKey) {
        storageMap.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getFromStorage(Object searchKey) {
        return (Resume) searchKey;
    }

    @Override
    protected void deleteFromStorage(Object searchKey) {
        storageMap.remove(((Resume) searchKey).getUuid());
    }

    @Override
    public void clear() {
        storageMap.clear();
    }

    @Override
    protected List<Resume> getListResumes() {
        return new ArrayList<>(storageMap.values());
    }

    @Override
    public int size() {
        return storageMap.size();
    }
}
