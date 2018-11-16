package collection;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * The Stream API enables developers to create the parallel streams that can take advantage of ** multi-core ** architectures and enhance the performance of Java code
 * Note: If the environment is not multi-threaded, then Parallel Stream creates thread and can affect the new requests coming in
*/
public class Streamparallel {

/**
 * Do remember, Parallel Streams must be used only with stateless, non-interfering, and associative operations i.e.
 *
 * A stateless operation is an operation in which the state of one element does not affect another element
 * A non-interfering operation is an operation in which data source is not affected
 * An associative operation is an operation in which the result is not affected by the order of operands
*/
    @Test
    public void parallelStreamTest() {
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> multiplaCal = integerList.parallelStream().reduce( (a, b) -> a * b);
        assertThat(multiplaCal.get()).isEqualTo(120);
    }

    @Test
    public void parallelStreamSpeedTest() {
        long st, pt, t1, t2;
        List<Employee> employees = Arrays.asList(
                new Employee("a", 20000),
                new Employee("b", 3000),
                new Employee("c", 15002),
                new Employee("d", 7856),
                new Employee("e", 200),
                new Employee("f", 50000));

        t1 = System.currentTimeMillis();
        System.out.println("Sequential Stream Count?= " + employees.stream().filter( employee -> employee.getSalary() > 15000).count());
        t2 = System.currentTimeMillis();
        System.out.println("Sequential Stream Time Taken?= " + (st = (t2-t1)) + "\n");

        t1 = System.currentTimeMillis();
        System.out.println("Parallel Stream Count?= " + employees.parallelStream().filter(employee -> employee.getSalary() > 15000).count());
        t2 = System.currentTimeMillis();
        System.out.println("Parallel Stream Time Taken?= " + (pt = (t2-t1)));

        assertThat(pt).isLessThan(st);

    }

    @Test
    public void parallelStreamSpeedTest2() throws ExecutionException, InterruptedException {
        long st, pt, t1, t2;
        long firstNum = 1, lastNum = 1_000_000;

        List<Long> aList = LongStream.rangeClosed(firstNum, lastNum).boxed().collect(Collectors.toList());

        // first
        t1 = System.currentTimeMillis();
        System.out.println("Sequential Stream sum?= " + aList.stream().reduce((a, b) -> a + b).get());
        t2 = System.currentTimeMillis();
        System.out.println("Sequential Stream Time Taken?= " + (st = (t2-t1)) + "\n");

        // second - this is the fastest one
        t1 = System.currentTimeMillis();
        System.out.println("Sequential Stream sum?= " + aList.stream().reduce(0L, Long :: sum));
        t2 = System.currentTimeMillis();
        System.out.println("Sequential Stream Time Taken?= " + (st = (t2-t1)) + "\n");

        // third
        t1 = System.currentTimeMillis();
        System.out.println("Parallel Stream sum?= " + aList.parallelStream().reduce(0L, Long :: sum));
        t2 = System.currentTimeMillis();
        System.out.println("Parallel Stream Time Taken?= " + (pt = (t2-t1)) + "\n");

        // forth
        t1 = System.currentTimeMillis();
        System.out.println("Parallel Stream sum?= " + aList.parallelStream().reduce((a, b) -> a + b).get());
        t2 = System.currentTimeMillis();
        System.out.println("Parallel Stream Time Taken?= " + (pt = (t2-t1)) + "\n");

        // fifth
        ForkJoinPool customThreadPool = new ForkJoinPool(4);
        t1 = System.currentTimeMillis();
        System.out.println("Parallel Stream sum?= " + customThreadPool.submit(() -> aList.parallelStream().reduce(0L, Long :: sum)).get());
        t2 = System.currentTimeMillis();
        System.out.println("Parallel Stream Time Taken?= " + (pt = (t2-t1)) + "\n");


    }

    class Employee {
        private String name;
        private int salary;

        public Employee(String name, int salary) {
            this.name = name;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }
    }

}
