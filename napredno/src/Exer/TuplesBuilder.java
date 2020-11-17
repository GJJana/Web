package Exer;

public class TuplesBuilder {


    public static Tuple2<String> buildStringsTuple (String str1, String str2) {
        return new Tuple2<String>() {
            @Override
            public String getFirst() {
                return str1;
            }

            @Override
            public String getSecond() {
                return str2;
            }

            @Override
            public int compareTo(Tuple2<String> o){
                if(this.getFirst().equals(o.getFirst()))
                    return this.getSecond().compareTo(o.getSecond());
                return this.getFirst().compareTo(o.getSecond());
            };
        };
    }

    public static Tuple2<Double> buildDoublesTuple (Double str1, Double str2){
        return new Tuple2<Double>() {
            @Override
            public Double getFirst() {
                return str1;
            }

            @Override
            public Double getSecond() {
                return str2;
            }
            @Override
            public int compareTo(Tuple2<Double> o){
                if(this.getFirst().equals(o.getFirst()))
                    return this.getSecond().compareTo(o.getSecond());
                return this.getFirst().compareTo(o.getSecond());
            };

        };
    }
    public static Tuple2<Integer> buildIntegersTuple (Integer str1, Integer str2){
        return new Tuple2<Integer>() {
            @Override
            public Integer getFirst() {
                return str1;
            }

            @Override
            public Integer getSecond() {
                return str2;
            }
            @Override
            public int compareTo(Tuple2<Integer> o){
                if(this.getFirst().equals(o.getFirst()))
                    return this.getSecond().compareTo(o.getSecond());
                return this.getFirst().compareTo(o.getSecond());
            };
        };
    }


}
