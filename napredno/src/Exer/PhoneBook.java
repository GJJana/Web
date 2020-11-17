package Exer;

import java.util.*;

public class PhoneBook{

    public HashMap<String, Set<Contact>> byNames;
    public HashMap<String,Set<Contact>> byNumbers;
    public PhoneBook(){
        byNumbers=new HashMap<>();
        byNames=new HashMap<>();
    }
    public void addContact(String name, String number) throws  DuplicateNumberException{
        if(byNumbers.containsKey(number))
            throw new  DuplicateNumberException("Duplicate number:"+number);
        byNumbers.putIfAbsent(number,new HashSet<>());
        byNumbers.get(number).add(new Contact(name,number));
        byNames.putIfAbsent(name,new HashSet<>());
        byNames.get(name).add(new Contact(name,number));
    }
   public void contactsByNumber(String number){
        byNumbers.keySet().stream().filter(x->x.contains(number)).forEach(x->byNumbers.get(x).forEach(System.out::println));

    }
    public void contactsByName(String name){
        byNames.get(name).forEach(System.out::println);
    }

    public static void main(String[] args) throws DuplicateNumberException {
        PhoneBook p=new PhoneBook();
        p.addContact("Jana","007");
        p.addContact("Jana","008");
        p.addContact("El","007");
        p.addContact("Kak","987");
        p.contactsByName("Kak");
        p.contactsByNumber("00");


    }

}
