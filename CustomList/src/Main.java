public class Main {
    public static void main(String[]args){
        CustomList<Integer> myList = new CustomList<>();
        CustomList<String> myCars = new CustomList<>(2);

        myList.add(2);
        myCars.add("Chevy HHR");
        myCars.add("Honda Van");
        System.out.println(myList.get(0));
        System.out.println(myCars.get(1));
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


    public void add(int index, T element) {

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