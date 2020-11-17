package Exer;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class User {

    private String name;
    private String id;
    private LocalDateTime timestamp;
    private Map<User,Integer> directContacts;
    private List<ILocation> locations;


    public User(String name, String id) {
        this.name = name;
        this.id = id;
        this.timestamp=null;
        this.directContacts=new HashMap<>();
        this.locations=new ArrayList<>();
    }

    public void addDirectContact(User u){
        this.directContacts.putIfAbsent(u,0);
        //updateContact(u,1);
    }
    public void updateContact(User u,int n){
        this.directContacts.computeIfPresent(u,(k,v)->v+=n);
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setDirectContacts(Map<User, Integer> directContacts) {
        this.directContacts = directContacts;
    }

    public void addLocations(List<ILocation> loc) {
        this.locations.addAll(loc);

    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Map<User, Integer> getDirectContacts() {
        return directContacts;
    }

    public List<ILocation> getLocations() {
        return locations;
    }

    public void sortNositelContact(){
       this.directContacts=this.directContacts.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    @Override
    public String toString() {
        return name +" "+ id + " " + timestamp ;

    }
}
