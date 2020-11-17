public class Kvadrat implements Shape {

    private double strana;

    public Kvadrat(double strana)throws InvalidDimensionException {
        if(strana==0)
            throw new InvalidDimensionException("Dimenzija nula");
        this.strana = strana;
    }

    @Override
    public double perimetar() {
        return 4*strana;
    }

    @Override
    public double plostina() {
        return strana*strana;
    }
    @Override
    public void scale(double coef) {
        this.strana*=coef;

    }
    @Override
    public String toString() {
        return "Square -> Side: "+ strana +"Area:"+plostina()+"Perimeter:"+perimetar();

    }
}
