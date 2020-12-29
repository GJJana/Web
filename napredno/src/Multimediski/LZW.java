package Multimediski;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class LZW {

    public Map<String, Integer> indexes;
    public List<Integer> code;

    public int generateIndex() {
        return indexes.size() + 1;
    }

    public LZW() {
        this.indexes = new HashMap<>();
        this.code = new ArrayList<>();
    }

    public void calculateIndex(String message) {
        //site znaci gi stava so pocetni indeksi
        List<String> tmp = Arrays.stream(message.split("")).distinct().collect(Collectors.toList());
        tmp.forEach(x -> this.indexes.put(x, tmp.indexOf(x) + 1));
        calculateCode(message);
        System.out.println("Indexes:");
        indexes.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(System.out::println);
        System.out.println("Code:");
        code.forEach(x -> System.out.print(x + " "));

    }

    public void calculateCode(String message) {
        if (message.length() == 0)
            return;

        for (int i = message.length(); i != 0; i--) {
            //ako postoi kako indeks se dodava vo kodot
            if (this.indexes.containsKey(message.substring(0, i))) {
                this.code.add(this.indexes.get(message.substring(0, i)));
                //ako ne  e kraj na string dodadi nov index sto e so plus eden karakter od porakata
                if (message.substring(i).length() != 0) {
                    this.indexes.putIfAbsent(message.substring(0, i + 1), generateIndex());
                    calculateCode(message.substring(i));
                    return;
                } else return;

            }

        }

    }
    public static void main(String[] args) throws IOException {
        LZW lzv = new LZW();
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        lzv.calculateIndex(br.readLine());
    }

}
