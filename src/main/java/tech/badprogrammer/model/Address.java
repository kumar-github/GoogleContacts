package tech.badprogrammer.model;

import java.io.Serializable;

public class Address implements Serializable {

    private int    id;
    private String street;
    private String city;
    private String zipcode;
    private String state;
    private String country;

    public Address() {
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(final String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(final String zipcode) {
        this.zipcode = zipcode;
    }

    public String getState() {
        return state;
    }

    public void setState(final String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }


    @Override
    public String toString() {
        return String.format("%d, %s, %s, %s, %s, %s", id, street, city, zipcode, state, country);
    }
}
