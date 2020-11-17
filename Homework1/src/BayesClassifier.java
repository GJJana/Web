
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class BayesClassifier {

    //funkcija za kreirane na zbor sostaven samo od mali bukvi i so ignoriranje na interpunkciski znaci
    private static List<String> signTrim1(String word) {
        List<String> tmp = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (Character c : word.toCharArray()) {
            if (Character.isLetterOrDigit(c) || c.equals('\'')) {
                sb.append(c);

            } else {
                if (sb.toString().isEmpty())
                    continue;
                tmp.add(sb.toString().toLowerCase());
                sb = new StringBuilder();
            }

        }
        return tmp;
    }

    //funkcija koja presmetuva aposteriorna verojatnost p(spam|word) ili p(ham|word) vo zavisnost koja verojatnost sakame da ja dobieme
    //p(word|spam) i p(word|ham) se presmetuvaat kako brojot na pojavuvanja na zborot vo site porakki od klasata spam/ham podeleno so vkupniot br na zborovi vo soodvetnata klasa

    private static float calculateProb(String word, List<String> list, List<String> list_no, float apriori_yes, float apriori_no) {
        //p(word|spam)*p(spam)ili ham dokolku se premsetuva za ham
        float broitel = ((float) list.stream().filter(x -> x.equals(word)).count() / list.size()) * apriori_yes;
        float imenitel = ((float) list.stream().filter(x -> x.equals(word)).count() / list.size()) * apriori_yes;
        if (list_no.contains(word)) {
            //p(word|ham)*p(ham) ili spam dokolku se presmetuva za ham
            imenitel += ((float) list_no.stream().filter(x -> x.equals(word)).count() / list_no.size()) * apriori_no;

        }
        //dokolku zborot se pojavuva povekje pati (vo poevkje poraki ) se mnozi verojatnosta so koeficient (vo slucajov 3) za da se zgoelmi tezinata na zborot (poverojaten vo odnos na drugite)
        if ((int) list.stream().filter(x -> x.equals(word)).count() > 2)
            return (broitel / imenitel) * 3;
        //vrakja p(spam|word)=p(word|spam)p(spam)/p(word|spam)p(spam)+p(word|ham)p(ham) ili ham ako se bara za ham mess
        return broitel / imenitel;
    }

    public static void main(String[] args) throws IOException {

        //pomosni podatocni strukturi
        List<String> spam_mess = new ArrayList<>();
        List<String> ham_mess = new ArrayList<>();
        List<String> spam_words = new ArrayList<>();
        List<String> ham_words = new ArrayList<>();
        Map<String, Float> Spam_map = new HashMap<>();
        Map<String, Float> Ham_map = new HashMap<>();
        List<String> stopWords = new ArrayList<>();

        //se cita lista od svrznici koi se koristat cesto za istite da ne se zemat vo predvid pri presmetuvanje na verojatnosta
        BufferedReader b = new BufferedReader(new FileReader("C:\\Users\\janag\\IdeaProjects\\Homework1\\src\\DataSets\\StopWords.txt"));
        String line;
        while ((line = b.readLine()) != null) {
            stopWords.add(line.trim());
        }
        //citanje od datoteka vo lokalen repozitorium so BufferedReader, linija po linija => trainnig data
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\janag\\IdeaProjects\\Homework1\\src\\DataSets\\SMSSpamTrain.txt"));

        int counter = 0;
        //citanje od file i kreiranje na lista
        //pomosni listi za br na poraki i zborovi vo sekoja od klasite
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\t");
            if (parts[0].equals("spam")) {
                spam_mess.add(parts[1]);
                Arrays.asList(parts[1].split(" ")).forEach(x -> {
                    List<String> tmp = signTrim1(x);
                    spam_words.addAll(tmp);
                });
            } else {
                ham_mess.add(parts[1]);
                Arrays.asList(parts[1].split(" ")).forEach(x -> {
                    List<String> tmp = signTrim1(x);
                    ham_words.addAll(tmp);
                });

            }
            counter++;
        }
        //a priori spam/ham
        //p(message=spam/ham)=br na spam(ham) poraki/vkupen broj na poraki
        float spam_apriori = (float) spam_mess.size() / counter;
        float ham_apriori = (float) ham_mess.size() / counter;

        //presmetuvanje a posetiori p(ham|word)
        //se presmetuva samo dokolku zborot e pogolem od 2 karakteri i ne spagja vo listata na svrznici
        ham_words.forEach(x -> {
            if (x.length() > 2 && !stopWords.contains(x)) {
                Ham_map.put(x, calculateProb(x, ham_words, spam_words, ham_apriori, spam_apriori));
            }
        });
        //presmetuvanje a posetiori p(spam|word)
        spam_words.forEach(x -> {
            if (x.length() > 2 && !stopWords.contains(x)) {
                Spam_map.put(x, calculateProb(x, spam_words, ham_words, spam_apriori, ham_apriori));
            }
        });


        //print na vredostite
        //System.out.println("--------------SPAM-------------");
        // Spam_map.forEach((x,y)-> System.out.println(x+" "+y.toString()));
        //System.out.println("--------------HAM---------------");
        // Ham_map.forEach((x,y)-> System.out.println(x+" "+y.toString()));


        //citanje od datoteka vo lokalen repozitorium so BufferedReader, linija po linija => testing data
        BufferedReader br1 = new BufferedReader(new FileReader("C:\\Users\\janag\\IdeaProjects\\Homework1\\src\\DataSets\\SMSSpamTest.txt"));
        //se zima sekoj red i se predvidiuva dali e spam ili ne
        int TP = 0;
        int FP = 0;
        int TN = 0;
        int FN = 0;
        counter = 0;
        //presmetuvanje na verojatnosti sekoja da e spam ili ham
        //p(spam|message)=(proizvod od p(spam|word) za sekoj zbor vo porakata)/(proizvod od p(spam|word) za sekoj zbor vo porakata)+(proizvod od p(ham|word) za sekoj zbor vo porakata)
        while ((line = br1.readLine()) != null) {
            String[] parts = line.split("\t");
            String correct_state = parts[0];
            float prob_Spam = 1;
            float prob_Ham = 1;
            String[] words = parts[1].split(" ");
            //probSpam= p1(spam|word1)*p2(spam|word2)...pN(spam|wordN)
            for (String word : words) {
                List<String> tmp = signTrim1(word);
                for (String t : tmp) {
                    if (Spam_map.containsKey(t)) {
                        prob_Spam *= (Spam_map.get(t));

                    }
                }
                tmp = signTrim1(word);
                //probHam= p1(ham|word1)*p2(ham|word2)...pN(ham|wordN)
                for (String t : tmp) {
                    if (Ham_map.containsKey(t)) {
                        prob_Ham *= (Ham_map.get(t));

                    }


                }
            }
            //verojatnost porakata da e spam odnosno ham
            prob_Spam = (prob_Spam / (prob_Spam + prob_Ham));
            prob_Ham = (prob_Ham / (prob_Spam + prob_Ham));
            //proverka dali e tocno predviduvanjeto za porakata
            //truePositvive- porakata e tocno predvidena deka e spam
            if (prob_Spam > prob_Ham && correct_state.equals("spam")) {
                TP++;
            }
            //trueNegative- porakata e tocno predvidena deka e ham
            if (prob_Ham > prob_Spam && correct_state.equals("ham")) {
                TN++;
            }
            //falsePositives- porakata e ham a pri predviduvanje e klasificirana kako spam
            if (prob_Spam > prob_Ham && correct_state.equals("ham")) {
                FP++;
            }
            //falseNegatives- porakata e spam a pri predviduvanje e klasificirana kako ham
            if (prob_Ham > prob_Spam && correct_state.equals("spam")) {
                FN++;
            }

            counter++;
        }

        System.out.println("True Positives: " + TP);
        System.out.println("False Positives: " + FP);
        System.out.println("True Negatives: " + TN);
        System.out.println("False Negatives: " + FN);
        //presmetuvanje na accuracy na klasifikatorot so dadenite training i testing podatoci

        System.out.format("Accuracy: %.2f%%", (float) (TP + TN) / counter);
        //TRP (sensitivity)=>kolku moze tocno da predvidi spam od site spam poraki
        float TPR = (float) TP / (TP + FN);
        System.out.format("\nSensitivity: %.2f%%", TPR);
        //TNR (specificity)=>kolku moze tocno da predvidi ham od site ham poraki (ili da kaze deka ne e spam)
        float TNR = (float) TN / (TN + FP);
        System.out.format("\nSpecificity: %.2f%%", TNR);
        //Balanced accuracy
        float balanced = (TNR + TPR) / 2;
        System.out.format("\nBalanced accuracy: %.2f%%", balanced);

    }

}
