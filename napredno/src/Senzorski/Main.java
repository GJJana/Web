package Senzorski;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Main {




    public static void main(String[] args) throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("L");
        int L=Integer.parseInt(br.readLine());
        System.out.println("N");
        int N=Integer.parseInt(br.readLine());
        System.out.println("Procent anchor jazli");
        int anchor= (N/100)*Integer.parseInt(br.readLine());
        System.out.println("RR");
        int RR=Integer.parseInt(br.readLine());
        System.out.println("Err");
        int err=Integer.parseInt(br.readLine());

        List<Node> jazli=new LinkedList<Node>();
        List<NodeAnchor> anchorJazli=new LinkedList<NodeAnchor>();
        for(int i =0;i<N-anchor;i++)
        {
            Node tmp=new Node(i,L,RR,err);
            jazli.add(tmp);
        }
        for(int j=N-anchor;j<N;j++)
        {
            NodeAnchor tmp= new NodeAnchor(j,L,RR,err);
            anchorJazli.add(tmp);
        }
        //dodavanje na sosedi
        for(int i=0;i<jazli.size();i++)
        {
            for(int j=0;j<anchorJazli.size();j++)
            {
                jazli.get(i).addNeighbour(anchorJazli.get(j));
            }

        }


        //lokalizacija na jazli ne iterativno
        int Ale=0;
        int count=1;
        List<Node> tmp=jazli;
        List<NodeAnchor> tmp1=anchorJazli;
        for(Node n:jazli)
        {
            n.localization();
            if(n.isLoc()==true)
            {
                Ale+=n.getError();
                count++;
            }

        }
        System.out.println("Non-iterative: ALE: " + Ale/count + ", Localized nodes(%): " + (count*100)/tmp.size() + "%");
        //lokalizacija iterativno so najbliski 3
        Ale=0;
        count=1;
        List<Node> tmp2=tmp;
        List<NodeAnchor> tmp3=tmp1;
        for(Node n:tmp)
        {
            n.localization();
            if(n.isLoc()==true)
            {
                Ale+=n.getError();
                count++;
                NodeAnchor nov=new NodeAnchor(n);
                anchorJazli.add(nov);
                tmp.remove(tmp.indexOf(n));
                //update za nov sosed
                for(int i=0;i<tmp.indexOf(n);i++)
                {
                    tmp.get(i).addNeighbour(nov);
                }


            }
        }
        System.out.println("Iterative: ALE: " + Ale/count + ", Localized nodes(%): " + (count*100)/tmp2.size() + "%");

        //lokalizacija iterativno so najrelevantni 3





    }
}
