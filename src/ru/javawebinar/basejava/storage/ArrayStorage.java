package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Initial resume class
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public int getIndex(String uuid) {//protected must be
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}



