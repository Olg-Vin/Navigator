package LinkedList;

import Navigator.Route;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<E> implements Iterable<E> {
    private static final int DEFAULT_CAPACITY = 10;
    Object[] array;
    private int size;
    public MyArrayList() {
        this.array = new Object[DEFAULT_CAPACITY];
    }
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public void add(E element){
        growIfNeed();
        array[size] = element;
        size++;
    }
    public E get(int index) {
        if (index < 0 || index >= size) {
            System.out.println("index out of range");;
        }
        return (E) array[index];
    }
    void growIfNeed(){
        if (size == array.length)
            grow();
    }

    void grow(){
        int newCapacity = array.length * 2;
        array = Arrays.copyOf(array, newCapacity);
    }
    public void sort(Comparator<Route> comparator){
        int index = 0;
        while (index < this.size) {
            if (index == 0) {
                index++;
            }
            if (comparator.compare((Route) array[index], (Route) array[index - 1]) >= 0) {
                index++;
            } else {
                E temp = (E) array[index];
                array[index] = array[index-1];
                array[index-1]=temp;
                index--;
            }
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new MyArrayListIterator();
    }
    private class MyArrayListIterator implements Iterator<E> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (E) array[currentIndex++];
        }
    }

}
