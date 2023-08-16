package OpinionPoll;

public class Person {

    private String name;
    private int age;


    public int getAge() {
        return age;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return this.name + " - " + this.age;
    }
}
