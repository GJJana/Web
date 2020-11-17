package Exer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Letters {

//each word should begin with a capital letter
//the lowercase letters should be arranged alphabetically

    //• every character will be a letter (’a’-’z’ ’A’-’Z’) or ’ ’ - space.
    //• each word in sentence will have exactly one capital letter
    //• there will be no two consecutive spaces, and no spaces at the beginning or end of
    //the string
    //• a word may consist of only one capital letter


    public static String wordArrange(String w){
        StringBuilder sb=new StringBuilder();
        char [] letters=w.toCharArray();
        //najdi ja golemata bukva i stavi ja prva
        List<Integer> listASCII=new ArrayList<>();
        for(int i=0;i<letters.length;i++){
            listASCII.add((int)letters[i]);
        }
        listASCII=listASCII.stream().sorted().collect(Collectors.toList());
        for(Integer j:listASCII){
            sb.append(((char)(int)j));
        }
    return sb.toString();


    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String sentence=br.readLine();
        //sentence will have between 1 and 1000 characters inclusive
        String words[]=sentence.split(" ");
        //sekoj zbor se mesti
        StringBuilder sb=new StringBuilder();
        for(int j=0;j<words.length;j++){
            words[j]=wordArrange(words[j]);
            //pocetnite bukvi
            sb.append(words[j].charAt(0));
        }
        String arr=wordArrange(sb.toString());
        //System.out.println(arr);
        sb=new StringBuilder();
        for(int i=0;i<arr.length();i++){
            for(int j=0;j<words.length;j++)
            {
                if(words[j]!=null&&words[j].charAt(0)==arr.charAt(i)){

                    sb.append(words[j]);
                    if(i!=arr.length()-1)
                        sb.append(" ");
                    else sb.append(".");
                    words[j]=null;

                    break;
                }
            }


        }
        System.out.println(sb.toString());






    }

}
