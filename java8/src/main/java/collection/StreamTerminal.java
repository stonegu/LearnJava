package collection;


import org.testng.annotations.Test;

import java.util.IntSummaryStatistics;
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
        assertThat(Stream.of(1, 2, 3, 4).collect(Collectors.averagingInt(n -> n))).isEqualTo(2.5d);
    }

    @Test
    public void sumTest() {
        assertThat(Stream.of(1, 2, 3, 4).collect(Collectors.summingInt(n -> n))).isEqualTo(10);

        IntSummaryStatistics statistics = Stream.of(1, 2, 3, 4).collect(Collectors.summarizingInt(n -> n));
        assertThat(statistics.getAverage()).isEqualTo(2.5d);
        assertThat(statistics.getSum()).isEqualTo(10);

        String joinResult = Stream.of(1, 2, 3, 4).map(n -> n+"").collect(Collectors.joining(",", "Pre- ", " -Post"));
        assertThat(joinResult).isEqualTo("Pre- 1,2,3,4 -Post");


    }

}
