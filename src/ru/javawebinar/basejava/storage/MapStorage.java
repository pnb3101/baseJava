package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> storageMap = new HashMap<>();

    @Override
    protected int getIndex(String uuid) {
        if(storageMap.containsKey(uuid)){
            return 1;
        }
        return -1;
    }

    @Override
    protected void updateStorage(Resume resume, int index) {
        storageMap.replace(resume.getUuid(), resume);
    }

    @Override
    protected void saveStorage(Resume resume, int index) {
        storageMap.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getStorage(int index) {
        List<Resume> resumes = new ArrayList<>(storageMap.values());
        return resumes.get(index);
    }

    @Override
    protected void deleteStorage(int index) {
        List<Resume> resumes = new ArrayList<>(storageMap.values());
        resumes.remove(index);
    }

    @Override
    public void clear() {
        storageMap.clear();
    }

    @Override
    public Resume[] getAll() {
        return storageMap.values().toArray(new Resume[storageMap.size()]);
    }

    @Override
    public int size() {
        return storageMap.size();
    }
}
