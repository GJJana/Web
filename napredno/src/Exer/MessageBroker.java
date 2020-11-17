package Exer;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class MessageBroker {
    private LocalDateTime dateTime;
    private Integer capacityPerTopic;
    private Set<Topic> setTopic;
    MessageBroker(LocalDateTime minimumDate, Integer capacityPerTopic){
        this.setTopic=new TreeSet<>();
        this.dateTime=minimumDate;
        this.capacityPerTopic=capacityPerTopic;
    }
    void addTopic (String topic, int partitionsCount){
        if(partitionsCount<=this.capacityPerTopic)
            this.setTopic.add(new Topic(topic,partitionsCount,capacityPerTopic,dateTime));
    }
    void addMessage (String topic, Message message){
        this.setTopic.stream().filter(x->x.getNameTopic().equals(topic)).forEach(k-> {
            try {
                k.addMessage(message);
            } catch (PartitionDoesNotExistException e) {
                e.printStackTrace();
            }
        });

    }
    void changeTopicSettings (String topic, int partitionsCount){

        if(partitionsCount<=this.capacityPerTopic){
        this.setTopic.forEach(k->{
            if(k.getNameTopic().equals(topic))
            {
                k.setPartitionCount(partitionsCount);
            }
        });}
    }

    @Override
    public String toString() {
        StringBuilder s=new StringBuilder().append(String.format("Broker with %d topics:\n",this.setTopic.size()));
       this.setTopic.forEach(x->s.append(x.toString()));
       return s.toString();
    }

    public static void main(String[] args) throws PartitionDoesNotExistException{
        Scanner sc = new Scanner(System.in);

        String date = sc.nextLine();
        LocalDateTime localDateTime =LocalDateTime.parse(date);
        Integer partitionsLimit = Integer.parseInt(sc.nextLine());
        MessageBroker broker = new MessageBroker(localDateTime, partitionsLimit);
        int topicsCount = Integer.parseInt(sc.nextLine());

        //Adding topics
        for (int i=0;i<topicsCount;i++) {
            String line = sc.nextLine();
            String [] parts = line.split(";");
            String topicName = parts[0];
            int partitionsCount = Integer.parseInt(parts[1]);
            broker.addTopic(topicName, partitionsCount);
        }

        //Reading messages
        int messagesCount = Integer.parseInt(sc.nextLine());

        System.out.println("===ADDING MESSAGES TO TOPICS===");
        for (int i=0;i<messagesCount;i++) {
            String line = sc.nextLine();
            String [] parts = line.split(";");
            String topic = parts[0];
            LocalDateTime timestamp = LocalDateTime.parse(parts[1]);
            String message = parts[2];
            if (parts.length==4) {
                String key = parts[3];
                try {
                    broker.addMessage(topic, new Message(timestamp,message,key));
                } catch (UnsupportedOperationException e) {
                    System.out.println(e.getMessage());
                }
            }
            else {
                Integer partition = Integer.parseInt(parts[3]);
                String key = parts[4];
                try {
                    broker.addMessage(topic, new Message(timestamp,message,partition,key));
                } catch (UnsupportedOperationException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        System.out.println("===BROKER STATE AFTER ADDITION OF MESSAGES===");
        System.out.println(broker);

        System.out.println("===CHANGE OF TOPICS CONFIGURATION===");
        //topics changes
        int changesCount = Integer.parseInt(sc.nextLine());
        for (int i=0;i<changesCount;i++){
            String line = sc.nextLine();
            String [] parts = line.split(";");
            String topicName = parts[0];
            Integer partitions = Integer.parseInt(parts[1]);
            try {
                broker.changeTopicSettings(topicName, partitions);
            } catch (UnsupportedOperationException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("===ADDING NEW MESSAGES TO TOPICS===");
        messagesCount = Integer.parseInt(sc.nextLine());
        for (int i=0;i<messagesCount;i++) {
            String line = sc.nextLine();
            String [] parts = line.split(";");
            String topic = parts[0];
            LocalDateTime timestamp = LocalDateTime.parse(parts[1]);
            String message = parts[2];
            if (parts.length==4) {
                String key = parts[3];
                try {
                    broker.addMessage(topic, new Message(timestamp,message,key));
                } catch (UnsupportedOperationException e) {
                    System.out.println(e.getMessage());
                }
            }
            else {
                Integer partition = Integer.parseInt(parts[3]);
                String key = parts[4];
                try {
                    broker.addMessage(topic, new Message(timestamp,message,partition,key));
                } catch (UnsupportedOperationException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        System.out.println("===BROKER STATE AFTER CONFIGURATION CHANGE===");
        System.out.println(broker);


    }
}
