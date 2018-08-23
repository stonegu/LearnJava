package optional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalBasicExample {
    public static void main(String... args) {
        Optional<String> gender = Optional.of("MALE");
        String answer1 = "Yes";
        String answer2 = null;
        Optional<String> answer1Opt = Optional.ofNullable(answer1);
        Optional<String> answer2Opt = Optional.ofNullable(answer2);

        gender.ifPresent(value -> System.out.println("gender: " + value));
        answer1Opt.ifPresent(value -> System.out.println("answer1: " + value));
        answer2Opt.ifPresent(value -> System.out.println("answer2: " + value));

        gender.filter(value -> value.equals("MALE")).ifPresent(value -> System.out.println("gender is Male"));

        // map
        List<String> companyName = Arrays.asList("paypal", "oracle", "microsoft", "", "apple");
        Optional<List<String>> listOptional = Optional.of(companyName);
        int size = listOptional.map(List<String> :: size).orElse(0);
        System.out.println("total company name is : " + size);

    }
}
