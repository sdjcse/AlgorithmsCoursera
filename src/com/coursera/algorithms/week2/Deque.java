package com.coursera.algorithms.week2;

import sun.awt.image.ImageWatched;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by sdj on 1/4/17.
 */
public class Deque<Item> implements Iterable<Item>
{
    private class LinkedList<Item>
    {
        Item data;
        LinkedList<Item> next;
        LinkedList<Item> prev;

        LinkedList(Item data)
        {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
        LinkedList(Item data,LinkedList next,LinkedList prev)
        {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
        public Item getData()
        {
            return data;
        }
        public LinkedList getPrev()
        {
            return this.prev;
        }
        public LinkedList getNext()
        {
            return this.next;
        }
    }
    private LinkedList<Item> startPtr,endPtr;
    private int size;
    public Deque()                           // construct an empty deque
    {
        this.startPtr = null;
        this.endPtr = null;
        this.size = 0;
    }

    public boolean isEmpty()                 // is the deque empty?
    {
        return startPtr==null;
    }
    public int size()                        // return the number of items on the deque
    {
        return size;
    }
    public void addFirst(Item item)          // add the item to the front
    {
        if(item == null)
        {
            throw new NullPointerException();
        }
        LinkedList temp = this.startPtr;
        this.startPtr = new LinkedList(item,startPtr,null);
        if(this.endPtr==null)
        {
            this.endPtr = this.startPtr;
        }else{
            temp.prev = this.startPtr;
        }
        this.size++;
    }
    public void addLast(Item item)           // add the item to the end
    {
        if(item == null)
        {
            throw new NullPointerException();
        }
        LinkedList temp = endPtr;
        endPtr = new LinkedList(item,null,endPtr);

        if(startPtr==null)
        {
            startPtr = endPtr;
        }else{
            temp.next = endPtr;
        }
        size++;
    }
    public Item removeFirst()                // remove and return the item from the front
    {
        return generalizedRemove(true);
    }
    public Item removeLast()                 // remove and return the item from the end
    {
        return generalizedRemove(false);
    }

    private Item generalizedRemove(boolean isStart)
    {
        if(this.isEmpty())
        {
            throw new NoSuchElementException();
        }
        Item temp = (Item) (isStart ? startPtr.getData() : endPtr.getData());
        if(isStart)
        {
            startPtr = startPtr.getNext();
            startPtr.prev = null;
        }
        else
        {
            endPtr = endPtr.getPrev();
            endPtr.next = null;
        }
        size--;
        return temp;
    }
    private class DequeIterator<Item> implements Iterator<Item>
    {
        private final  Deque obj;
        private LinkedList<Item> ptr;
        DequeIterator(Deque obj)
        {
            this.obj = obj;
            this.ptr = obj.startPtr;
        }
        @Override
        public boolean hasNext()
        {
            return (ptr!=null);
        }

        @Override
        public  void remove()
        {
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next()
        {
            Item temp = null;
            if(this.hasNext())
            {
                temp = ptr.data;
                ptr = ptr.next;
            }
            return temp;
        }
    }
    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    {
        return new DequeIterator<Item>(this);
    }
    public static void main(String[] args)   // unit testing
    {
        Deque<Integer> testObj = new Deque<>();
        testObj.addFirst(10);
        testObj.addFirst(100);
        testObj.addFirst(1048);
        testObj.addFirst(489);
        testObj.addLast(153);
        testObj.addLast(15);
        testObj.addLast(20);
        Iterator iter = testObj.iterator();
        while(iter.hasNext())
        {
            System.out.println(iter.next());
        }
        System.out.println(testObj.size());
        System.out.println();
        System.out.println(testObj.removeFirst());
        System.out.println(testObj.removeFirst());
        System.out.println(testObj.removeLast());
        System.out.println(testObj.removeLast());
        System.out.println(testObj.size());
        System.out.println();
        iter = testObj.iterator();
        while(iter.hasNext())
        {
            System.out.println(iter.next());
        }
        System.out.println(testObj.size());
    }
}
