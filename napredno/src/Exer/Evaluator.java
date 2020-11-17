package Exer;
import java.util.Scanner;

public  class Evaluator<T extends Comparable<T>> {

    public static<T extends Comparable<T>> boolean evaluateExpression(T left, T right, String operator){
        IEvaluator <T>el=EvaluatorBuilder.build(operator);
        return el.evaluate(left,right);
    }
}
