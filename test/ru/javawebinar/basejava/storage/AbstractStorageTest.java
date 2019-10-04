package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;


public abstract class AbstractStorageTest {
    Storage storage;
    static ResumeTestData resume = new ResumeTestData();

    static final String UUID_1 = "uuid1";
    static final String UUID_2 = "uuid2";
    static final String UUID_3 = "uuid3";
    static final String UUID_4 = "uuid4";

    static final Resume RESUME_1 = resume.createResume(UUID_1, "Name1");
    static final Resume RESUME_2 = resume.createResume(UUID_2, "Name2");
    static final Resume RESUME_3 = resume.createResume(UUID_3, "Name3");
    static final Resume RESUME_4 = resume.createResume(UUID_4, "Григорий Кислин");

    AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void get() {
        assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void update() {
        Resume updatedResume = RESUME_1;
        storage.update(updatedResume);
        assertSame(updatedResume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateException() {
        storage.update(RESUME_4);
    }


    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void getAllSorted() {
        List<Resume> resumesAfterGet = storage.getAllSorted();
        assertEquals(3, resumesAfterGet.size());
        assertEquals(Arrays.asList(RESUME_1, RESUME_2, RESUME_3), resumesAfterGet);
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertEquals(storage.get(UUID_4), RESUME_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveException() {
        storage.save(RESUME_1);
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

}