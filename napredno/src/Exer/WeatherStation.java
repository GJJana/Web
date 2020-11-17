package Exer;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/*public class WeatherStationTest {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        int n = scanner.nextInt();
        scanner.nextLine();
        WeatherStation ws = new WeatherStation(n);
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("=====")) {
                break;
            }
            String[] parts = line.split(" ");
            float temp = Float.parseFloat(parts[0]);
            float wind = Float.parseFloat(parts[1]);
            float hum = Float.parseFloat(parts[2]);
            float vis = Float.parseFloat(parts[3]);
            line = scanner.nextLine();
            Date date = df.parse(line);
            ws.addMesurement(temp, wind, hum, vis, date);
        }
        String line = scanner.nextLine();
        Date from = df.parse(line);
        line = scanner.nextLine();
        Date to = df.parse(line);
        scanner.close();
        System.out.println(ws.total());
        try {
            ws.status(from, to);
        } catch (RuntimeException e) {
            System.out.println(e);
        }
    }
}*/

// vashiot kod ovde
public class WeatherStation{
    /*//cuva podatoci za za x denovi
    List<Measurements> stanica;
    public WeatherStation(int x)
    {

    }
    public void addMesurement(float tem,float w,float hum,float vis,Date date){
        stanica.add(new Measurements(tem,w,hum,vis,date));
    }
    public int total()
    {
        return stanica.size();
    }
    public void status(Date from,Date to )
    {
        //gi pecati site merenja koi se vo od-do ramki vo rastecki redosled
        float tmp=stanica.stream()
                .filter(x->x.date.after(from)||x.date.before(to))
                .sorted()
                .mapToDouble(Measurements::getTemp)
                .average()
                .orElseThrow(RuntimeException::new)

        System.out.println(tmp);

    }


}
class Measurements implements Comparable<Measurements> {
    float temp;
    float humidity;
    float wind;
    float vis;
    Date date;

    public Measurements(float tem,float w,float hum,float vis,Date date)
    {
        this.temp=tem;
        this.wind=w;
        this.humidity=hum;
        this.vis=vis;
        this.date=date;
    }

    public float getTemp()
    {
        return this.temp;
    }
    @Override
    public int compareTo(Measurements o) {

        if(o.date.before(this.date))
            return -1;
        else return 0;

    }*/
}