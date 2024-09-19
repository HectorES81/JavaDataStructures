import java.lang.reflect.Array;
import java.util.Random;

public class Main {
    public static void main(String[]args){
        CustomList<Integer> myList = new CustomList<>(Integer.class);
        CustomList<String> myCars = new CustomList<String>(String.class, 2);
        CustomList<Integer> emptyList = new CustomList<>(Integer.class);
        CustomList<Integer> tenThousandInts = new CustomList<>(Integer.class);
        for (int i = 0; i <= 10000 ; i++) {
            tenThousandInts.add((int) (Math.random() * i));
        }

        myList.add(2);
        myCars.add("Chevy HHR");
        myCars.add("Honda Van");
        myCars.add(1, "Toyota 4Runner");
        myCars.set(0, "Ford Ranger");

        System.out.println(myList.get(0));
        System.out.println(myCars.get(0));
        System.out.println(myCars.get(1));
        System.out.println(myCars.get(2));
        myCars.remove(0);
        System.out.println(myCars.get(1));
        System.out.println(emptyList.isEmpty());
        System.out.println(myCars.contains("Honda Va"));
        System.out.println(myCars);

        tenThousandInts.insert(5, 50);
        System.out.println(tenThousandInts);
        tenThousandInts.insertionSort();
        System.out.println(tenThousandInts);
    }
}
@SuppressWarnings("unchecked")
class CustomList<T extends Comparable<T>> {
    private T[] data;
    private int size;
    private int capacity;
    private Class<T> type;

    public CustomList(Class<T> type) {
        this(type,10);
    }
    public CustomList(Class<T> type, int capacity) {
        this.type = type;
        if (capacity <= 0) {
            this.capacity = 10;
        } else {
            this.capacity = capacity;
        }

        this.data = (T[]) Array.newInstance(type, this.capacity);
        this.size = 0;
    }

    public void add(T element) {
        if( this.size == this.capacity) {
            this.resize();
        }
        this.data[this.size] = element;
        this.size++;
    }

    /**
     * Adds or inserts an element at the desired position, it will increase the capacity and size
     * of the array.
     * @param index the index at whic to insert the new element
     * @param element the element to insert
     *
     */
    public void add(int index, T element) {
        checkIndex(index);
        if(this.size == this.capacity) {
            this.resize();
        }

        for (int i = this.size-1; i >= index; i--) {
            this.data[i+1] = this.data[i];
        }
        this.data[index] = element;
        this.size++;
    }
    public void insert(int index, T element) {
        this.add(index, element);
    }

    private void checkIndex(int index) {
        if(index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
    }
    public T get(int index) {
        if(index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        return this.data[index];
    }

    /**
     * Sets the value at an index, replacing the existing value
     *
     * @param index index of element to set
     * @param element the value of the element to set
     */
    public void set(int index, T element) {
        checkIndex(index);
        this.data[index] = element;
    }

    /**
     * Removes an element at that index and shifts all the elements down.
     * List size is decreased by one
     *
     * @param index the index of the element to remove
     */
    public void remove(int index) {
        checkIndex(index);
        for (int i = index; i < this.size -1; i++) {
            this.data[i] = this.data[i+1];
        }
        this.data[this.size - 1] = null;
        this.size--;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return (this.size == 0);
    }

    public boolean contains(T element) {
        for (int i = 0; i < this.size; i++) {
            if (element == null) {
                if ( this.data[i] == null) {
                    return true;
                }
            } else {
                if(element.equals(this.data[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    public void insertionSort() {
        for (int i = 1; i < this.size; i++) {
            T keyElem = this.data[i];
            int j = i -1;

            while (j >=0 && this.data[j].compareTo(keyElem) > 0) {
                this.data[j+1] =this.data[j];
                j--;
            }
            this.data[j + 1] = keyElem;
        }
    }
    /*
    indexOf(T element)
    clear()
    toArray()
    ensureCapacity(int minCapacity)
    trimToSize()
    */
    public void resize() {
        this.capacity *= 2;
        T[] newData = (T[]) Array.newInstance(type, this.capacity);
        System.arraycopy(this.data, 0, newData, 0, this.size);
        this.data = newData;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");

        for (int i = 0; i < this.size; i++) {
            sb.append(this.data[i]);
            if (i < this.size - 1){
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}