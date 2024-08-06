package tech.badprogrammer.model;

import java.io.Serializable;

public class Contact implements Serializable {

    private int    id;
    private String firstName;
    private String lastName;
    private String phone;
    private String label;

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

    public String getLabel() {
        return label;
    }

    public void setLabel(final String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return String.format("%d, %s, %s, %s %s", id, firstName, lastName, phone, label);
    }
}
