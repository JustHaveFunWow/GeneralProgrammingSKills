package chapter3;

/**
 * Created by zhuoxiuwu on 2017/9/8.
 */
public abstract class BinarySearchST<Key extends Comparable<Key>,Value>{
    private Key[] keys;
    private Value[] vals;
    private int N;
    public BinarySearchST(int capacity){
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Comparable[capacity];
    }

    public int size(){
        return N;
    }
    public Value get(Key key){
        if (isEmpty())
            return null;
        int i = rank(key);
        if (i<N && keys[i].compareTo(key) == 0){
            return vals[i];
        }else
            return null;
    }

    public void put(Key key,Value val){
        int i = rank(key);
        if (i<N &&keys[i].compareTo(key) ==0){
            vals[i] =val;
            return;
        }
        for (int j = N; j >i; j--) {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] =val;
        N++;

    }

    public abstract int rank(Key key);

    public abstract boolean delete(Key key);

    public boolean isEmpty() {
        return keys.length == 0;
    }
}
