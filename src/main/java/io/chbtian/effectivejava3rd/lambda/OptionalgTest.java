package io.chbtian.effectivejava3rd.lambda;

import java.util.Optional;
import java.util.OptionalInt;

public class OptionalgTest {
    public static void main(String[] args) {
        OptionalInt oi = OptionalInt.of(4);
        System.out.println(oi.getAsInt());
        System.out.println(oi.orElse(3));

        Optional<String> os = Optional.ofNullable("optional");

    }
}
