import java.time.LocalDateTime;

public class Element implements Comparable<Element> {

    private LocalDateTime timestamp;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public int compareTo(Element o) {
        return  o.getTimestamp().getNano()-this.getTimestamp().getNano();
    }
}
