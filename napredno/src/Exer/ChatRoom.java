package Exer;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class ChatRoom implements Comparable<ChatRoom> {
    private String name;
    private Set<String> usernames;
    ChatRoom(String name){
        this.name=name;
        this.usernames=new TreeSet<>();
    }
    public void addUser(String username){
        this.usernames.add(username);
    }
    public void removeUser(String username){
        this.usernames.remove(username);
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append(this.name).append("\n");
        if(this.usernames.size()==0)
            return sb.append("EMPTY\n").toString();
        this.usernames.forEach(x->sb.append(x).append("\n"));
        return sb.toString();
    }
    public boolean hasUser(String username){
        return this.usernames.contains(username);
    }
    public int numUsers(){
        return this.usernames.size();
    }

    public String getName() {
        return name;
    }

    public Set<String> getUsernames() {
        return usernames;
    }

    @Override
    public int compareTo(ChatRoom o) {
        return this.name.compareTo(o.name);
    }

}
