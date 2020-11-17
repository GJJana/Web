
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Canvas {

    Map<String, Set<Shape>> idShapes;

    void idKorisnikCheck(String id) throws InvalidIDException{
        if(id.length()!=6|| !Stream.of(id).allMatch(x->Character.isLetter(x.charAt(0))||Character.isDigit(x.charAt(0))))
            throw new InvalidIDException(id);
    }

    void readShapes (InputStream is) throws IOException ,InvalidDimensionException,InvalidIDException{
        BufferedReader br=new BufferedReader(new InputStreamReader(is));
        String s;
        while((s=br.readLine())!=null){
            //id formata id korisnik dimenzii
            //String [] parts=s.split(" ");

            List<String>parts=Arrays.asList(s.split(" "));

            try{
                idKorisnikCheck(parts.get(0));
            }catch ( InvalidIDException e){
                e.getMessage();
                continue;
            }

            idShapes.putIfAbsent(parts.get(1),new TreeSet<Shape>(new PlostinaCom()));
            if(Integer.parseInt(parts.get(0))==1){
                //krug
                idShapes.get(parts.get(1)).add(new Krug(Double.parseDouble(parts.get(2))));

            }else {
                if(Integer.parseInt(parts.get(0))==2){
                    //kvadrat
                    idShapes.get(parts.get(1)).add(new Kvadrat(Double.parseDouble(parts.get(2))));

                }
                else   idShapes.get(parts.get(1)).add(new Pravoagolnik(Double.parseDouble(parts.get(2)),(Double.parseDouble(parts.get(3)))));
            }

        }



    }
    void scaleShapes (String userID, double coef){
            idShapes.get(userID).forEach(x->x.scale(coef));
    }
    void printAllShapes (OutputStream os) throws IOException {
            Set<Shape> tmp=new TreeSet<>(new PlostinaCom());
            this.idShapes.values().forEach(tmp::addAll);
            tmp.forEach(x-> {
                try {
                    os.write(x.toString().getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            os.flush();
    }

    void printByUserId (OutputStream os) {

        Set<Shape> tmp = new TreeSet<>(new PlostinaCom());
        this.idShapes.values().forEach(tmp::addAll);
        Map<String, Integer> t = new TreeMap<String, Integer>();
        idShapes.forEach((k, v) -> {
            t.put(k, v.size());
        });


        // t=t.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(e1,e2)->e1,LinkedHashMap::new);

        // idShapes.keySet().stream().sorted(x->idShapes.get(x).size()).forEach();
        t.keySet().forEach(k -> {
            idShapes.get(k).forEach(l -> {
                try {
                    os.write(l.toString().getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });

    }

    void statistics (OutputStream os){
        idShapes.values().forEach(s->{
            try {
                os.write(s.stream().mapToDouble(l->l.plostina()).summaryStatistics().toString().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) throws IOException,InvalidDimensionException,InvalidIDException{
        Canvas canvas = new Canvas();

        System.out.println("READ SHAPES AND EXCEPTIONS TESTING");
        canvas.readShapes(System.in);

        System.out.println("BEFORE SCALING");
        canvas.printAllShapes(System.out);
        canvas.scaleShapes("123456", 1.5);
        System.out.println("AFTER SCALING");
        canvas.printAllShapes(System.out);

        System.out.println("PRINT BY USER ID TESTING");
        canvas.printByUserId(System.out);

        System.out.println("PRINT STATISTICS");
        canvas.statistics(System.out);
    }

}
