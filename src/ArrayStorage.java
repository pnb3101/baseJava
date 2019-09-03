import static java.util.Arrays.copyOf;
import static java.util.Arrays.fill;

/**
 * Initial resume class
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;
    int checkedresume = -1;

    void update(Resume resume) {
        if (!exsistResume(resume.uuid)) {
            System.out.println("There is no resume for update.");
        } else {
            storage[checkedresume] = resume;
            System.out.println("Successful update.");
        }
    }

    void clear() {
        fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume r) {
        if (storage.length == size) {
            System.out.println("Sorry. Storage is full.");
        } else if (!exsistResume(r.uuid)) {
            storage[size] = r;
            size++;
        } else {
            System.out.println("Resume already exsist.");
        }

    }

    Resume get(String uuid) {
        Resume result = null;
        if (exsistResume(uuid)) {
            result = storage[checkedresume];
            return result;
        } else {
            System.out.println("There is no resume to get.");
            return null;
        }
    }

    void delete(String uuid) {
        if (exsistResume(uuid)) {
            storage[checkedresume] = null;
            for (int j = checkedresume; j < size - 1; j++) {
                storage[j] = storage[j + 1];
            }
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("There is no resume for delete.");
        }
    }

    Resume[] getAll() {
        Resume[] listResume = copyOf(storage, size);
        return listResume;
    }

    int size() {
        return size;
    }

    boolean exsistResume(String uuid) {
        boolean exist = false;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                exist = true;
                checkedresume = i;
            }
        }
        return exist;
    }
}

