package Exer;

public class Candidate implements Comparable<Candidate> {
    public String code;
    public String name;
    public  int age;

    public Candidate(String code, String name, int age) {
        this.code = code;
        this.name = name;
        this.age = age;
    }


    @Override
    public String toString() {
        return "Candidate{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Candidate o) {
        if(this.name.equals(o.name)){
            if(this.age<o.age)
                return -1;
            if(this.age>o.age)
                return 1;
            return 0;
        }
        return this.name.compareTo(o.name);
    }

}
