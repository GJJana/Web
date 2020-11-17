package Exer;

import java.time.LocalDateTime;
import java.util.Objects;

public class Timestamp<T> implements Comparable<Timestamp<T>> {
    private final LocalDateTime time;
    private final T element;

    public Timestamp(LocalDateTime time, T element) {
        this.time = time;
        this.element = element;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public T getElement() {
        return element;
    }

    @Override
    public int compareTo(Timestamp<T> o) {
        if(this.time.isAfter(o.time))
            return 1;
        if(this.time.isBefore(o.time))
            return -1;
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Timestamp)) return false;
        Timestamp<?> timestamp = (Timestamp<?>) o;
        return Objects.equals(time, timestamp.time);

    }

    @Override
    public int hashCode() {
        return Objects.hash(time, element);
    }

    @Override
    public String toString() {
        return
                 time+" "+
                 element;
    }
}
