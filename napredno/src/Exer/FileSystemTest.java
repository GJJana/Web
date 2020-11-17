package Exer;

import java.io.File;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FileSystemTest {
    static class File implements Comparable<File>{
        String name;
        Integer size;
        LocalDateTime time;

        public File(String name, Integer size, LocalDateTime time) {
            this.name = name;
            this.size = size;
            this.time = time;
        }

        @Override
        public String toString() {
            return String.format("%-10%s %5d %T",name,size,time);
        }

        @Override
        public int compareTo(File o) {
            if(this.time.equals(o.time))
            {
                if(this.name.equals(o.name))
                {
                    if(this.size<o.size)
                        return -1;
                    else return 0;
                }
                else return this.name.compareTo(o.name);
            }
            else return this.time.compareTo(o.time);
        }
    }
   static  class FileSystem{
        Map<Character, Set<File>> map;

        public void addFile(char folder, String name, int size, LocalDateTime createdAt)
        {
            map.get(folder).add(new File(name,size,createdAt));
        }

       /* public List<File> findAllHiddenFilesWithSizeLessThen(int size)
        {
            //lista na site File sto pocnuvaat na . i se so golemina pomala od size
           List<File> tmp=new ArrayList<>();
           map.values().forEach(x->x.stream()
                   .filter(y->y.name.startsWith(".")&&y.size<size).forEach(z->tmp.add(z)));

           return tmp;
        }*/

        public int totalSizeOfFilesFromFolders(List<Character> folders){
            //vk golemina na dat koi se naogjaat vo folderite od listata
            List<Integer> files=new ArrayList<>();
            folders.stream()
                    .forEach(
                            x->map.get(x).stream()
                            .forEach(y->files.add(y.size))
                    );
            return files.stream().mapToInt(Integer::intValue).sum();

        }

        public Map<Integer, Set<File>> byYear() {
            //dat grupirani spored god
            Map<Integer, Set<File>> tmp = new HashMap<>();

            map.values().stream()
                    .forEach(x ->
                            x.stream().forEach(y -> {
                                if(tmp.containsKey(y.time.getYear()))
                                    tmp.get(y.time.getYear()).add(y);
                                else {Set<File> set=new HashSet<>();
                                    set.add(y);
                                    tmp.put(y.time.getYear(),set);
                                }
                            })
                    );

            return tmp;
        }
        public Map<String, Long> sizeByMonthAndDay() {
            //za sekoj mesec i den se presmetuva vk golemina na site dat vo toj mesec i den
            Map<String, Long> tmp = new HashMap<>();

            map.values().stream()
                    .forEach(x -> x.stream()
                            .forEach(y ->
                                    {
                                        StringBuilder sb = new StringBuilder();
                                        sb.append(y.time.getDayOfMonth()).append(y.time.getMonth());
                                        if (tmp.containsKey(sb.toString()))
                                            tmp.put(sb.toString(), tmp.get(sb.toString()) + y.size);
                                        else {

                                            tmp.put(sb.toString(), new Long(y.size));
                                        }
                                    }
                            ));

            return tmp;

        }


    }
    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            if(parts.length!=3)
                continue;
            fileSystem.addFile(parts[0].charAt(0), parts[1],
                    Integer.parseInt(parts[2]),
                    LocalDateTime.of(2016, 12, 29, 0, 0, 0).minusDays(Integer.parseInt(parts[3]))
            );
        }
        int action = scanner.nextInt();
        if (action == 0) {
            scanner.nextLine();
            int size = scanner.nextInt();
            System.out.println("== Find all hidden files with size less then " + size);
           // List<File> files = fileSystem.findAllHiddenFilesWithSizeLessThen(size);
            //files.forEach(System.out::println);
        } else if (action == 1) {
            scanner.nextLine();
            String[] parts = scanner.nextLine().split(":");
            System.out.println("== Total size of files from folders: " + Arrays.toString(parts));
            int totalSize = fileSystem.totalSizeOfFilesFromFolders(Arrays.stream(parts)
                    .map(s -> s.charAt(0))
                    .collect(Collectors.toList()));
            System.out.println(totalSize);
        } else if (action == 2) {
            System.out.println("== Files by year");
            Map<Integer, Set<File>> byYear = fileSystem.byYear();
            byYear.keySet().stream().sorted()
                    .forEach(key -> {
                        System.out.printf("Year: %d\n", key);
                        Set<File> files = byYear.get(key);
                        files.stream()
                                .sorted()
                                .forEach(System.out::println);
                    });
        } else if (action == 3) {
            System.out.println("== Size by month and day");
            Map<String, Long> byMonthAndDay = fileSystem.sizeByMonthAndDay();
            byMonthAndDay.keySet().stream().sorted()
                    .forEach(key -> System.out.printf("%s -> %d\n", key, byMonthAndDay.get(key)));
        }
        scanner.close();
    }


}
