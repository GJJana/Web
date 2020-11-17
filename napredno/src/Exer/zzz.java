package Exer;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class zzz {
    ArrayList<Integer> brojki;
    public zzz() {
        ArrayList<Integer> brojki = new ArrayList<Integer>();
    }
    public static void main(String[] args) {
        zzz obj=new zzz();
        int [][]matrica=new int[2][2];
        for (int i =0;i<8;i++){
            obj.brojki.add(i);
        }
        matrica[0][1]=8;
        Iterator<Integer> it=obj.brojki.iterator();
        while(it.hasNext())
        {
            if (it.next()==2)
            {
                it.remove();
            }
        }

    }
}
