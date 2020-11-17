package Exer;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

 class RomanConverterTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        IntStream.range(0, n)
                .forEach(x -> System.out.println(RomanConverter.toRoman(scanner.nextInt())));
        scanner.close();
    }
}


class RomanConverter {
    /**
     * Roman to decimal converter
     *
     * @param n number in decimal format
     * @return string representation of the number in Roman numeral
     */
    public static String toRoman(int n) {
        // your solution here
        String str=new String();
        while(n!=0){
            if(n/1000!=0)
            {
              str+='M';
              n=n-1000;
              continue;
            }
            if(n/900!=0)
            {
                str+="CM";
                n=n-900;
                continue;
            }
            if(n/500!=0)
            {
                str+="D";
                n=n-500;
                continue;
            }
            if(n/400!=0)
            {
                str+="CD";
                n=n-400;
                continue;
            }
            if(n/100!=0)
            {
                str+='C';
                n=n-100;
                continue;
            }
            if(n/90!=0)
            {
                str+="XC";
                n=n-90;
                continue;
            }
            if(n/50!=0)
            {
                str+="L";
                n=n-50;
                continue;
            }
            if(n/40!=0)
            {
                str+="XL";
                n=n-40;
                continue;
            }

            if(n/10!=0)
            {
                str+='X';
                n=n-10;
                continue;
            }
            if(n/9!=0)
            {
                str+="IX";
                n=n-9;
                continue;
            }if(n/5!=0)
            {
                str+="V";
                n=n-5;
                continue;
            }
            if(n/4!=0)
            {
                str+="IV";
                n=n-4;
                continue;
            }
            str+='I';
            n--;
        }
        return str;
    }

}
