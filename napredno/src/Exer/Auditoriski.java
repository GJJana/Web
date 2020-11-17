package Exer;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Auditoriski implements Comparable<Auditoriski> {
    //reprezentacija na datum broj na denovi od pocetok na broenje
    //• репрезентација на сите датуми од 1 Јануари 1800 до 31 Декември 2500
    //• одземање два датуми
    //• зголемување датум за даден број денови
    //• да споредува два датуми со помош на equals и compareTo
    private static final int START=1800;
    private static final int FINISH=2500;
    private int days;
    private static final int[]  daysInMonth={31,28,31,30,31,30,31,31,30,31,30,31};
    private static final int[]  daysInMonthLeap={31,29,31,30,31,30,31,31,30,31,30,31};
    private static  int [] daysTillJan1;
    private static int[] daysTillFirstOfMonth;



    public Auditoriski(int d,int m, int y){
        if(!isValid(d,m,y)){
            System.out.println("Nevaliden datum");
            return;
        }
        this.days=CalDays(d,m,y);
    }
    public Auditoriski(int days){
        if(days<0&&days>255675){
            System.out.println("Nevaliden datum");
            return;
        }
        this.days=days;
    }

    public int CalDays(int d, int m, int y){
        int tmp=d;
        //denovite od prethodnite meseci (index 0->m ) taa godina +d
        // za sekoja prethodna da vidi dali e leap ako e dodava 366 ako ne e dodava 365

        for(int i=m-2;i>=0;i--)
        {
            if(i==1 && isLeapYear(y))
                tmp+=1;
            tmp+=daysInMonth[i];
        }
        for(int j=y-1;j>=START;j--)
        {
            //System.out.println(j);
            if(isLeapYear(j))
            {

                tmp+=366;}
            else
                tmp+=365;
        }
        return tmp;

    }
    public boolean isValid(int d,int m, int y){
        if(isLeapYear(y)) {
            if ((y > FINISH || y < START || m > 12 || m < 1)||(d < 1 &&d>daysInMonthLeap[m-1]))
                return false;
            return true;
        }else{
            if ((y > FINISH || y < START || m > 12 || m < 1)||(d < 1 &&d>daysInMonth[m-1]))
                return false;
            return true;
        }
    }
    public boolean isLeapYear(int y)
    {
        return (y%400==0||(y%4==0&&y%100!=0));
    }
    public int subtract(Auditoriski obj){

        return this.days-obj.getDays();

    }
    public Auditoriski increment(int num){
        this.days+=num;
        return this;
    }

    public int getDays() {
        return days;
    }

    @Override
    public String toString() {
        int d=this.days;
        int y=START;
        int m=0;
        while(d>365){
            if(isLeapYear(y))
                d-=366;
            else
                d-=365;
            y++;

        }
        for(int i=0;i<12;i++){
            //ako e prestapna za feb
            if(isLeapYear(y)&&i==1){
                if(d-daysInMonthLeap[i]<=0) {
                    m=i+1;
                    break;
                }
                d-=daysInMonthLeap[i];
                continue;
            }
            if(d-daysInMonth[i]<=0) {
                m=i+1;
                break;
            }
            d-=daysInMonth[i];
        }
        return d+"/"+m+"/"+y;
    }


    public boolean equals(Auditoriski obj) {
        return obj.getDays()==this.days ;
    }


    public int compareTo(Auditoriski o) {
        int tmp;
        tmp=this.days-o.getDays();
        if(tmp<0)
            return -1;
        if(tmp>0)
            return 1;
        return tmp;
    }

    public static void main(String[] args) {
        Auditoriski sample = new Auditoriski(1, 10, 2012);
        System.out.println(sample.days);
        System.out.println(sample.subtract(new Auditoriski(1, 1, 2000)));
        System.out.println(sample);
        sample = new Auditoriski(1, 1, 1800);
        System.out.println(sample);
        sample = new Auditoriski(31, 12, 2500);
        System.out.println(sample.days);
        System.out.println(sample);
        sample = new Auditoriski(30, 11, 2012);
        System.out.println(sample);
        sample = sample.increment(100);
        System.out.println(sample);




    }





}
