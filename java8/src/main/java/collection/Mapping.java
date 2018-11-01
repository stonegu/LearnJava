package collection;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Mapping {
    public static void main (String... args) {
        List<String> stringList = Arrays.asList("b", "s", "a", "t");
        System.out.println(stringList);

        List<String> upcaseStringList = stringList.stream().map(element -> element.toUpperCase()).collect(Collectors.toList());
        System.out.println(upcaseStringList);

        List<String> sortedStringList = stringList.stream().sorted(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        }).collect(Collectors.toList());

        System.out.println(sortedStringList);

        // List to Map for String
        Map<Integer, String> map = stringList.stream().collect(Collectors.toMap(element -> stringList.indexOf(element), element -> element));
        System.out.println(map);

        Map<Integer, String> map2 = stringList.stream().collect(Collectors.toMap(element -> stringList.indexOf(element), Function.identity()));
        System.out.println(map2);

        // better way :
        AtomicInteger index = new AtomicInteger();
        Map<Integer, String> map3 = stringList.stream().collect(Collectors.toMap(element -> index.getAndIncrement(), element -> element));
        System.out.println(map3);

        // List to Map for Object
        List<Person> personList = Arrays.asList(new Person.PersonBuild("name1", "gender1").build(), new Person.PersonBuild("name2", "gender2").build());
        Map<String, String> nameGenderMap = personList.stream().collect(Collectors.toMap(person -> person.getName(), person -> person.getGender()));
        System.out.println(nameGenderMap);


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
