@FunctionalInterface
public interface CheckLog<T> {

    boolean check(T thing);

    default void print(T thing) {
        System.out.println(thing);
    }

}
