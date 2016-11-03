import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class MainTest {

    private List<String> stuff = new ArrayList<>();

    @Before
    public void setup() {
        stuff.add("Phone");
        stuff.add("Laptop");
        stuff.add("Bag");
        stuff.add("Ball");
        stuff.add("Keys");
    }

    @Test
    public void forEachWithAnonymousInnerClass() {
        stuff.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
    }

    @Test
    public void forEachWithLambdaExpression() {

        stuff.forEach(i -> System.out.println(i));
    }

    @Test
    public void forEachWithMethodReference() {

        stuff.forEach(System.out::println);
    }

    @Test
    public void streamFilter() {

        stuff.stream()
                .filter(item -> item.startsWith("B"))
                .forEach(System.out::println);
    }

    @Test
    public void streamFindFirst() {

        stuff.stream()
                .findFirst()
                .ifPresent(System.out::println);
    }

    @Test
    public void streamSorted() {

        stuff.stream()
                .sorted()
                .forEach(System.out::println);
    }

    @Test
    public void streamMapItemsAppendWithStar() {

        stuff.stream()
                .map(item -> "*" + item)
                .forEach(System.out::println);
    }

    @Test
    public void streamFlatMapToStreamOfCharacters() {

        stuff.stream()
                .flatMap(i -> Arrays.asList(i.split("")).stream())
                .forEach(System.out::println);
    }
    
}
