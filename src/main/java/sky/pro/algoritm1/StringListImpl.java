package sky.pro.algoritm1;

import java.util.Arrays;

public class StringListImpl implements StringList {

    private final String[] storage;
    private int size;

    public StringListImpl() {
        storage = new String[10];

    }

    public StringListImpl(int initSize) {
        storage = new String[initSize];
    }

    @Override
    public String add(String item) {
        validateSize();
        validateItem(item);
        storage[size++]=item;
        return item;
        /*storage[size++]=item;
        if (size == storage.length) {
            resize(size + 1);
        }
        storage[size] = item;
        size++;

        return item;*/
    }

    @Override
    public String add(int index, String item) {
        validateSize();
        validateItem(item);
        validateIndex(index);

        if (index==size) {
            storage[size++]=item;
            return item;
        }
        System.arraycopy(storage,index,storage,index+1,size-index);
        storage[index]=item;
        size++;

        return item;


        /*checkBounds(index);
        if (size == storage.length) {
            resize(size + 1);
        }
        for (int i = size, i>index, i--){
            storage[i] = storage[i - 1];
        }
        storage[index] = item;
        size++;

        return item;*/
    }

    @Override
    public String set(int index, String item) {
        validateIndex(index);
        validateItem(item);
        storage[index] = item;
        return item;

    }

    @Override
    public String remove(String item) {
        validateItem(item);

        int index = indexOf(item);
        /*if (index==-1) {
            throw new ElementNotFoundException();
        }
        if (index != size) {

            System.arraycopy(storage, index + 1, storage, index, size - index);
        }

        size--;
        return item;*/
        return remove(index);
    }

    @Override
    public String remove(int index) {

validateIndex(index);

        String item = storage[index];
        if (index != size) {

            System.arraycopy(storage, index + 1, storage, index, size - index);
        }

        size--;
        return item;
    }

    @Override
    public boolean contains(String item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = size - 1; i >= 0; i--) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        validateIndex(index);

        return storage[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public String[] toArray() {
        Arrays.copyOf(storage, size);
        return new String[0];
    }

        /*private void resize(int newSize) {
            int size= this.size *2;
            size = Math.max(size, newSize);
            String[] newData = new String[size];
            System.arraycopy(storage,0,newData,0, this.size);
            storage =newData;
        }

        private void checkBounds(int index) {
            if (index < 0 || index> size) {
                throw new StorageIsFullException();

            }
        }*/

    private void validateItem(String item) {
        if (item == null) {
            throw new NullItemException();
        }
    }

    private void validateSize() {
        if (size == storage.length) {
            throw new StorageIsFullException();
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index > size) {
            throw new InvalidIndexException();
        }
    }
}

