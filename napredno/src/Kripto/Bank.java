package Kripto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Bank {

    private List<Account> accounts=new ArrayList<>();

    public Bank(Account a){
        this.accounts.add(a);
    }
    public double totalAssets(){
    // ја враќа сумата на состојбата на сите сметки
        return accounts.stream().mapToDouble(Account::getState).sum();
        /*double tmp=0;
        for(Account a:this.accounts)
        {
            tmp+=a.getState();
        }
        return tmp;*/

    }
    public void addAccount(Account a){
        this.accounts.add(a);
    }
    public void addInterest(){
        //кој го повикува методот addInterest на
        //сите сметки кои се подложни на камата
        /*for(Account a :accounts){
            if(a instanceof InterestBearingAccount)
                ((InterestBearingAccount) a).addInterest();
        }*/

        accounts.stream().forEach(a->{
                        if(a instanceof InterestBearingAccount) {
                             ((InterestBearingAccount) a).addInterest();
                        } });
    }

    public static void main(String[] args) {
      /*  Account a1=new InterestCheckingAccount("Jana",123,200);
        Account a2=new PlatinumCheckingAccount("Bobo", 232,100);
        Account a3=new NonInterestCheckingAccount("Elene",333,44.6);
        Bank b =new Bank(a1);
        b.addAccount(a2);
        b.addAccount(a3);
        System.out.println(b.totalAssets());
        b.addInterest();
        System.out.println(b.totalAssets());*/




        List <String> list=Arrays.asList("Dog","Turtle","Rabbit","Parrot","Cat","Fish","Mouse","Hamster","Cow","Duck","Shrimp","Pig","Goat","Crab","Deer","Bee","Sheep","Turkey","Dove","Chicken","Horse","Bird","Squirrel","Ox","Lion","Panda","Walrus","Otter","Kangaroo","Monkey","Cow","Koala","Mole","Elephant","Leopard","Hippopotamus","Giraffe","Fox","Coyote","Hedgehong","Camel","Starfish","Koala","Alligator","Owl","Tiger","Bear","Whale","Raccoon","Lion","Crocodile","Dolphin","Snake","Elk","Bat","Hare","Toad","Frog","Deer","Rat","Badger","Lizard","Reindeer","Insect");
        list=list.stream().distinct().collect(Collectors.toList());
        System.out.println(list);



    }

}
