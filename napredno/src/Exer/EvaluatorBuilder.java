package Exer;

public class EvaluatorBuilder<T> {

    public static IEvaluator build (String operator){

        return new IEvaluator() {
            @Override
            public boolean evaluate(Comparable a, Comparable b) {

                if(operator.equals("<"))
                    return a.compareTo(b)<0;
                if(operator.equals("=="))
                    return a.equals(b);
                if(operator.equals(">"))
                    return a.compareTo(b)>0;
                return !a.equals(b);

            }
        };
    }
}
