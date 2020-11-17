package Exer;

import java.time.LocalDateTime;
import java.util.*;

class PartitionAssigner {
    public static Integer assignPartition (Message message, int partitionsCount) {
        return (Math.abs(message.getKey().hashCode())  % partitionsCount) + 1;
    }
}
public class Topic {
    private String nameTopic;
    private Map<Integer, List<Message>> map;
    private int partitionCount;
    private int maxMess;
    private LocalDateTime startDate;

    public Topic(String topicName, int partitionsCount) {
        this.nameTopic = topicName;
        this.partitionCount = partitionsCount;
        this.map = new TreeMap<>();
        this.maxMess=0;
    }
    public Topic(String topicName, int partitionsCount,int maxM,LocalDateTime dateTime) {
        this.nameTopic = topicName;
        this.partitionCount = partitionsCount;
        this.map = new TreeMap<>();
        this.maxMess=maxM;
        this.startDate=dateTime;
    }


    public void addMessage(Message message) throws PartitionDoesNotExistException {
        if (!this.map.containsKey(message.getParticiton()))
            throw new PartitionDoesNotExistException(message.getParticiton().toString());
        if(map.keySet().size()+1<this.maxMess&&message.getTimestamp().isBefore(this.startDate))
        {   if(message.getParticiton()==null)
            message.setParticiton(PartitionAssigner.assignPartition(message,this.partitionCount));
            this.map.get(message.getParticiton()).add(message);}
    }

    public void changeNumberOfPartitions(int newPartitionsNumber) throws UnsupportedOperationException {
        if (this.partitionCount > newPartitionsNumber)
            throw new UnsupportedOperationException();
        this.partitionCount = newPartitionsNumber;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Topic: %10s Partitions: %5d\\n", this.nameTopic, this.partitionCount));
        this.map.forEach((k, v) -> {
            sb.append(String.format("%d : Count of messages: %d", k, v.size()));
            v.forEach(m -> sb.append(m.toString()).append("\n"));
        });
        return sb.toString();
    }

    public String getNameTopic() {
        return nameTopic;
    }

    public Map<Integer, List<Message>> getMap() {
        return map;
    }

    public int getPartitionCount() {
        return partitionCount;
    }

    public void setNameTopic(String nameTopic) {
        this.nameTopic = nameTopic;
    }

    public void setPartitionCount(int partitionCount) {
        this.partitionCount = partitionCount;
    }

    public int getMaxMess() {
        return maxMess;
    }
}