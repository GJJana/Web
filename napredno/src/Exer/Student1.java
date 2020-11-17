package Exer;

import java.util.List;

public class Student1 implements Comparable<Student1> {

    private String code;
    private List<Integer> oceni;
    private Double prosek;


    public Student1(String code, List<Integer> oceni) {
        this.code = code;
        this.oceni = oceni;
        this.prosek=Calprosek();

    }
    public int brOceni(int k){
        return (int)oceni.stream().filter(x->x==k).count();
    }

    public double Calprosek(){
        return oceni.stream().mapToInt(Integer::intValue).average().orElse((double )0);
    }

    @Override
    public int compareTo(Student1 o) {
        if(this.prosek.equals(o.prosek))
            return this.code.compareTo(o.code);
        return  o.prosek.compareTo(this.prosek);
    }

    @Override
    public String toString() {
        return String.format("%s %.2f",code,prosek);
    }
}
