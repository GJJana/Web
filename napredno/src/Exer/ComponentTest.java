package Exer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ComponentTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        Window window = new Window(name);
        Component prev = null;
        while (true) {
            try {
                int what = scanner.nextInt();
                scanner.nextLine();
                if (what == 0) {
                    int position = scanner.nextInt();
                    System.out.println(position);
                    window.addComponent(position, prev);
                } else if (what == 1) {
                    String color = scanner.nextLine();
                    int weight = scanner.nextInt();
                    Component component = new Component(color, weight);
                    prev = component;
                } else if (what == 2) {
                    String color = scanner.nextLine();
                    int weight = scanner.nextInt();
                    Component component = new Component(color, weight);
                    prev.addComponent(component);
                    prev = component;
                } else if (what == 3) {
                    String color = scanner.nextLine();
                    int weight = scanner.nextInt();
                    Component component = new Component(color, weight);
                    prev.addComponent(component);
                } else if(what == 4) {
                    break;
                }

            } catch (InvalidPositionException e) {
                System.out.println(e.getMessage());
            }
            scanner.nextLine();
        }

        System.out.println("=== ORIGINAL WINDOW ===");
        System.out.println(window);
        int weight = scanner.nextInt();
        scanner.nextLine();
        String color = scanner.nextLine();
        window.changeColor(weight, color);
        System.out.println(String.format("=== CHANGED COLOR (%d, %s) ===", weight, color));
        System.out.println(window);
        int pos1 = scanner.nextInt();
        int pos2 = scanner.nextInt();
        System.out.println(String.format("=== SWITCHED COMPONENTS %d <-> %d ===", pos1, pos2));
        window.swichComponents(pos1, pos2);
        System.out.println(window);
    }


   static  class Component implements Comparable<Component>{
        String color;
        int weight;
        List<Component> components;
        public Component(String color,int weight)
        {
            this.color=color;
            this.weight=weight;
            components=new ArrayList<>();
            System.out.println(color+weight);

        }

       @Override
       public String toString() {
           return "Component{" +
                   "color='" + color + '\'' +
                   ", weight=" + weight +
                   '}';
       }

       void addComponent(Component component){

            components.add(component);
            components.stream().sorted().collect(Collectors.toList());

        }

        @Override
        public int compareTo(Component o) {
            if(this.weight==o.weight)
                return this.color.compareTo(o.color);
            if(this.weight<o.weight)
                return -1;
            return 0;
        }
    }
   static  class Window{
        String name;
        List<Component> lista;
        public Window(String name)
        {
            this.name=name;
            lista=new ArrayList<>();
        }
        void addComponent (int postition,Component component)throws InvalidPositionException{
            if(lista.get(postition)!=null)
                throw new InvalidPositionException(postition);
            lista.add(postition,component);
            for(Component c:lista)
            System.out.println(c.toString());
        }

        @Override
        public String toString() {
            String tmp=new String();
            for(int i=0;i<lista.size();i++)
            {
                if(lista.get(i)!=null)
                {
                    tmp+=i+"\n"+lista.get(i).toString()+"\n";
                }
            }
            return tmp;
        }

        void changeColor(int weight,String color)
        {
            //ja menuva bojata na site komponenti so pomala tezina
            lista.stream().filter(x->x.weight<weight).map(x->x.color=color).collect(Collectors.toList());
        }
        void swichComponents(int pos1,int pos2){
            Component c=lista.get(pos1);
            lista.add(pos1,lista.get(pos2-1));
            lista.add(pos2,c);
        }
    }

   static class InvalidPositionException extends Exception{
        public InvalidPositionException(int i)
        {
            super("Invalid position "+i+",already taken");
        }
    }
}

// вашиот код овде