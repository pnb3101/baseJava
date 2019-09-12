package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    private Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private Resume resume = new Resume();
    private Resume resume1 = new Resume(UUID_1);
    private Resume resume2 = new Resume(UUID_2);
    private Resume resume3 = new Resume(UUID_3);

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @Test
    public void get() {
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void update() {
        Storage storageBeforeUpdate = storage;
        storage.update(resume1);
        Assert.assertEquals(storageBeforeUpdate, storage);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateException() {
        storage.update(resume);
    }


    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void getAll() {
        Resume[] resumes = {resume1, resume2, resume3};
        Arrays.sort(resumes);
        Resume[] resumesAfterGet = storage.getAll();
        Arrays.sort(resumesAfterGet);
        Assert.assertTrue(Arrays.equals(resumes, resumesAfterGet));
    }

    @Test
    public void save() {
        storage.save(resume);
        Assert.assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveException() {
        storage.save(resume1);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_2);
        storage.get(UUID_2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("uuid8");
    }

    @Test
    public void storageOverflow() {
        try {
            while (storage.size() < AbstractArrayStorage.STORAGE_LIMIT) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail("Test is fail.");
            System.out.println(e.toString());
        }
        storage.save(new Resume());
    }
}