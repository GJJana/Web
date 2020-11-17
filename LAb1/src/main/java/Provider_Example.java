import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

import java.security.*;
import java.util.Arrays;

class EncryptedFrame{
    byte[]header;
    byte[]data;
    byte[]MIC;
    IvParameterSpec ivParameterSpec;
    public void setHeader(byte[] header) {
        this.header = header;
    }
    public void setData(byte[] data) {
        this.data = data;
    }
    public void setMIC(byte[] mIC) {
        MIC = mIC;
    }
    public EncryptedFrame(byte[] header, byte[] data, byte[] mIC, IvParameterSpec ivParameterSpec) {
        super();
        this.header = header;
        this.data = data;
        MIC = mIC;
        this.ivParameterSpec = ivParameterSpec;
    }

    public void print() {
        System.out.println(new String(Base64.encode(header)));
        System.out.println("Payload: "+new String(Base64.encode(data)));
        System.out.println("MIC: "+new String(Base64.encode(MIC)));
    }
}

class ClearTextFrame{
    byte[]header;
    byte[] data;
    byte[]MIC;
    byte[] IV=new byte[16];
    IvParameterSpec ivParameterSpec;
    public void setMIC(byte[] MIC) {
        this.MIC = MIC;
    }
    public byte[] getHeader() {
        return header;
    }
    public byte[] getData() {
        return data;
    }
    public ClearTextFrame(byte[] header,byte[] data)
    {
        this.header=header;
        this.data=data;
    }
    public ClearTextFrame(byte[] source, byte[] destination, byte[] data)
    {
        //header=Stream.concat(Arrays.stream(source),Arrays.stream(destination)).toArray(byte::new);
        header=new byte[source.length+destination.length];
        System.arraycopy(source,0,header,0,source.length);
        System.arraycopy(destination,0,header,source.length,destination.length);
        this.data=data;
        this.MIC= new byte[8];
    }
    public IvParameterSpec IV_Random()
    {
        SecureRandom srandom = new SecureRandom();
        srandom.nextBytes(IV);
        return new IvParameterSpec(IV);
    }
    public void print() {
        System.out.println(new String (Base64.encode(header)));
        System.out.println("Payload: "+new String(Base64.encode(data)));
        System.out.println("MIC: "+new String(Base64.encode(MIC)));

    }
}

public class Provider_Example{
    public static EncryptedFrame encryptFrame(ClearTextFrame frame, String myKey) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance("AES/CTR/PKCS5Padding");
        byte[] key = myKey.getBytes("UTF-8");
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        key = md.digest(key);
        key = Arrays.copyOf(key, 16);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        //MIC
        //header e ist sekade
        //data se deli na delcinja po 128bita=16bytes
        //nov IV i IVspec
        byte[] IV=frame.IV;
        IvParameterSpec IVspec=frame.ivParameterSpec;
        //tmp za privremen rezultata od AES(k) XOR so clearTextFrame
        byte[] tmp=cipher.update(IV);
        //prvo XOR so source delot od frame sto e  bajti
        int i=0;
        //source samo prvite 16
        for(int j=0; j<16;j++)
        {
            tmp[i]=(byte) (frame.header[j]^tmp[i++]);
        }
        tmp=cipher.update(tmp);
        //destination samo prvite 16
        i=0;
        for(int j=17; j<33;j++)
        {
            tmp[i]=(byte) (frame.header[j]^tmp[i++]);
        }
        tmp=cipher.update(tmp);
        //data
        for(byte k :frame.data) {
            i = 0;
            for (int j = 0; j < 16; j++) {
                tmp[i] = (byte) (k ^ tmp[i++]);
            }
            if (k == frame.data[frame.data.length - 1]) {
                tmp = cipher.doFinal(tmp);
            } else {
                cipher.update(tmp);

            }
        }
        //samo prvite 8bajti za MIC
        byte[] MIC = Arrays.copyOfRange(tmp, 0, 8);
        frame.setMIC(MIC);
        //enkripcija
        cipher=Cipher.getInstance("AES/CTR/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec,IVspec);
        byte[] encMIC=cipher.doFinal(MIC);
        byte[] encDAta=new byte[frame.data.length];
        for(int j=0;j<frame.data.length;j+=16)
        {
            byte[] subArr=new byte[16];
            System.arraycopy(frame.data,j,subArr,0,16);
            byte[] tmp1=cipher.doFinal(subArr);
            System.arraycopy(tmp1,0,encDAta,j,16);

        }
        return new EncryptedFrame(frame.header,encDAta,encMIC,frame.ivParameterSpec);

    }
    public static ClearTextFrame decryptFrame(EncryptedFrame frame, String myKey) throws InvalidAlgorithmParameterException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES/CTR/PKCS5Padding");
        byte[] key = myKey.getBytes("UTF-8");
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        key = md.digest(key);
        key = Arrays.copyOf(key, 16);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec,frame.ivParameterSpec);
        byte[] MIC=cipher.doFinal(frame.MIC);
        byte[] data=new byte[frame.data.length];
        for(int j=0;j<frame.data.length;j+=16)
        {
            byte[] subArr=new byte[16];
            System.arraycopy(frame.data,j,subArr,0,16);
            byte[] tmp1=cipher.doFinal(subArr);
            System.arraycopy(tmp1,0,data,j,16);
        }
        ClearTextFrame ct= new ClearTextFrame(frame.header,frame.data);
        ct.setMIC(MIC);
        return ct;
    }

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeyException {
        Security.addProvider(new BouncyCastleProvider());
        String source="30-65-EC-6F-C4-58";
        String destination="01-23-45-67-89-AB";
        String new_data="kusamainformaciakusamainformacia";
        byte[] sourceMAC = source.getBytes("UTF-8");
        byte[] destinationMAC = destination.getBytes("UTF-8");
        byte[] data = new_data.getBytes("UTF-8");
        String key="jirlmnydlkniklin";
        ClearTextFrame plainText=new ClearTextFrame(sourceMAC,destinationMAC,data);
        EncryptedFrame encryptedFrame=encryptFrame(plainText,key);
        ClearTextFrame decryptedFrame=decryptFrame(encryptedFrame,key);
        System.out.println("Before encryption:");
        plainText.print();
        System.out.println("After encryption");
        encryptedFrame.print();
        System.out.println("After decryption");
        decryptedFrame.print();
    }

}