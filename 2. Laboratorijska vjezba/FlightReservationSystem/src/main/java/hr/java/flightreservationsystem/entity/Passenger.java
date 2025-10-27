package hr.java.flightreservationsystem.entity;

public class Passenger extends User {
    private Integer age;

    public Passenger(String firstName, String lastName, String email, Integer age) {
        super(firstName, lastName, email);
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
