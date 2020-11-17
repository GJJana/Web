package Exer;

import java.util.HashSet;
import java.util.Random;

public class BirthdayParadox {



static final int NUM_TRIALS=5000;

    public static void main(String[] args) {


        Random r =new Random();


        for (int i=2;i<=50;i++){
            HashSet<Integer> rodendeni=new HashSet<>();
            int count=0;
            for(int k=0;k<NUM_TRIALS;k++){
            for(int j=0;j<i;j++) {

                int nov=r.nextInt(364)+1;
                if(!rodendeni.add(nov)) {
                    count++;
                    break;
                }
                else rodendeni.add(nov);

            }}
            System.out.format("For %d people, the probability of two birthdays is about %.5f\n",i,(count*0.1/NUM_TRIALS));
            }

        }

    }
