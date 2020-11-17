import java.util.Comparator;

public class ParametarCom implements Comparator<Shape> {

    @Override
    public int compare(Shape o1, Shape o2) {
        return Double.compare(o1.perimetar(), o2.perimetar());
    }
}
