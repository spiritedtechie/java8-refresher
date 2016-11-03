import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

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

        List<String> result = stuff.stream()
                .filter(item -> item.startsWith("B"))
                .collect(Collectors.toList()); // use of collector to convert stream back to list

        assertThat(result, is(Arrays.asList("Bag", "Ball")));
    }

    @Test
    public void streamFindFirst() {

        Optional<String> first = stuff.stream().findFirst();

        assertThat(first, is(Optional.of("Phone")));
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

    @Test
    public void greetUsingHighOrderFunction() {

        String greeting = greet(() -> "John");

        assertThat(greeting, is("Hello John"));
    }

    // Higher order function
    private String greet(Supplier<String> name) {
        return "Hello " + name.get();
    }

    @Test
    public void greetUsingFirstClassFunction() {

        Function<String, String> greet = (name) -> "Hello " + name;

        assertThat(greet.apply("John"), is("Hello John"));
    }

    @Test
    public void functionalInterfaceToCheckIfSomethingCanBeLogged() {

        CheckLog<String> checker = s -> s.contains("p");

        assertTrue(checker.check("pole"));
        assertFalse(checker.check("Fish"));
        assertTrue(checker.check("Bop"));
    }

    @Test
    public void optionalReturningFunction() {

        Optional<String> result1 = prependStar("red");
        Optional<String> result2 = prependStar("red.");

        assertThat(result1, is(Optional.of("*red")));
        assertThat(result2, is(Optional.empty()));

        String parsed1 = result1.orElse("unknown");
        assertThat(parsed1, is("*red"));

        String parsed2 = result2.orElse("unknown");
        assertThat(parsed2, is("unknown"));
    }

    // Function returning Optional
    private Optional<String> prependStar(String text) {
        return text.contains(".") ? Optional.empty() : Optional.of("*" + text);
    }

}
