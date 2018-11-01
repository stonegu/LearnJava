package collection;


import org.testng.annotations.Test;

import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.intThat;
import static org.mockito.Mockito.when;


public class StreamTerminal {

    @Test
    public void reduceTest() {
        int reduced1 = IntStream.range(1, 4).reduce(10, (a, b) -> a + b);
        assertThat(reduced1).isEqualTo(16);

        OptionalInt optionalInt = IntStream.rangeClosed(1, 4).reduce((a, b) -> a + b);
        assertThat(optionalInt.getAsInt()).isEqualTo(10);
    }

    @Test
    public void averageTest() {
        assertThat(IntStream.range(1, 5).average().getAsDouble()).isEqualTo(2.5d);
        assertThat(Stream.of("1", "2", "3", "4").collect(Collectors.averagingInt(n -> Integer.parseInt(n)))).isEqualTo(2.5d);





    }

}
