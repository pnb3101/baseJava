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
import static ru.javawebinar.basejava.storage.ResumeTestData.*;

public abstract class AbstractStorageTest {
    Storage storage;

    AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(createResume(UUID_1, "Name1"));
        storage.save(createResume(UUID_2, "Name2"));
        storage.save(createResume(UUID_3, "Name3"));
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
        Resume updatedResume = createResume(UUID_1, "New Name");
        storage.update(updatedResume);
        assertSame(updatedResume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateException() {
        storage.update(createResume(UUID_4, "Григорий Кислин"));
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
        assertEquals(Arrays.asList(createResume(UUID_1, "Name1"), createResume(UUID_2, "Name2"),
                createResume(UUID_3, "Name3")), resumesAfterGet);
    }

    @Test
    public void save() {
        storage.save(createResume(UUID_4, "Григорий Кислин"));
        assertEquals(storage.get(UUID_4), createResume(UUID_4, "Григорий Кислин"));
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