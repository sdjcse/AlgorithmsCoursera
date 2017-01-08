package com.coursera.algorithms.week2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by sdj on 1/4/17.
 */
public class RandomizedQueue<Item> implements Iterable<Item>
{
    private Item containArray[] = (Item [])new Object[1];
    private int start, end;

    // Constructor
    public RandomizedQueue()                 // construct an empty randomized queue
    {
        start = 0;
        end = 0;

    }

    // Double the size of the existing array if the size limit exceeds
    private void increaseSize()
    {
        Item tempArray [] = (Item [])new Object[containArray.length*2];
        for ( int i=0 ; i < containArray.length ; i++ )
        {
            tempArray[i] = containArray[i];
        }
        this.containArray = tempArray;
    }

    // reduce the size by half if the number of items in the array is 1/4th
    private void decreaseSize()
    {
        int i , j;
        if (this.size() < (containArray.length/4)+1)
        {
            Item tempArray [] = (Item [])new Object[containArray.length/2];
            for ( i=0 , j=start ; j < end ; j++ , i++)
            {
                tempArray[i] = this.containArray[j];
            }
            start = 0;
            end = j;
        }
    }

    public boolean isEmpty()                 // is the queue empty?
    {
        return end-start == 0;
    }

    public int size()                        // return the number of items on the queue
    {
        return end-start;
    }

    public void enqueue(Item item)           // add the item
    {
        if (item == null)
        {
            throw  new NullPointerException();
        }
        if (end-start == containArray.length)
        {
            this.increaseSize();
        }
        this.containArray[end] = item;
        end++;
    }
    public Item dequeue()                    // remove and return a random item
    {
        if (this.size() == 0)
        {
            throw new NoSuchElementException();
        }
        int temp = StdRandom.uniform(start,end);
        Item tempObj = this.containArray[temp];
        if (temp == end-1)
        {
            this.containArray[temp] = null;
        }
        else
        {
            this.containArray[temp] = this.containArray[end-1];
            this.containArray[end-1] = null;
        }
        end--;
        if (this.size() <= this.containArray.length/4)
        {
            this.decreaseSize();
        }
        return tempObj;
    }
    public Item sample()                     // return a random item (but do not remove it)
    {
        if ( this.size() == 0 )
        {
            throw new NoSuchElementException();
        }
        return this.containArray[StdRandom.uniform(start,end)];
    }
    public Iterator<Item> iterator()         // return an independent iterator over items in random order
    {
        return new RandomizedQueueIterator<Item>(this);
    }

    private class RandomizedQueueIterator<Item> implements Iterator<Item>
    {
        private final  RandomizedQueue<Item> obj;
        int [] localOrder = null;
        private int s;
        RandomizedQueueIterator(RandomizedQueue<Item> obj)
        {
            this.obj = obj;
            localOrder = new int[obj.size()];
            for(int i=start,k=0;i<end;i++,k++)
            {
                localOrder[k] = i;
            }
            this.shuffleArray(localOrder);
        }

        private void shuffleArray(int [] a)
        {
            for ( int i = 0 ; i < a.length ; i++ )
            {
                this.swap(a , i , StdRandom.uniform(0 , a.length-1 ));
            }
        }

        private void swap(int [] a , int source , int destiny)
        {
            int temp = a[source];
            a[source] = a[destiny];
            a[destiny] = temp;
        }


        @Override
        public boolean hasNext()
        {
            return ! ( s == localOrder.length );
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
            if ( this.hasNext() )
            {
                temp = (Item) obj.containArray[localOrder[s]];
                localOrder[s] = 0;
                s++;
            }
            else
            {
                throw new NoSuchElementException();
            }
            return temp;
        }
    }

    public static void main(String[] args)   // unit testing (required)
    {
    }
}
