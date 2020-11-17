package Senzorski;

public class NodeAnchor extends Node implements Comparable<NodeAnchor>{


    int tezina;
    public NodeAnchor(int ID, int L, int RR, int Err)
    {
        super(ID,  L, RR, Err);
        this.tezina=0;
    }

    public NodeAnchor(Node n) {
        super(n);
        this.setLoc(true);
        this.tezina=1;
    }
    public void updateTezina()
    {
        this.tezina++;
    }
    public  int compareTo(NodeAnchor o){
        if(o.tezina<this.tezina)
            return -1;
        if(o.tezina==this.tezina)
            return 0;
        return 1;
    }







}
