package Kripto;

import java.util.HashSet;
import java.util.LinkedList;
import java.lang.ArrayIndexOutOfBoundsException;

public class IntegerList {

    private LinkedList<Integer> list;


    public IntegerList(){
        this.list=new LinkedList<Integer>();
    }
    public IntegerList(Integer[] numbers){
        this.list=new LinkedList<>();
        for(int i=0;i<numbers.length;i++){
            this.list.add(numbers[i]);
        }
    }

     public void add(int el, int idx){
        this.list.add(idx,el);
     }
    public  int remove(int idx)throws ArrayIndexOutOfBoundsException{
        if(idx<0||idx>this.list.size())
            throw new ArrayIndexOutOfBoundsException();
        int tmp=this.list.get(idx);
        this.list.remove(idx);
        return tmp;
    }
    public void set(int el, int idx)throws ArrayIndexOutOfBoundsException{
        if(idx<0||idx>this.list.size())
            throw new ArrayIndexOutOfBoundsException();
        this.list.set(idx,el);
    }
    public  int get(int idx){
        return this.list.get(idx);
    }
   public int  size(){
        return this.list.size();
   }

    public int count(int el){
        int tmp=0;
        for(Integer i :this.list)
        {
            if(el==i)
                tmp++;
        }
        return tmp;
    }
    public void removeDuplicates(){
        HashSet<Integer> k=new HashSet<>(this.list);
        this.list=new LinkedList<>(k);

    }

    public int sumFirst(int k){
        int sum=0;
        for(int i=0;i<k;i++){
            sum+=this.list.get(i);
        }
        return sum;
    }
    public int sumLast(int k){
        int sum=0;
        for(int i=this.list.size()-1;i>=this.list.size()-k;i--){
            sum+=this.list.get(i);
        }
        return sum;
    }
    public void shiftRight(int idx, int k)throws ArrayIndexOutOfBoundsException{
        if(idx>this.list.size()||idx<0||k==this.list.size())
            throw new ArrayIndexOutOfBoundsException();
        if (k==0)
            return;
        LinkedList<Integer> tmp=new LinkedList<>();
        int el=this.list.get(idx);

        if(k>this.list.size())
            k=k-this.list.size();
        if(k>=this.list.size()-idx)
        {
            shiftLeft(idx,this.list.size()-idx-1);
            return;
        }

        for(int i=0;i<this.list.size();i++){
            if(i==idx)
            {
                //ako e na idx
                continue;
            }
            if(i==idx+k)
            {
                //na novoto mesto
                tmp.add(el);
                continue;
            }
            tmp.add(this.list.get(i));
        }
        this.list=tmp;
    }
    public void shiftLeft(int idx , int k) throws ArrayIndexOutOfBoundsException{
        if(idx>this.list.size()||idx<0||idx-k<0)
            throw new ArrayIndexOutOfBoundsException();
        LinkedList<Integer> tmp=new LinkedList<>();
        int el=this.list.get(idx);
        for(int i=0;i<this.list.size();i++)
        {
            if(i==idx-k)
            {
                //novo mesto
                tmp.add(el);
            }
        }

    }
    public  IntegerList addValue(int value){
        LinkedList<Integer>tmp=this.list;
        tmp.forEach(x->x+=value);
        return new IntegerList((Integer[]) tmp.toArray());
    }

}
