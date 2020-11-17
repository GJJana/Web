package Kripto;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.math.BigInteger;
import java.util.Random;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;


public class Diffie_Hellman {






    public static String AES_encrypt(String strToEncrypt, BigInteger secret) throws InvalidKeyException,
            NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException {
        //prepare key
        SecretKeySpec aesKey=new SecretKeySpec(secret.toByteArray(),0,16,"AES");
        String aesKeyFuture=Base64.getEncoder().encodeToString(aesKey.getEncoded());
        Cipher aesCipher=Cipher.getInstance("AES/ECB/PKCS5Padding");

        aesCipher.init(Cipher.ENCRYPT_MODE, aesKey);
        byte[] clearText=strToEncrypt.getBytes();
        byte[] cipherText=aesCipher.doFinal(clearText);
        //recreate key
        byte[] askKey=Base64.getDecoder().decode(aesKeyFuture);
        SecretKey aesDKey=new SecretKeySpec(askKey,"AES");
        //decipher input
        aesCipher.init(Cipher.DECRYPT_MODE,aesDKey);
        byte[] deciphered=aesCipher.doFinal(cipherText);
        return new String(cipherText);
    }

    public static String AES_decrypt(String strToDecrypt, BigInteger secret) throws InvalidKeyException,
            BadPaddingException,
            IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException {
        SecretKeySpec aesKey=new SecretKeySpec(secret.toByteArray(),0,16,"AES");
        String aesKeyFuture=Base64.getEncoder().encodeToString(aesKey.getEncoded());
        Cipher aesCipher=Cipher.getInstance("AES/ECB/PKCS5Padding");

        aesCipher.init(Cipher.ENCRYPT_MODE, aesKey);
        byte[] clearText=strToDecrypt.getBytes();
        byte[] cipherText=aesCipher.doFinal(clearText);
        //recreate key
        byte[] askKey=Base64.getDecoder().decode(aesKeyFuture);
        SecretKey aesDKey=new SecretKeySpec(askKey,"AES");
        //decipher input
        aesCipher.init(Cipher.DECRYPT_MODE,aesDKey);
        byte[] deciphered=aesCipher.doFinal(cipherText);
        return new String(deciphered);
    }

    public static BigInteger getSHA(String input) throws NoSuchAlgorithmException
    {
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        byte[]bit_ar= md.digest(input.getBytes(StandardCharsets.UTF_8));
        return new BigInteger(1,bit_ar);
    }


    public static void main(String[] args) throws ArrayIndexOutOfBoundsException,NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, InvalidKeyException {

        //vospostavuvanje na protokolot
        Random r = new Random();
        BigInteger p = BigInteger.probablePrime(1024, r);
        BigInteger a = new BigInteger("15");
        System.out.println("javni parametri za DH modificiran protokol");
        System.out.println("p="+p);
        System.out.println("a="+a);
        BigInteger PrivateA=new BigInteger(p.bitLength()-2,new SecureRandom()).mod(p);
        BigInteger PrivateB=new BigInteger(p.bitLength()-2,new SecureRandom()).mod(p);
        //javnite klucevi za Alice i Bob se dobivaat na ist nacin i zaviato od odbranite
        // javni parametri i tajniot kluc
        BigInteger PublicA=a.modPow(PrivateA,p);
        BigInteger PublicB=a.modPow(PrivateB,p);
        //javen kluc na Alice
        System.out.println("Alice->Bob:"+PublicA);




        //DSA kaj bob
        //taen kluc
        BigInteger KeyBob=PublicA.modPow(PrivateB,p);
        //potpisuvanje
        BigInteger dsa_p=new BigInteger("22222391");
        BigInteger dsa_q=new BigInteger("22159");
        BigInteger dsa_a=new BigInteger("55");
        //pomosna c
        BigInteger dsa_c=new BigInteger(dsa_q.bitLength(),new SecureRandom()).mod(dsa_q);
        BigInteger dsa_b=dsa_a.modPow(dsa_c,dsa_p);
        System.out.println("javni parametri na Bob za DSA");
        System.out.println(dsa_p);
        System.out.println(dsa_q);
        System.out.println(dsa_a);
        System.out.println(dsa_b);
        //potpis na porakata a(y),a(x)
        BigInteger tmp=new BigInteger(dsa_q.bitLength(),new SecureRandom()).mod(dsa_q);
        BigInteger t=dsa_a.modPow(tmp,dsa_q).mod(dsa_q);
        String x=new String(PublicB.toString()+","+PublicA.toString());
        BigInteger s=getSHA(x).add(dsa_c.multiply(t)).multiply(tmp.modInverse(dsa_q)).mod(dsa_q);
        String poraka=new String(x+","+t+","+s);
        String e=AES_encrypt(poraka,KeyBob);
        System.out.println("Bob->Alice:"+PublicB);
        System.out.println(e);

        //po dobienata poraka Alice presmetuva kluc i ja dekriptira primenata poraka
        BigInteger KeyAlice=PublicB.modPow(PrivateA,p);

        String d=AES_decrypt(poraka,KeyAlice);
        System.out.println("Posle dekripcija od Alice:"+d);
        //verifikacija ALICE
        String[] parts=d.split(",");
        String x_del=new String(PublicB.toString()+","+PublicA.toString());
        BigInteger r_del;
        BigInteger s_del;
        if (parts.length>=4){
         r_del=new BigInteger(parts[2]);
         s_del=new BigInteger(parts[3]);}
        else{
            r_del=new BigInteger("k");
            s_del=new BigInteger("s");

        }
        BigInteger m=s_del.modInverse(dsa_q);
        BigInteger uA=m.multiply(getSHA(x_del).mod(dsa_q));
        BigInteger uB=m.multiply(r_del).mod(dsa_q);
        BigInteger ver=(dsa_a.modPow(uA,dsa_p).multiply(dsa_b.modPow(uB,dsa_p)).mod(dsa_p)).mod(dsa_q);
        System.out.println(ver.toString()+" "+r_del.toString());
        if(r_del.mod(dsa_q).equals(ver.mod(dsa_q)))
            System.out.println("Verificiran");




        //DSA za Alice

        dsa_p=new BigInteger("74391749");
        dsa_q=new BigInteger("18597937");
        dsa_a=new BigInteger("61");
        dsa_c=new BigInteger(dsa_q.bitLength(),new SecureRandom()).mod(dsa_q);
        dsa_b=dsa_a.modPow(dsa_c,dsa_p);
        System.out.println("javni parametri na Alice za DSA");
        System.out.println(dsa_p);
        System.out.println(dsa_q);
        System.out.println(dsa_a);
        System.out.println(dsa_b);
        //potpis na porakate a(x),a(y0)
        tmp=new BigInteger(dsa_q.bitLength(),new SecureRandom()).mod(dsa_q);
        t=dsa_a.modPow(tmp,dsa_q).mod(dsa_q);
        x=new String(PublicA.toString()+","+PublicB.toString());
        s=getSHA(x).add(dsa_c.multiply(t)).multiply(tmp.modInverse(dsa_q)).mod(dsa_q);
        poraka=new String(x+","+t+","+s);
        e=AES_encrypt(poraka,KeyAlice);
        System.out.println("Alice->Bob:"+PublicA);
        System.out.println(e);
        //Dekripcija BOB
        d=AES_decrypt(poraka,KeyBob);
        System.out.println("Posle dekripcija od Bob:"+d);
        //verifikacija BOB
        parts=d.split(",");
        x_del=new String(PublicA.toString()+","+PublicB.toString());
        if (parts.length>=4){
            r_del = new BigInteger(parts[2]);
            s_del = new BigInteger(parts[3]);
        }

        m=s_del.modInverse(dsa_q);
        uA=m.multiply(getSHA(x_del).mod(dsa_q));
        uB=m.multiply(r_del).mod(dsa_q);
        ver=(dsa_a.modPow(uA,dsa_p).multiply(dsa_b.modPow(uB,dsa_p)).mod(dsa_p)).mod(dsa_q);
        System.out.println(ver.toString()+" "+r_del.toString());
        if(r_del.mod(dsa_q).equals(ver.mod(dsa_q)))
            System.out.println("Verificiran");








    }





}
