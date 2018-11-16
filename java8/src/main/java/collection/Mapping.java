package collection;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.hamcrest.collection.IsMapContaining.hasKey;
import static org.hamcrest.collection.IsMapContaining.hasValue;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Mapping {

    @Test
    public void upcaseStringListTest() {
        List<String> stringList = Arrays.asList("b", "s", "a", "t");
        List<String> upcaseStringList = stringList.stream().map( a -> a.toUpperCase()).collect(Collectors.toList());
        System.out.println(upcaseStringList);

        assertThat(upcaseStringList).containsExactly("B", "S", "A", "T");


        List<String> upcaseStringList2 = stringList.stream().map(String :: toUpperCase).collect(Collectors.toCollection(ArrayList::new));
        System.out.println(upcaseStringList2);
        assertThat(upcaseStringList).containsExactly("B", "S", "A", "T");

        List<String> sortedStringList = stringList.stream().sorted(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        }).collect(Collectors.toList());
        assertThat(sortedStringList).containsExactly("a", "b", "s", "t");
    }

    @Test
    public void listToMap() {
        List<String> stringList = Arrays.asList("b", "s", "a", "t");

        // List to Map for String
        Map<Integer, String> map = stringList.stream().collect(Collectors.toMap(element -> stringList.indexOf(element), element -> element));
        System.out.println(map);
        assertThat(map, hasEntry(1, "s"));

        Map<Integer, String> map2 = stringList.stream().collect(Collectors.toMap(element -> stringList.indexOf(element), Function.identity()));
        System.out.println(map2);
        assertThat(map, hasEntry(1, "s"));

        // better way :
        AtomicInteger index = new AtomicInteger();
        Map<Integer, String> map3 = stringList.stream().collect(Collectors.toMap(element -> index.getAndIncrement(), element -> element));
        System.out.println(map3);
        assertThat(map, hasEntry(1, "s"));

    }

    @Test
    public void listToMapOfObject() {
        List<String> stringList = Arrays.asList("b", "s", "a", "t");

        // List to Map for Object
        List<Person> personList = Arrays.asList(new Person.PersonBuild("name1", "gender1").build(), new Person.PersonBuild("name2", "gender2").build());
        Map<String, String> nameGenderMap1 = personList.stream().collect(Collectors.toMap(person -> person.getName(), person -> person.getGender()));

        Map<String, String> nameGenderMap2 = personList.stream().collect(Collectors.toMap(Person :: getName, Person::getGender));

        System.out.println(nameGenderMap1);
        System.out.println(nameGenderMap2);

        assertThat(nameGenderMap1, hasEntry("name1", "gender1"));
        assertThat(nameGenderMap2, hasEntry("name1", "gender1"));

    }


    public static class Person {
        private String name;
        private String gender;

        public Person(PersonBuild personBuild) {
            this.name = personBuild.name;
            this.gender = personBuild.gender;
        }

        public String getName() {
            return name;
        }

        public String getGender() {
            return gender;
        }

        public static class PersonBuild {
            private String name;
            private String gender;

            public PersonBuild(String name, String gender) {
                this.name = name;
                this.gender = gender;
            }

            public PersonBuild withName(String name) {
                this.name = name;
                return this;
            }

            public PersonBuild withGender(String gender) {
                this.gender = gender;
                return this;
            }

            public Person build() {
                return new Person(this);
            }
        }

    }
}
