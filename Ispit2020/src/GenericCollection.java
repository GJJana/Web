import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class GenericCollection<T extends Element> {
    Map<String, List<T>> map;
    void addGenericItem (String category, T element){
        map.putIfAbsent(category,new ArrayList<>());
        map.get(category).add(element);
    }

    Collection<T> findAllBetween (LocalDateTime from, LocalDateTime to){
            Set<T> tmp=new TreeSet<>();
             map.values().stream().forEach(l->tmp.addAll(l.stream().filter(k->k.getTimestamp().isBefore(to)&&k.getTimestamp().isAfter(from)).collect(Collectors.toSet())));
             return tmp;
    }
    Collection<T> itemsFromCategories (List<String> categories){
        List<T> el=new ArrayList<>();
        categories.forEach(x->el.addAll(map.get(x)));
        return el;
    }
    public Map<String, Set<T>> byMonthAndDay(){

    }
    public Map<Integer, Long> countByYear(){

    }
}
