

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomHashSet<T> implements CustomSet<T> {


    private static class Entry {
        // data:
        Object key;
        // pointer to the next value:
        Entry next;
    }

    // declare our underlying structure, we fill up this array with different data:
    private Entry[] buckets;

    private int size;

    // when we instantiate out custom hash set, we declare how big we want it to be:
    // think about how much data you will store and determining your capacity accordingly:
    public CustomHashSet(int capacity) {
        // start out empty:
        size = 0;
        // init array to be as big as the capacity that we pass in:
        buckets = new Entry[capacity];
    }

    // make a hash function, takes our data and converts to a number to be used as the index:
    private int hashFunction(int hashCode) {
        int index = hashCode;
        // make sure index is positive:
        if (index < 0) index = -index;
        // return the remainder of dividing by the length, ensures that our index is within the proper bounds:
        // % -> modulus -> remainder
        return index % buckets.length;
    }


    // only add the element if it is not present because sets store unique values (no duplicates)
    @Override
    public boolean add(T element) {
        int index = hashFunction(element.hashCode());

        // grab the entry at the specified index:
        Entry current = buckets[index];

        // keep looping while current is non-null (meaning that there is already a value there)
        // keep iterating until we find an empty spot
        while(current != null) {
            // if we find the value that we are trying to insert, return false because we don't insert it:
            if (current.key.equals(element)) return false;
            // iterate to the next entry:
            current = current.next;
        }
        // after this loop, assume we've reached an empty spot and the element is not in the set:
        Entry entry = new Entry();
        entry.key = element;
        // set up our next pointer:
        // TODO come back to this:
        entry.next = buckets[index];
        // assigning the entry to this spot in the array:
        buckets[index] = entry;
        // increment our size because we've added an element:
        size ++;
        // return true because we insert it:
        return true;
    }

    @Override
    public boolean remove(T element) {
        int index = hashFunction(element.hashCode());
        Entry current = buckets[index];
        // keep track of previous so we can "fix the link" when we remove:
        Entry previous = null;

        // while we have values:
        while(current != null) {
            // if we find the element, we remove it:
            if(current.key.equals(element)) {
                // "break the chain" and remove the element from the chain of elements
                // if this is the first index:
                if(previous == null) {
                    // changing the "inital" node
                    buckets[index] = current.next;
                }
                else {
                    // thinking of trains, removing the current train car and hooking up the previous car with the next (So there's no gap in the train)
                    // making our previous node point to the next node:
                    previous.next = current.next;
                }
                // decrease the size because we removed an element
                size --;
                // return true because we want to return whether or not the removal happened
                return true;
            }
            // increment our pointers:
            previous = current;
            current = current.next;
        }
        // we never found the element, so we didn't remove it:
        return false;
    }

    // true if set contains element, false otherwise:
    @Override
    public boolean contains(T element) {
        int index = hashFunction(element.hashCode());
        Entry current = buckets[index];

        while(current != null) {
            // only return true if we find the element:
            if(current.key.equals(element)) {return true;}
            // increment current
            current = current.next;
        }
        // if we got through all of the "next" entries, we didn't find our value:
        return false;
    }

    @Override
    public Iterator iterator() {
        // return an instance of our custom iterator:
        return new CustomHashSetIterator();
    }

    class CustomHashSetIterator implements Iterator {
        // indexes:
        private int currentBucket;
        private int previousBucket;
        // entries:
        private Entry currentEntry;
        private Entry previousEntry;

        public CustomHashSetIterator() {
            // initialize our values to be "default"
            currentEntry = null;
            previousEntry = null;
            currentBucket = -1;
            previousBucket = -1;
        }

        // do we have another value?
        @Override
        public boolean hasNext() {
            // current entry:
            if(currentEntry != null && currentEntry.next != null) {
                return true;
            }

            // iterate through the buckets to find any non-null values:
            for(int index = currentBucket + 1; index < buckets.length; index ++) {
                if(buckets[index] != null) return true;
            }

            // nothing left:
            return false;
        }

        // return the next value, increment the iterator
        @Override
        public Object next() {
            previousEntry = currentEntry;
            previousBucket = currentBucket;

            // if either the current or next node are null:
            if(currentEntry == null || currentEntry.next == null) {
                currentBucket ++;

                // keep going until we find a bucket that contains a node:
                while(currentBucket < buckets.length && buckets[currentBucket] == null) {
                    currentBucket++;
                }

                // if we're still within the bounds of our buckets:
                if(currentBucket < buckets.length) {
                    // update currentEntry pointer:
                    currentEntry = buckets[currentBucket];
                }
                // if we went past the bounds of the array
                else {
                    // if we try to access an element that isn't there
                    throw new NoSuchElementException();
                }
            }
            // this means there is still at least one more element in the bucket:
            else {
                // incrementing the current pointer
                currentEntry = currentEntry.next;
            }
            // return the next object (since we already updated the pointer, we just return the current entry key
            return currentEntry.key;
        }
    }

    // return the private size variable:
    @Override
    public int size() {
        return size;
    }

    // TODO Implement this:
    // print out each element in the set:
    //Added this section 4/27:

    //New Code Start:
    CustomList<String> set = new HashSet<String>();
    for (String s : set) {
        System.out.println(s);
    }
    //New Code End - Kyle Weiding

    public String toString() {
        return "";
    }
}
