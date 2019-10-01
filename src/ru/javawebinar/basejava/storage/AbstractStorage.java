package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    @Override
    public void update(Resume resume) {
        SK searchKey = getSearchKey(resume.getUuid());
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            updateInStorage(resume, searchKey);
        }
    }

    @Override
    public void save(Resume resume) {
        SK searchKey = getSearchKey(resume.getUuid());
        if (isExist(searchKey)) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            saveInStorage(resume, searchKey);
        }
    }

    @Override
    public Resume get(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        } else {
            return getFromStorage(searchKey);
        }
    }

    @Override
    public void delete(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteFromStorage(searchKey);
        }
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> resumesList = getListResumes();
        Collections.sort(resumesList);
        return resumesList;
    }

    protected abstract List<Resume> getListResumes();

    protected abstract SK getSearchKey(String uuid);

    protected abstract boolean isExist(SK searchKey);

    protected abstract void updateInStorage(Resume resume, SK searchKey);

    protected abstract void saveInStorage(Resume resume, SK searchKey);

    protected abstract Resume getFromStorage(SK searchKey);

    protected abstract void deleteFromStorage(SK searchKey);
}
