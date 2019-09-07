package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    public void save(Resume resume) {
        if (getIndex(resume.getUuid()) != -1) {
            System.out.println("Resume " + resume.getUuid() + " already exist");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else {
            int index = -getIndex(resume.getUuid()) - 1;
            System.arraycopy(storage, index, storage, index + 1, size - index-1);
            storage[index] = resume;
            size++;
        }
    }

    @Override
    public void delete(String uuid) {
        if(size>=1){
            int index = getIndex(uuid);
            System.arraycopy(storage, index+1, storage, index, size-index-1);
            size--;
        }
    }

    @Override
    public int getIndex(String uuid) {//protected must be
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
