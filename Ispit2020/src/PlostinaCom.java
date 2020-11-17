import java.util.Comparator;

public class PlostinaCom implements Comparator<Shape> {
    @Override
    public int compare(Shape o1, Shape o2) {
        return Double.compare(o1.plostina(), o2.plostina());
    }
}
