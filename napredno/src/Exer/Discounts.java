package Exer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Discounts {
        private List<Store> listStores=new ArrayList<>();
    public int readStores(InputStream inputStream) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(inputStream));
        String s;
        int i=0;
        while((s=br.readLine())!=null){
               i++;

               List<String> tmp= Arrays.asList(s.split(" "));


              // listStores.add(new Store(tmp.get(0),tmp.subList(1,tmp.size())).toString().s);
        }
        return i;
    }
    public List<Store> byAverageDiscount(){
        return listStores.stream().sorted(new AvgDiscountCom()).limit(3).collect(Collectors.toList());

    }
    public List<Store> byTotalDiscount(){
        return listStores.stream().sorted(new TotalDiscCom()).limit(3).collect(Collectors.toList());
    }
    public static void main(String[] args) throws IOException {

    }
}
