package Exer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ResizableArray <T>{
    private T[] elements;
    private int capacity;

   public ResizableArray(T[]t){
       this.elements=t;
       this.capacity=t.length-1;
   }
    public ResizableArray() {
       new ResizableArray(this.elements.getClass(),capacity);
    }
    public ResizableArray(Class<T> t,int capacity){
        @SuppressWarnings("unchecked")
        final T[] e = (T[]) Array.newInstance(t, capacity);
        this.elements = e;

    }
    public void addElement(T element){
       //napravi nova i dodadi element

        T[] tmp=(T[]) new Object[capacity+2];
        System.out.println(capacity);
        tmp=this.elements;
        System.out.println(tmp.length);
        tmp[capacity]=element;

        this.capacity++;
    }
    public boolean removeElement(T element){
        for(int i=0;i<capacity;i++){
            if (element==this.elements[i]) {
                this.elements[i]=null;
                return true;
            }
        }
        return false;

    }
    public boolean contains(T element){
        for(int i=0;i<capacity;i++){
            if (element==this.elements[i])
                return true;
        }
        return false;
    }
    public Object[] toArray(){
        return this.elements;
    }
    public boolean isEmpty(){
        for(int i=0;i<capacity;i++){
            if (this.elements[i]!=null) {
                return false;
            }
        }
        return true;

    }
    public T elementAt(int idx)throws ArrayIndexOutOfBoundsException{
        if(idx<0||idx>=capacity)
            throw new ArrayIndexOutOfBoundsException();
        return this.elements[idx];
    }
    public static <T> void copyAll(ResizableArray<? super T> dest, ResizableArray<? extends T> src){
      //kopiranje na site elementi od src na dest (dodadeni)
       for(int i=0;i<=src.capacity;i++)
       {
           dest.addElement(src.elements[i]);
       }

    }

    public T[] getElements() {
        return elements;
    }

    public int getCapacity() {
        return capacity;
    }
    public int count(){
       return this.capacity;
    }


    ////TEST///
    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int test = jin.nextInt();
        if ( test == 0 ) { //test ResizableArray on ints
            ResizableArray<Integer> a = new ResizableArray<Integer>();
            System.out.println(a.count());
            int first = jin.nextInt();
            a.addElement(first);
            System.out.println(a.count());
            int last = first;
            while ( jin.hasNextInt() ) {
                last = jin.nextInt();
                a.addElement(last);
            }
            System.out.println(a.count());
            System.out.println(a.contains(first));
            System.out.println(a.contains(last));
            System.out.println(a.removeElement(first));
            System.out.println(a.contains(first));
            System.out.println(a.count());
        }
        if ( test == 1 ) { //test ResizableArray on strings
            ResizableArray<String> a = new ResizableArray<String>();
            System.out.println(a.count());
            String first = jin.next();
            a.addElement(first);
            System.out.println(a.count());
            String last = first;
            for ( int i = 0 ; i < 4 ; ++i ) {
                last = jin.next();
                a.addElement(last);
            }
            System.out.println(a.count());
            System.out.println(a.contains(first));
            System.out.println(a.contains(last));
            System.out.println(a.removeElement(first));
            System.out.println(a.contains(first));
            System.out.println(a.count());
            ResizableArray<String> b = new ResizableArray<String>();
            ResizableArray.copyAll(b, a);
            System.out.println(b.count());
            System.out.println(a.count());
            System.out.println(a.contains(first));
            System.out.println(a.contains(last));
            System.out.println(b.contains(first));
            System.out.println(b.contains(last));
            ResizableArray.copyAll(b, a);
            System.out.println(b.count());
            System.out.println(a.count());
            System.out.println(a.contains(first));
            System.out.println(a.contains(last));
            System.out.println(b.contains(first));
            System.out.println(b.contains(last));
            System.out.println(b.removeElement(first));
            System.out.println(b.contains(first));
            System.out.println(b.removeElement(first));
            System.out.println(b.contains(first));

            System.out.println(a.removeElement(first));
            ResizableArray.copyAll(b, a);
            System.out.println(b.count());
            System.out.println(a.count());
            System.out.println(a.contains(first));
            System.out.println(a.contains(last));
            System.out.println(b.contains(first));
            System.out.println(b.contains(last));
        }
        if ( test == 2 ) { //test IntegerArray
            IntegerArray a = new IntegerArray();
            System.out.println(a.isEmpty());
            while ( jin.hasNextInt() ) {
                a.addElement(jin.nextInt());
            }
            jin.next();
            System.out.println(a.sum());
            System.out.println(a.mean());
            System.out.println(a.countNonZero());
            System.out.println(a.count());
            IntegerArray b = a.distinct();
            System.out.println(b.sum());
            IntegerArray c = a.increment(5);
            System.out.println(c.sum());
            if ( a.sum() > 100 )
                ResizableArray.copyAll(a, a);
            else
                ResizableArray.copyAll(a, b);
            System.out.println(a.sum());
            System.out.println(a.removeElement(jin.nextInt()));
            System.out.println(a.sum());
            System.out.println(a.removeElement(jin.nextInt()));
            System.out.println(a.sum());
            System.out.println(a.removeElement(jin.nextInt()));
            System.out.println(a.sum());
            System.out.println(a.contains(jin.nextInt()));
            System.out.println(a.contains(jin.nextInt()));
        }
        if ( test == 3 ) { //test insanely large arrays
            LinkedList<ResizableArray<Integer>> resizable_arrays = new LinkedList<ResizableArray<Integer>>();
            for ( int w = 0 ; w < 500 ; ++w ) {
                ResizableArray<Integer> a = new ResizableArray<Integer>();
                int k =  2000;
                int t =  1000;
                for ( int i = 0 ; i < k ; ++i ) {
                    a.addElement(i);
                }

                a.removeElement(0);
                for ( int i = 0 ; i < t ; ++i ) {
                    a.removeElement(k-i-1);
                }
                resizable_arrays.add(a);
            }
            System.out.println("You implementation finished in less then 3 seconds, well done!");
        }
    }
}
