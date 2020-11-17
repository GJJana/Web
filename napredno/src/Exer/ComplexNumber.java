package Exer;

public class ComplexNumber <T extends Number,U extends Number> implements Comparable<ComplexNumber<T,U>>{
    private T real;
    private U imaginary;

    public ComplexNumber(T real, U imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public T getReal() {
        return real;
    }

    public U getImaginary() {
        return imaginary;
    }

    public double modul(){

        return Math.sqrt(Math.pow(this.imaginary.doubleValue(),2)+Math.pow(this.real.doubleValue(),2));
    }
    @Override
    public int compareTo(ComplexNumber<T, U> o) {
        if(this.modul()>o.modul())
            return 1;
        if(this.modul()<o.modul())
            return -1;
        return 0;
    }

    @Override
    public String toString() {
        return String.format("%.2f%+.2fi",this.real.doubleValue(),this.imaginary.doubleValue());
    }
}
