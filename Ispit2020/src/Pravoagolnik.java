public class Pravoagolnik implements Shape {

    private double dolzina;
    private double visina;

    public Pravoagolnik(double dolzina, double visina) throws InvalidDimensionException {
        if(dolzina==0||visina==0)
            throw new InvalidDimensionException("Dimenzija nula");
        this.dolzina = dolzina;
        this.visina = visina;
    }

    @Override
    public double perimetar() {
        return 2*dolzina+2*visina;
    }

    @Override
    public double plostina() {
        return (dolzina*visina)/2;
    }

    @Override
    public void scale(double coef) {
        this.dolzina*=coef;
        this.visina*=coef;
    }

    @Override
    public String toString() {
        return "Rectangle -> Sides: "+ dolzina +
                "," + visina +"Area:"+plostina()+"Perimeter:"+perimetar();

    }
}
