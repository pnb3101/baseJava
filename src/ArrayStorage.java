import java.util.ArrayList;
import java.util.Iterator;

/**
 * Initial resume class
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private ArrayList<Resume> resumes = new ArrayList();

    public ArrayStorage() {
    }

    void clear() {
        this.resumes.removeAll(this.resumes);
    }

    void save(Resume r) {
        this.resumes.add(r);
    }

    Resume get(String uuid) {
        Iterator var2 = this.resumes.iterator();

        Resume r;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            r = (Resume) var2.next();
        } while (!r.uuid.equals(uuid));

        return r;
    }

    void delete(String uuid) {
        for (int i = 0; i < this.resumes.size(); ++i) {
            if (((Resume) this.resumes.get(i)).uuid.equals(uuid)) {
                this.resumes.remove(i);
            }
        }

    }

    Resume[] getAll() {
        ArrayList arr = new ArrayList();

        try {
            Iterator var2 = this.resumes.iterator();

            while (var2.hasNext()) {
                Resume r = (Resume) var2.next();
                arr.add(r);
            }
        } catch (NullPointerException var4) {
            System.out.println("No resume in storage");
        }

        Resume[] result = new Resume[0];
        result = (Resume[]) arr.toArray(new Resume[10000]);
        return result;
    }

    int size() {
        return this.resumes.size();
    }

}

