package hr.java.flightreservationsystem.entity;

public class Pilot extends User {
    private Integer hoursFlown;

    public Pilot(String firstName, String lastName, String email, Integer hoursFlown) {
        super(firstName, lastName, email);
        this.hoursFlown = hoursFlown;
    }

    public Integer getHoursFlown() {
        return hoursFlown;
    }

    public void setHoursFlown(Integer hoursFlown) {
        this.hoursFlown = hoursFlown;
    }
}
