import java.util.ArrayList;
import java.util.Iterator;

/**
 * Initial resume class
 */
public class ArrayStorage {
    Resume[] storage = new Resume[5];
    int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        Resume result = null;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid))
                result = storage[i];
        }
        return result;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid))
                storage[i] = null;
            for (int j = i; j < size - 1; j++) {
                storage[j] = storage[j + 1];
            }
            storage[size - 1] = null;
            break;
        }
        size--;
    }

    Resume[] getAll() {
        Resume[] listResume = new Resume[size];
        for (int i = 0; i < size; i++) {
            listResume[i] = storage[i];
        }
        return listResume;
    }

    int size() {
        return size;
    }
}

