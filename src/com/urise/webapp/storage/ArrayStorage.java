package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Initial resume class
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size = 0;
    private int index = -1;

    public void update(Resume resume) {
        if (!isExist(resume.getUuid())) {
            System.out.println("There is no resume for update.");
        } else {
            storage[index] = resume;
            System.out.println("\nSuccessful update.");
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (storage.length == size) {
            System.out.println("Sorry. Storage is full.");
        } else if (!isExist(resume.getUuid())) {
            storage[size] = resume;
            size++;
        } else {
            System.out.println("Resume already exsist.");
        }

    }

    public Resume get(String uuid) {
        if (isExist(uuid)) {
            return storage[index];
        }
        System.out.println("There is no resume to get.");
        return null;
    }

    public void delete(String uuid) {
        if (isExist(uuid)) {
            if (size - 1 - index >= 0) System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("There is no resume for delete.");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private boolean isExist(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                index = i;
                return true;
            }
        }
        return false;
    }
}

