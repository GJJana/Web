package Exer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ChatSystem {
    private Map<String, ChatRoom> rooms;
    private Set<String> register;
    public ChatSystem() {
        this.rooms=new TreeMap<>();
        this.register=new TreeSet<>();
    }

    public void addRoom(String roomName){

      this.rooms.putIfAbsent(roomName,new ChatRoom(roomName));
    }
    public void removeRoom(String roomName){
        this.rooms.remove(roomName);
    }

    public ChatRoom getRoom(String roomName)throws NoSuchRoomException{
        if(!this.rooms.containsKey(roomName))
            throw new NoSuchRoomException(roomName);
        return this.rooms.get(roomName);

    }

    public void register(String userName) throws NoSuchElementException{
        this.register.add(userName);
        this.rooms.values().stream().filter(x->x.compareTo(this.rooms.values().stream().min(new UsernameComparator()).orElseThrow(NoSuchElementException::new))==0)
               .findFirst().orElseThrow(NoSuchElementException::new).addUser(userName);

    }
    public void registerAndJoin(String userName, String roomName) throws NoSuchRoomException {
        this.register(userName);
        this.rooms.get(roomName).addUser(userName);

    }
    public void joinRoom(String userName, String roomName) throws NoSuchRoomException {
        //System.out.println("rooms");
        if(!this.rooms.containsKey(roomName))
            throw new NoSuchRoomException(roomName);
        this.rooms.get(roomName).addUser(userName);
    }
    public void leaveRoom(String username, String roomName) throws NoSuchRoomException,NoSuchUserException {
        if(!this.rooms.containsKey(roomName))
            throw new NoSuchRoomException(roomName);
        if(!this.rooms.get(roomName).getUsernames().contains(username))
            throw new NoSuchUserException(username);
        this.rooms.get(roomName).removeUser(username);
    }
    public void followFriend(String username, String friend_username) throws NoSuchUserException {
        if(!this.register.contains(friend_username))
            throw new NoSuchUserException(friend_username);
        this.rooms.forEach((k,v)->{
               if(v.getUsernames().contains(friend_username))
                   v.addUser(username);
        });
    }
    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchRoomException ,NoSuchElementException{

        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if ( k == 0 ) {
            ChatRoom cr = new ChatRoom(jin.next());
            int n = jin.nextInt();
            for ( int i = 0 ; i < n ; ++i ) {
                k = jin.nextInt();
                if ( k == 0 ) cr.addUser(jin.next());
                if ( k == 1 ) cr.removeUser(jin.next());
                if ( k == 2 ) System.out.println(cr.hasUser(jin.next()));
            }
            System.out.println("");
            System.out.println(cr.toString());
            n = jin.nextInt();
            if ( n == 0 ) return;
            ChatRoom cr2 = new ChatRoom(jin.next());
            for ( int i = 0 ; i < n ; ++i ) {
                k = jin.nextInt();
                if ( k == 0 ) cr2.addUser(jin.next());
                if ( k == 1 ) cr2.removeUser(jin.next());
                if ( k == 2 ) cr2.hasUser(jin.next());
            }
            System.out.println(cr2.toString());
        }
        if ( k == 1 ) {
            ChatSystem cs = new ChatSystem();
            Method mts[] = cs.getClass().getMethods();
            while ( true ) {
                String cmd = jin.next();
                if ( cmd.equals("stop") ) break;
                if ( cmd.equals("print") ) {
                    System.out.println(cs.getRoom(jin.next())+"\n");continue;
                }
                for ( Method m : mts ) {
                    if ( m.getName().equals(cmd) ) {
                        String params[] = new String[m.getParameterTypes().length];
                        for ( int i = 0 ; i < params.length ; ++i ) params[i] = jin.next();
                        m.invoke(cs,params);
                    }
                }
            }
        }
    }
}
