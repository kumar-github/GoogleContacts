package tech.badprogrammer.model;

public class Contact {

    private int    id;
    private String firstName;
    private String lastName;
    private String phone;

    public Contact() {
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format("%d, %s, %s, %s", id, firstName, lastName, phone);
    }
}
