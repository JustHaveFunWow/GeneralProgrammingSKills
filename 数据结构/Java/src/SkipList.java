import java.util.Random;
import java.util.concurrent.ConcurrentSkipListMap;

public class SkipList<T extends Comparable<? super T>>{
    private int maxLevel;
    private SkipList<T>[] root;

}