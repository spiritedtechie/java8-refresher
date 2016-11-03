@FunctionalInterface
public interface CheckLog<T> {

    boolean check(T thing);
    
}
