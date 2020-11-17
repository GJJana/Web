package Senzorski;

import com.sun.source.tree.Tree;

import java.util.*;
import java.util.stream.Collectors;

public class Node {
    private int ID;
    private int x;
    private int y;
    private boolean loc=false;//true ako e lokaliciran
    private double error=0;//greska pri lokalizacija real-predicted distance
    private int xprim=0;
    private int yprim=0;
    private int RR;
    private int Err;
    private TreeMap<NodeAnchor,Double> neighbours;

   public Node(Node n)
   {
       this.loc=n.isLoc();
       this.x=n.getX();
       this.y=n.getY();
       this.RR=n.RR;
       this.ID=n.ID;
       this.Err=n.Err;
       this.error=n.getError();
       this.neighbours=n.neighbours;
       this.xprim=n.xprim;
       this.yprim=n.yprim;
   }
    public Node(int ID, int L, int RR, int Err) {
        this.ID=ID;
        this.RR=RR;
        this.Err=Err;
        //vo daden range
        Random gen=new Random();
        x= gen.nextInt(L);
        y=gen.nextInt(L);
        this.neighbours=new TreeMap<NodeAnchor,Double>();
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setLoc(boolean loc) {
        this.loc = loc;
    }

    public void setError() {
        this.error = Math.sqrt(Math.abs(Math.pow(this.x-this.xprim,2)+Math.pow(this.y-this.yprim,2)));
    }

    public void setXprim(int xprim) {
        this.xprim = xprim;
    }

    public void setYprim(int yprim) {
        this.yprim = yprim;
    }

    public double distance(Node n1, NodeAnchor n2)
    {
        //so sum
         double dis=Math.sqrt(Math.abs(Math.pow(n1.x-n2.getX(),2)+Math.pow(n1.y-n2.getY(),2)));
         Random r=new Random();
         double rangeMin=dis-(dis/100*Err);
         double rangeMax=dis+(dis/100*Err);
         return rangeMin+(rangeMax-rangeMin)*r.nextDouble();
    }
    public void trilateration()
    {
        //prvite tri anchor jazli
        Set<Map.Entry<NodeAnchor,Double>> ent=this.neighbours.entrySet();
        Map.Entry<NodeAnchor,Double> [] entArr=ent.toArray(new Map.Entry[ent.size()]);
        NodeAnchor a1=entArr[0].getKey();
        double d1=entArr[0].getValue();
        NodeAnchor a2=entArr[1].getKey();
        double d2=entArr[1].getValue();
        NodeAnchor a3=entArr[2].getKey();
        double d3=entArr[2].getValue();

        double i1=a1.getX(),i2=a2.getX(),i3=a3.getX();
        double j1=a1.getY(),j2=a2.getY(),j3=a3.getY();
        double x,y;

        x = (((((2 * j3) - (2 * j2)) * (((d1 * d1) - (d2 * d2)) + ((i2 * i2) - (i1 * i1)) + ((j2 * j2) - (j1 * j1)))) - (((2 * j2) - (2 * j1)) * (((d2 * d2) - (d3 * d3)) + ((i3 * i3) - (i2 * i2)) + ((j3 * j3) - (j2 * j2))))) / ((((2 * i2) - (2 * i3)) * ((2 * j2) - (2 * j1))) - (((2 * i1) - (2 * i2)) * ((2 * j3) - (2 * j2)))));
        y = (((d1 * d1) - (d2 * d2)) + ((i2 * i2) - (i1 * i1)) + ((j2 * j2) - (j1 * j1)) + (x * ((2 * i1) - (2 * i2)))) / ((2 * j2) - (2 * j1));

        this.xprim=Integer.parseInt(String.valueOf(x));
        this.yprim=Integer.parseInt(String.valueOf(y));
    }
    public void localization(){

        //dokolku ima tri tocki koi se vo RR i se anchor
        if(neighbours.values().size()>=3)
        {
            this.loc=true;
            //funkcija za trilateracija
            sortedbyValue(this.neighbours);
            trilateration();
            //presmetaj greska
            setError();
        }

    }

    public void  sortedbyValue(TreeMap<NodeAnchor,Double> map)
    {
      Comparator<NodeAnchor> valueComparator=new Comparator<NodeAnchor>() {
          @Override
          public int compare(NodeAnchor o1, NodeAnchor o2) {
              int compare = map.get(o1).compareTo(map.get(o2));
              if (compare == 0)
                  return 1;
              else
                  return compare;
          }
      };
        this.neighbours.putAll(map);

    }

    public void addNeighbour(NodeAnchor n)
    {
        //se dodavaat samo sosedi koi se anchor i vo RR na jazelot
        double dis=distance(this,n);
        if(dis<=this.RR)
        {
            this.neighbours.put(n,dis);
        }

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isLoc() {
        return loc;
    }

    public double getError() {
        return error;
    }

    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                ", error=" + error +
                ", xprim=" + xprim +
                ", yprim=" + yprim +
                '}';
    }
}
