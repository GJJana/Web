package Exer;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class StopCoronaApp {

    private Map<String,User> users=new HashMap<>();
    private Set<User> nositeli=new TreeSet<>(new NositeliCom());

    public void addUser(String name, String id)throws UserIdAlreadyExistsException{
       if(users.containsKey(id))
           throw new UserIdAlreadyExistsException(id);
       users.put(id,new User(name,id));
    }

    public boolean directLocation(ILocation l1,ILocation l2){
        return Math.abs(l1.getTimestamp().getMinute()-l2.getTimestamp().getMinute())<5&&
                Math.sqrt(Math.pow(l1.getLongitude()-l2.getLongitude(),2)+Math.pow(l1.getLatitude()-l2.getLatitude(),2))<=2;
    }
    public void addLocations (String id, List<ILocation> iLocations){

        User g=this.users.get(id);
        this.users.get(id).addLocations(iLocations);
        //proveri so koj ostvaril blizok kontakt
        this.users.forEach((key,val)->{ iLocations.forEach(l->{
                 if(!key.equals(id)){
                    //so sekoja od lokaciite na userot da proveri dali se bliski
                    if(val.getLocations().stream().anyMatch(l1 -> directLocation(l, l1))){
                        int x=(int)val.getLocations().stream().filter(l1 -> directLocation(l, l1)).count();
                        val.addDirectContact(g);
                        val.updateContact(g,x);
                        this.users.get(id).addDirectContact(val);
                        this.users.get(id).updateContact(val,x);
                    }
                 }
        }); });
    }
    void detectNewCase (String id, LocalDateTime timestamp){

        this.users.get(id).setTimestamp(timestamp);
        this.users.get(id).sortNositelContact();
        this.nositeli.add(this.users.get(id));


    }

    public  Map<User, Integer> getDirectContacts (User u){
        return this.users.get(u.getId()).getDirectContacts();
    }

    public Collection<User> getIndirectContacts (User u){
        Set<User> indirectContact=new TreeSet<>(new IndirectContactCom());
        this.users.get(u.getId()).getDirectContacts().keySet().forEach(m->{
            m.getDirectContacts().keySet().forEach(k->{
                if(!this.users.get(u.getId()).getDirectContacts().keySet().contains(k)&&!k.getId().equals(u.getId()))
                    indirectContact.add(k);
            });});




        return indirectContact;
    }

    public void createReport (){
        this.nositeli.forEach(u->{
            System.out.println(u.toString());
            System.out.println("Direct contacts:");
            u.getDirectContacts().forEach((k,v)-> System.out.println(k.getName()+" "+k.getId().substring(0,4)+"*** "+v));
            System.out.println("Count of direct contacts:"+ u.getDirectContacts().values().stream().mapToInt(Integer::intValue).sum());
            System.out.println("Indirect comtacts:");
            getIndirectContacts(u).forEach(i-> System.out.println(i.getName()+" "+i.getId().substring(0,4)+"***"));
            System.out.println("Count of indirect contacts: "+getIndirectContacts(u).size());

                }

        );
        System.out.print("Average direct contacts: ");
        nositeli.stream().mapToInt(l->(int)l.getDirectContacts().values().stream().mapToInt(Integer::intValue).sum()).average().ifPresent(System.out::print);
        System.out.print("\nAverage indirect contacts: ");
        nositeli.stream().mapToInt(l->getIndirectContacts(l).size()).average().ifPresent(System.out::print);

    }

}
