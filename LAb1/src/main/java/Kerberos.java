


import org.bouncycastle.jcajce.provider.symmetric.ARC4;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.sql.Time;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
class AES{
         SecretKeySpec secretKey;
         byte[] key;

        public  void setKey(String myKey)
        {
            MessageDigest sha = null;
            try {
                key = myKey.getBytes("UTF-8");
                sha = MessageDigest.getInstance("SHA-1");
                key = sha.digest(key);
                key = Arrays.copyOf(key, 16);
                secretKey = new SecretKeySpec(key, "AES");
            }
            catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        public  String AES_encrypt(String strToEncrypt, String secret)
        {
            try
            {
                setKey(secret);
                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                return java.util.Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
            }
            catch (Exception e)
            {
                System.out.println("Error while encrypting: " + e.toString());
            }
            return null;
        }

        public  String AES_decrypt(String strToDecrypt, String secret)
        {
            try
            {
                setKey(secret);
                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
                cipher.init(Cipher.DECRYPT_MODE, secretKey);
                return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
            }
            catch (Exception e)
            {
                System.out.println("Error while decrypting: " + e.toString());
            }
            return null;
        }



}
class Alice{

    String IDa;
    String IDb;
    String Ka;
    byte[] nonceRa;

    public Alice(String IDa,String IDb,String key) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException {
        this.IDa=IDa;
        this.IDb=IDb;
        this.Ka=key;
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        this.nonceRa=Arrays.copyOf(md.digest(this.Ka.getBytes("UTF-8")),16);

    }

    public String verification(String yaYb) throws IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, UnsupportedEncodingException {
        System.out.println("-----Alice-----");
        String ya=yaYb.split(";")[0];
        //dekripcija na ya
        AES aestmp= new AES();
        System.out.println("Ka: "+Ka);
        String decText=aestmp.AES_decrypt(ya,Ka);
        System.out.println("Posle dekripcija Ya: "+decText);
        String Kses=decText.substring(0,16);
        System.out.println("Kses: "+Kses);
        //verify ra
        String ra=decText.substring(decText.length()-38,decText.length()-24);
        System.out.println("nonceRa:" +new String(nonceRa));
        System.out.println("nonce od Ya: "+ra);
        if (!ra.equals(new String(this.nonceRa)))
        {
            throw new InvalidParameterException("Nonce");
        }
        //verify IDb
        String Idb=decText.substring(decText.length()-16);
        System.out.println("IDb:" +IDb);
        System.out.println("IBb od Ya: "+Idb);
        if (!Idb.equals(this.IDb))
        {
            throw new InvalidParameterException("IDb");
        }
        String []T=decText.substring(decText.length()-24,decText.length()-16).split(":");
        Time time=new Time(Integer.parseInt(T[0]),Integer.parseInt(T[1]),Integer.parseInt(T[2]));
        Time Ts=new Time(System.currentTimeMillis());
        System.out.println("T od Ya: " +time.toString());
        System.out.println("Ts: "+Ts.toString());
        if(!Ts.after(time))
        {
            throw new InvalidParameterException("T");
        }
        //encrypt yab=ekses(IDa,Ts)
        String plainText=IDa+Ts.toString();
        String Yab=aestmp.AES_encrypt(plainText,Kses);
        System.out.println("Alice response : "+Yab+yaYb.split(";")[1]);
        return Yab+";"+yaYb.split(";")[1];
    }


}
class Bob{
    String IDb;
    String IDa;
    String Kb;

    public Bob(String IDb,String IDa,String kb) throws UnsupportedEncodingException {
        this.IDa=IDa;
        this.IDb=IDb;
        this.Kb=kb;
    }
    public String verification(String yabYb) throws IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, UnsupportedEncodingException {
        System.out.println("-----Bob-----");
        //Yb dekripcija
        String yb=yabYb.split(";")[1];
        AES aestmp=new AES();
        String decText=aestmp.AES_decrypt(yb,this.Kb);
        System.out.println("Posle dekripcija Yb: "+decText);
        //Kses
        String Kses=decText.substring(0,16);
        System.out.println("Kses: " + Kses);
        //yab dekripcija
        String yab=yabYb.split(";")[0];
        String decTextYab=aestmp.AES_decrypt(yab,Kses);
        System.out.println("Posle dekripcija Yab: "+decTextYab);
        //verify IDa
        String ida=decTextYab.substring(decTextYab.length()-24,decTextYab.length()-8);
        System.out.println("IDa: "+IDa);
        System.out.println("IDa od Yab: "+ida);
        if (!ida.equals(this.IDa))
        {
            throw new InvalidParameterException("IDa");
        }
        //verify lifetime T

        String []T=decText.substring(decText.length()-8).split(":");
        Time time=new Time(Integer.parseInt(T[0]),Integer.parseInt(T[1]),Integer.parseInt(T[2]));
        System.out.println("T: " +time.toString());
        Time current=new Time(System.currentTimeMillis());
        if(!current.after(time))
        {
            throw new InvalidParameterException("T");
        }
        //verify timestamp Ts
        String []T1=decTextYab.substring(decTextYab.length()-8).split(":");
        Time Ts=new Time(Integer.parseInt(T1[0]),Integer.parseInt(T1[1]),Integer.parseInt(T1[2]));
        System.out.println("Ts: " +Ts.toString());
        if(!current.after(Ts))
        {
            throw new InvalidParameterException("Ts");
        }
        return "validno";

    }

}
class KDC{
    String Ka;
    String Kb;
    public KDC(String ka, String kb)
    {
        this.Kb=kb;
        this.Ka=ka;
    }


    public String ticketGranting(String IDa, String IDb, byte[] ra) throws IllegalBlockSizeException, InvalidKeyException, NoSuchAlgorithmException, BadPaddingException, NoSuchPaddingException, UnsupportedEncodingException {
        System.out.println("-----KDC-----");
        //generate random Kses
        Random randomGen=new SecureRandom();
        byte[] Kses=new byte[16];
        randomGen.nextBytes(Kses);
        //generate lifetime T
        Time T=new Time(System.currentTimeMillis()+3600000*3);
        //ya encryption with Ka (Kses,ra,T,IDb)
        AES aes=new AES();
        StringBuilder sb= new StringBuilder();
        //kses 16
        //T 8
        //ra 14
        //id 16
        sb.append(new String(Kses)).append(new String(ra)).append(T.toString()).append(IDb);
        System.out.println("Ka: "+Ka);
        System.out.println("Pred enkripcija Ya: "+sb.toString());
        String ya=aes.AES_encrypt(sb.toString(),Ka);
        //yb encryption with Kb (kses ,IDa,T)
        sb=new StringBuilder();
        sb.append(new String(Kses)).append(IDa).append(T.toString());
        System.out.println("Kb: "+Kb);
        System.out.println("Pred enkripcija Yb "+sb.toString());
        String yb=aes.AES_encrypt(sb.toString(),Kb);
        System.out.println("KDC response: "+ya+yb);
        return ya+";"+yb;


    }


}



public class Kerberos {

    public static void main(String[] args) throws UnsupportedEncodingException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException {

        String ka="kriptografija123";
        String kb="bezbednostkluc12";
        String IDa="janajanajanajana";
        String IDb="bobobobobobobobo";
        Alice alice= new Alice(IDa,IDb,ka);
        Bob bob=new Bob(IDb,IDa,kb);
        KDC kdc=new KDC(ka,kb);
        //alice prakja request
        String response=kdc.ticketGranting(alice.IDa,alice.IDb,alice.nonceRa);
        //alice vreificira i enkriptira
        String aliceVer=alice.verification(response);
        //Bob verificira
        String bobVer=bob.verification(aliceVer);

    }


}
