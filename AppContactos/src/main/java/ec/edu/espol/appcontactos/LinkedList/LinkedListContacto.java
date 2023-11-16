/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.appcontactos.LinkedList;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 *
 * @author alexc
 */
public class LinkedListContacto<E> implements List<E> {

    private Nodo<E> first;
    private Nodo<E> last;
    private int size;

    private class Nodo<E> {

        private E contenido;
        private Nodo<E> sig;
        private Nodo<E> ant;

        public Nodo(Nodo<E> ant, E e, Nodo<E> sig) { //constructor de una lista sin anidar 
            contenido = e;
            this.sig = sig;
            this.ant = ant;
        }
        
        @Override
        public String toString() {
            return "[Anterior: " + this.ant.contenido + ", Elemento: " + this.contenido + ", Siguiente: " + this.sig.contenido + "]";
        }
    }
    
    private class Iterador implements Iterator {
        private int itr = 0;

        @Override
        public boolean hasNext() {
            return itr < size;
        }

        @Override
        public Object next() {
            if(!hasNext())
                throw new NoSuchElementException();
            Nodo<E> f = first;
            for (int i = 0; i < itr; i++) {
                f = f.sig;
            }
            return f.contenido;
        }
        
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public boolean contains(Object o) {
        for (E e : this) {
            if (e.equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterador();
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean add(E e) {
        Nodo<E> tmp = new Nodo(last, e, first);
        if (isEmpty()) {
            first = tmp;
            last = tmp;
            tmp.sig = tmp;
            tmp.ant = tmp;
            size++;
            return true;
        }
        Nodo<E> nodo = last;       
        last = tmp;
        tmp.sig = first;
        nodo.sig = tmp;       
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index > size - 1)
            throw new ArrayIndexOutOfBoundsException();
        Nodo<E> tmp = first;
        for (int i = 0; i < index; i++) {
            tmp = tmp.sig;
        }
        return tmp.contenido;
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void add(int index, E element) {
        if (index <= 0 || index > this.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }

        if (index != 0) {
            Nodo<E> tmp = first;
            for (int i = 0; i < index; i++) {
                tmp = tmp.sig;
            }
            Nodo<E> tmpAnt = tmp.ant;
            Nodo<E> nuevoNodo = new Nodo(tmpAnt, element, tmp);
            tmpAnt.sig = nuevoNodo;
            tmp.ant = nuevoNodo;
        } else {
            Nodo<E> nuevoNodo = new Nodo(last, element, first);
            first.ant = nuevoNodo;
            last.sig = nuevoNodo;
            first = nuevoNodo;
        }
        size++;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index > size - 1)
            throw new IndexOutOfBoundsException();
        if (index != 0 && index != size - 1) {
            Nodo<E> nodo = first;
            for (int i = 0; i < index; i++) {
                nodo = nodo.sig;
            }
            Nodo<E> tmpSig = nodo.sig;
            Nodo<E> tmpAnt = nodo.ant;
            tmpSig.ant = tmpAnt;
            tmpAnt.sig = tmpSig;
            size--;
            return nodo.contenido;
        } else if (index == 0)
            return removeFirst();
        else
            return removeLast();
    }
    
    public E removeLast() {
        E elem = first.contenido;
        first.ant = last.ant;
        last.ant.sig = first;
        last = last.ant;
        size--;
        return elem;
    }
    
    public E removeFirst() {
        E elem = last.contenido;
        last.sig = first.sig;
        first.sig.ant = last;
        first = first.sig;
        size--;
        return elem;
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
