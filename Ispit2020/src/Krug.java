public class Krug implements Shape {
    private double radius;

    public Krug(double radius)throws InvalidDimensionException {
        if(radius==0)
            throw new InvalidDimensionException("Dimenzija nula");
        this.radius = radius;
    }

    @Override
    public double perimetar() {
        return 2*radius*3.14;
    }

    @Override
    public double plostina() {
        return radius*radius*3.14;
    }
    @Override
    public void scale(double coef) {
        this.radius*=coef;

    }
    @Override
    public String toString() {
        return "Circle -> Side: "+ radius +"Area:"+plostina()+"Perimeter:"+perimetar();

    }
}
