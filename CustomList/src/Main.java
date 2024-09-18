public class Main {
    public static void main(String[]args){
        CustomList<Integer> myList = new CustomList<>();
        CustomList<String> myCars = new CustomList<>(2);

        myList.add(2);
        myCars.add("Chevy HHR");
        myCars.add("Honda Van");
        myCars.add(1, "Toyota 4Runner");
        System.out.println(myList.get(0));
        System.out.println(myCars.get(1));
        System.out.println(myCars.get(2));
    }
}

class CustomList<T> {
    private T[] data;
    private int size;
    private int capacity;

    public CustomList() {
        this(10);
    }
    public CustomList(int capacity) {
        if (capacity <= 0) {
            this.capacity = 10;
        } else {
            this.capacity = capacity;
        }
        this.data = (T[]) new Object[this.capacity];
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
     * @return void
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

    /*
    set(int index, T element)
    remove(int index)
    size()
    isEmpty()
    contains(T element)
    indexOf(T element)
    clear()
    toArray()
    ensureCapacity(int minCapacity)
    trimToSize()
    */
    public void resize() {
        this.capacity *= 2;
        T[] newData = (T[]) new Object[this.capacity];
        for (int i = 0; i < this.size; i++) {
            newData[i] = this.data[i];
        }
        this.data = newData;
    }
}