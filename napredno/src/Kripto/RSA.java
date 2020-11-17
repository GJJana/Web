package Kripto;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public  class RSA{
    private BigInteger one= new BigInteger("1");
    private BigInteger p;
    private BigInteger q;
    private BigInteger d;
    private BigInteger e;
    private BigInteger n;

    RSA(int N){
        p= new BigInteger("8510550132782927263006728934210669124536246404129419529410663803190697945762815370336070516551971859096176632195451815053505156612121303991047804593516967"

        );
        q=new BigInteger("10813255079903187995362471945742370564965102709177105698534554815737392517160245161827037279142355028467804985008599209566109661534050050462081176133294799");
        BigInteger P=(p.subtract(one)).multiply((q.subtract(one)));
        n=p.multiply(q);
        e=new BigInteger("65537");
        d=e.modInverse(P);

    }

    BigInteger encrypt(BigInteger message){
        return message.modPow(e,n);
    }
    BigInteger decrypt(BigInteger message){
        return message.modPow(d,n);
    }
    public String toString(){
        String tmp="";
        tmp+="p:"+p+"\n";
        tmp+="q:"+q+"\n";
        tmp+="e:"+e+"\n";
        tmp+="d(e^-1):"+d+"\n";
        tmp+="n(p*q):"+n+"\n";
        return tmp;
    }

    public static void main(String[] args) {
        int N=1024;
        RSA key=new RSA(N);
        System.out.println(key);
        //enkripcija
        String tmp="Two things are infinite: the universe and human stupidity; and I'm not sure about the universe.";
        byte[]bytes=tmp.getBytes();
        BigInteger mess=new BigInteger(bytes);
        BigInteger enkripcija=key.encrypt(mess);
        BigInteger dekripcija=key.decrypt(enkripcija);
        Charset charset= StandardCharsets.US_ASCII;

        //dekriptitana poraka vo tekst
        String d=charset.decode(ByteBuffer.wrap(dekripcija.toByteArray())).toString();
        System.out.println("dekriptirana "+d);

    }
}