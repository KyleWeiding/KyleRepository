import javax.xml.crypto.Data;
import java.util.ArrayList;

// Linked Lists are stored using nodes
// each node has a value as well as a pointer to the next node
public class CustomLinkedList<T> implements CustomList<T> {
    // every linked list has a head, or the start of the list
    Node head;

    // fairly simple class, just store the data that we need
    class Node {
        // every node has some data
        T data;
        // pointer to the next node:
        Node next;
        // constructor for our node, takes in data and set it to our member variable:
        Node(T data) {
            this.data = data;
        }
    }

    // make a constructor:
    public CustomLinkedList() {
        // specify that our head is null because our list is empty:
        this.head = null;
    }

    @Override
    public void add(T element) {
        // we want to take the argument and add it to our list:
        // case 1: we have an empty list:
        if (this.head == null) {
            // setting the data of the head to be what we passed in:
            this.head = new Node(element);
            // since this is the first element of the list, the "next" pointer points to null
            this.head.next = null;
        }
        else {
            // initialize our placeholder to be the head
            Node placeholder = this.head;
            // if we already have some values:
            while(true) {
                // every iteration, we want to check the "next" value
                // if the "next" node is null, we have reached the end of our list and we can inster the new elemtn
                if(placeholder.next == null) {
                    // assign the value:
                    placeholder.next = new Node(element);
                    // once we reach the end of the list, we want to break:
                    break;
                }
                else {
                    // increment the placeholder
                    placeholder = placeholder.next;
                }
            }
        }
    }

    @Override
    public T get(int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("No negative values!");
        }
        // since we're storing this with pointers, we have to traverse this list to get to it
        Node placeholder = this.head;
        // start our counter at 0
        for(int j = 0; j < i; j ++){
            // if we reach a null value before the end of the list, we will break
            if(placeholder == null) {
                throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + j);
            }
            placeholder = placeholder.next;
        }
        // if we made it through the loop, this entails that we iterated enough times so that we got to the correct index:
        return placeholder.data;
    }

    @Override
    public void print() {
        // iterate through each node and print it out:
        Node placeholder = this.head;
        while(placeholder != null) {
            // every step, we print out the data and then iterate our node:
            System.out.print(placeholder.data + ", ");
            placeholder = placeholder.next;
        }
        System.out.println();

    }

    @Override
    public void add(int i, T element) {
        // TODO: Implement this
        //New Code Start:
        ArrayList<Data> list = new ArrayList<>();
        for (int i = 0; i < T; i++) {
            list.add(new Data([i], T));
        }
        //New Code End - Kyle Weiding
    }

}


/*
Array List [1,2,3,5,6,7,8,9]
If we want to insert 4 into the ArrayList, we would have to shift over a bunch of the elements:
Linked List 1 -> 2 -> 3 -> 5 -> 6 -> 7 -> 8 -> 9 -> null
With a linked list, we could just make 3 point to a new node 4, and have the new node point to 5, then we don't have to copy over the rest
 */
