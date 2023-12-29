package me.whiteship.chapter01.item03.methodreference;

import autovalue.shaded.com.google$.common.base.$Function;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Person {

    LocalDate birthday;

    public Person() {

    }

    public Person(LocalDate birthday) {
        this.birthday = birthday;
    }

//    public static int compareByAge(Person a, Person b) {
//        return a.birthday.compareTo(b.birthday);
//    }

    public int compareByAge(Person b) {
        return this.birthday.compareTo(b.birthday);
    }

    public static void main(String[] args) {


        List<LocalDate> dates = new ArrayList<>();
        dates.add(LocalDate.of(1982, 7, 15));
        dates.add(LocalDate.of(2011, 3, 2));
        dates.add(LocalDate.of(2013, 1, 28));
        List<Person> collect = dates.stream().map(d -> new Person(d)).collect(Collectors.toList());
        List<Person> collect2 = dates.stream().map(Person::new).collect(Collectors.toList());

        Function<LocalDate, Person> function = Person::new;//변수로 뺄 수도 있다.
        //Person::new; 메서드 레퍼런스 사용시 Person()를 참조하고 싶을 경우?


        List<Person> people = new ArrayList<>();
        people.add(new Person(LocalDate.of(1982, 7, 15)));
        people.add(new Person(LocalDate.of(2011, 3, 2)));
        people.add(new Person(LocalDate.of(2013, 1, 28)));

        //익명내부클래스
        Comparator<Person> comparator = new Comparator<>() {
            @Override
            public int compare(Person a, Person b) {
                return a.birthday.compareTo(b.birthday);
            }
        };
        people.sort(comparator);

        //람다
        people.sort((p1, p2) -> p1.birthday.compareTo(p2.birthday));
        // Comparator.comparing도 있네? 이건 정적팩터리 메서드
        people.sort(Comparator.comparing(p -> p.birthday));


        //메서드 참조
        people.sort(Person::compareByAge);//compareByAge는 comparator에 호환이 된다. 생긴건 int를 리턴하는 바이펑션
        //compareByAge가 static이 아니라면 new Person을 통해 미리 만들고 해야한다.


    }

    public int getAge() {
        return LocalDate.now().getYear() - birthday.getYear();
    }

}
