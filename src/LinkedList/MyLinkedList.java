package LinkedList;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E>
        implements MyList<E>, Iterable<E> {
    int size = 0;
    Node<E> first;
    Node<E> last;
    public MyLinkedList() {
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size<=0;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) >= 0;
    }

    @Override
    public void add(E element) {
        addLast(element);
    }
    private void addLast(E element){
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, element, null);
        last = newNode;
        if (l == null) first = newNode;
        else l.next = newNode;
        size++;
    }
    private void addFirst(E element){
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(null, element, f);
        first = newNode;
        if (f == null) last = newNode;
        else f.prev = newNode;
        size++;
    }
    private void addBefore(E element, Node<E> current){
        final Node<E> previous = current.prev;
        final Node<E> newNode = new Node<>(previous, element, current);
        current.prev = newNode;
        if (previous == null) first = newNode;
        else previous.next = newNode;
        size++;
    }
    @Override
    public void add(int index, E element) {
        if (index == size) addLast(element);
        else if (index==0) addFirst(element);
        else addBefore(element, node(--index));
    }
    private Node<E> node(int index) {
        Node<E> x;
        if (index < (size >> 1)) {
            x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
        } else {
            x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
        }
        return x;
    }

    @Override
    public E get(int index) {
        return node(index).item;
    }

    @Override
    public E remove(int index) {
        Node<E> current = node(index);
        final E element = current.item;
        final Node<E> next = current.next;
        final Node<E> prev = current.prev;
        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            current.prev = null;
        }
        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            current.next = null;
        }
        current.item = null;
        size--;
        return element;
    }

    @Override
    public boolean remove(E element) {
        Node<E> current = first;
        Node<E> previous = null;
        while (current != null) {
            if (current.item.equals(element)) {
                if (previous != null) {
                    previous.next = current.next;
                    if (current.next == null)
                        last = previous;
                    size--;
                } else {
                    first = first.next;
                    if (first == null)
                        last = null;
                    size--;
                }
                return true; // Элемент был найден и удален
            }
            previous = current;
            current = current.next;
        }
        return false; // Элемент не был найден в списке
    }

    @Override
    public void clear() {
        for (Node<E> x = first; x != null; ) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
    }

    public int indexOf(E element) {
        int index = 0;
        if (element == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) return index;
                index++;
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (element.equals(x.item)) return index;
                index++;
            }
        }
        return -1;
    }
    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;
        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }
    private class LinkedListIterator implements Iterator<E> {
        private Node<E> current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();
            E data = current.item;
            current = current.next;
            return data;
        }
    }
}
