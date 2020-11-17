package Exer;

import java.time.LocalDateTime;

public class Message {
     private LocalDateTime timestamp;
     private String text;
     private Integer particiton;
     private String key;

    public Message(LocalDateTime timestamp,String text,String key) {
        this.timestamp = timestamp;
        this.text=text;
        this.key=key;
    }

    public Message(LocalDateTime timestamp, String text, Integer particiton, String key) {
        this.timestamp = timestamp;
        this.text = text;
        this.particiton = particiton;
        this.key = key;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getText() {
        return text;
    }

    public Integer getParticiton() {
        return particiton;
    }

    public String getKey() {
        return key;
    }

    @Override
    public String toString() {
        return "Message{" +
                "timestamp=" + timestamp +
                ", message='" + text + '\'' +
                '}';
    }

    public void setParticiton(Integer particiton) {
        this.particiton = particiton;
    }
}
