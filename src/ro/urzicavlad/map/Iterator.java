package ro.urzicavlad.map;

public interface Iterator <K,V> {

    Map.Entry<K,V> next();

    boolean hasNext();

}
