package Exer;

import java.time.LocalDateTime;

public interface ILocation{
    double getLongitude();

    double getLatitude();

    LocalDateTime getTimestamp();
}