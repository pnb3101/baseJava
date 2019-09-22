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
    protected void updateStorage(Resume resume, Object searchKey) {
        storageMap.put(resume.getUuid(), resume);
    }

    @Override
    protected void saveStorage(Resume resume, Object searchKey) {
        storageMap.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getStorage(Object searchKey) {
        return (Resume) searchKey;
    }

    @Override
    protected void deleteStorage(Object searchKey) {
        storageMap.remove(((Resume) searchKey).getUuid());
    }

    @Override
    public void clear() {
        storageMap.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> resumesList = Arrays.asList(storageMap.values().toArray(new Resume[0]));
        Collections.sort(resumesList);
        return resumesList;
    }

    @Override
    public int size() {
        return storageMap.size();
    }
}
