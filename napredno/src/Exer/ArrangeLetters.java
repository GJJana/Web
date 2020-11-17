package Exer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class ArrangeLetters {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String input=br.readLine();
        //1-1000 char
        //char letter or space
        //one capital letter
        //no two spaces and trimed
        System.out.println(arrange(input));
    }

    public static String arrange(String in){
        //each word should begin with a capital letter
        //the lowercase letters should be arranges alphabetically
        // da odi do prazno maesto da go namesti i vrati
        StringBuilder sb=new StringBuilder();
        StringBuilder sb_konecen=new StringBuilder();
        for(int i =0;i<in.length();i++)
        {
            if(in.charAt(i)==' ')
            {
                //da se isprati vo nadvoresna f-ja i da se vrati vo konecen string
                sb_konecen.append(word_arr(sb.toString())).append(" ");
                sb=new StringBuilder();

            }
            else if(in.charAt(i)<='Z'&&in.charAt(i)>='A')
            {
                sb_konecen.append(in.charAt(i));
            }
            else sb.append(in.charAt(i));
        }
       return sb_konecen.append(word_arr(sb.toString())).toString();
    }
    public static  String word_arr(String word)
    {
        //System.out.println(word);
        //site se mali bukvi
        String  [] arr=word.split("");
         return Arrays.stream(arr)
                .sorted()
                .collect(joining());


    }
}
