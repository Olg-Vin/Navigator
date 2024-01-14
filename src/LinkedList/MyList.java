package LinkedList;

import java.util.Comparator;

public interface MyList<E> extends Iterable<E>{
    int size(); // возвращает количество элементов в списке
    boolean isEmpty(); // возвращает true, если список пустой
    boolean contains(E element); // возвращает true, если список содержит указанный элемент
    void add(E element); // добавляет элемент в конец списка
    void add(int index, E element); // добавляет элемент по указанному индексу
    E get(int index); // возвращает элемент по указанному индексу
    E remove(int index); // удаляет элемент по указанному индексу
    boolean remove(E element); // удаляет первое вхождение указанного элемента
    void clear(); // удаляет все элементы из списка
}
