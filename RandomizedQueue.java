import edu.princeton.cs.algs4.StdRandom;    
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] arr;
    private int index;
    private int size;
    private void swap(int a,int b){
        Item temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    private void resizeArray(int capacity){
        Item[] temp = (Item[])new Object[capacity];
        for(int i=0;i<index;i++){
            temp[i] = arr[i];
        }
        arr = temp;
        size = capacity;
    }
    // construct an empty randomized queue
    public RandomizedQueue(){
        size = 2;
        arr = (Item[])new Object[size];
        index = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return index == 0;
    }

    // return the number of items on the randomized queue
    public int size(){
        return index;
    }

    // add the item
    public void enqueue(Item item){
        if(item == null){
            throw new IllegalArgumentException();
        }
        
        if(index == size){
            resizeArray(2 * size);
        }
        arr[index++] = item;
    }

    // remove and return a random item
    public Item dequeue(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        int indexToRemove = StdRandom.uniform(0,index);
        Item ss = arr[indexToRemove];
        swap(indexToRemove,index-1);
        index--;
        resizeArray(index);
        // if last index is quarter of total size of array then shrink the array by half
        if(index == (size/4) && index > 0){
            resizeArray(size/2);
        }
        return ss;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        int indexToRemove = StdRandom.uniform(0,index);
        Item ss = arr[indexToRemove];
        return ss;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new MyIterator();
    }

    private class MyIterator implements Iterator<Item>{

        private int[] temp;
        private int tempindex;


        MyIterator(){
            temp = new int[index];
            tempindex = 0;
            for (int i=0; i<index; i++) {
                temp[i] = i;
            }
            StdRandom.shuffle(temp);
        }


        public Item next(){

            if(!hasNext()){
                throw new NoSuchElementException();
            }
            return arr[temp[tempindex++]];

        }
        public boolean hasNext(){
            return tempindex != index;
        }
        public void remove(){
            throw new UnsupportedOperationException();
        }

    }

    // unit testing (required)
    public static void main(String[] args){
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        Iterator<Integer> iterator = queue.iterator();
        Iterator<Integer> iterator2 = queue.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next() + " " + iterator2.next());
        }
        for (int i = 0; i < 10; i++) {
            queue.dequeue();
        }
        System.out.println(queue.size());


        System.out.println("Success");
    }

}