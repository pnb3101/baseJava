import java.util.ArrayList;
import java.util.Iterator;

/**
 * Initial resume class
 */
public class ArrayStorage {
        Resume[] storage = new Resume[10000];

        void clear() {
            for(int i=0; i<storage.length; i++) {
                storage[i] = null;
            }
        }

        void save(Resume r) {
            int size = size();
            storage[size] = r;
        }

        Resume get(String uuid) {
          Resume result = null;
          try {
              for (int i = 0; i < storage.length; i++) {
                  if (storage[i].uuid.equals(uuid))
                      result = storage[i];
              }
          }catch (NullPointerException e){

          }
          return result;
        }

        void delete(String uuid) {
          try {
             for (int i = 0; i < storage.length; i++) {
                 if (storage[i].uuid.equals(uuid))
                     storage[i] = null;
                    for(int j=i; j<storage.length-1; j++){
                        storage[j]=storage[j+1];
                    }
                 storage[storage.length-1]=null;
                 break;
             }
         }catch(NullPointerException e){    }
        }

        Resume[] getAll() {
          return storage;
        }

        int size() {
            int sizestorage=0;
            try {
                for (int i = 0; i < storage.length; i++) {
                    if (!storage[i].equals(null))
                        sizestorage++;
                }
            }catch(NullPointerException e){     }
            return sizestorage;
        }
    }

