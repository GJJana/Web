package Exer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Partial exam II 2016/2017
 */
public class FootballTableTest {
    public static void main(String[] args) throws IOException {
        FootballTable table = new FootballTable();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.lines()
                .map(line -> line.split(";"))
                .forEach(parts -> table.addGame(parts[0], parts[1],
                        Integer.parseInt(parts[2]),
                        Integer.parseInt(parts[3])));
        reader.close();
        System.out.println("=== TABLE ===");
        System.out.printf("%-19s%5s%5s%5s%5s%5s\n", "Team", "P", "W", "D", "L", "PTS");
        table.printTable();
    }
}

// Your code here
class FootballTable {
    List<Tim> timovi = new ArrayList<Tim>();

    public void addGame(String homeTeam, String awayTeam, int homeGoals, int awayGoals) {


        int flag1 = 0, flag2 = 0;

        for (Tim t : timovi) {

            //ako postojat
            if (t.ime.equals(homeTeam)) {
                flag1 = 1;
                if (homeGoals > awayGoals)
                    t.natprevar(1);
                if (homeGoals == awayGoals)
                    t.natprevar(0);
            }
            if (t.ime.equals(awayTeam)) {
                flag2 = 1;
                if (homeGoals < awayGoals)
                    t.natprevar(1);
                if (homeGoals == awayGoals)
                    t.natprevar(0);
            }
        }
        //ako ne postojat
        if (flag1 == 0) {
            Tim tmp = new Tim(homeTeam);
            if (homeGoals > awayGoals)
                tmp.natprevar(1);
            if (homeGoals == awayGoals)
                tmp.natprevar(0);
            timovi.add(tmp);
        }
        if (flag2 == 0) {
            Tim tmp = new Tim(homeTeam);
            if (homeGoals < awayGoals)
                tmp.natprevar(1);
            if (homeGoals == awayGoals)
                tmp.natprevar(0);
            timovi.add(tmp);
        }
    }

        public void printTable() {
            //reden broj na timot vo tabelata vo opagjacki redosled
            //imeto 15 mesta poramneto
            //broj na odograni natprevari
            //broj na pobedi,nereseni i br na osvoeni bodovi  5 mesta poramneti od desno
            //poeni=pobedi x3+ nereseni x1
            timovi.stream()
                    .sorted()
                    .forEach(x -> System.out.printf("%-15s %5d %5d %5d %5d", x.ime, x.odigrani_natp, x.pobedi, x.nereseni));

        }
    }

    class Tim implements Comparable<Tim> {
        String ime;
        int odigrani_natp;
        int pobedi;
        int nereseni;
        int bodovi;

        public Tim(String ime) {
            this.ime = ime;
            odigrani_natp = 0;
            pobedi = 0;
            nereseni = 0;
        }

        public void natprevar(int flag) {
            odigrani_natp += 1;
            if (flag == 1) {
                pobedi += 1;
                bodovi += 3;
            }
            if (flag == 0) {
                nereseni += 1;
                bodovi += 1;
            }
        }


        @Override
        public int compareTo(Tim o) {
            if (this.ime.equals(o.ime))
                return 0;
            else return -1;
        }
    }
