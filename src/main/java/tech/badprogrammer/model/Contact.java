package tech.badprogrammer.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Contact implements Serializable {

    private int       id;
    private String    firstName;
    private String    lastName;
    private String    phone;
    private String    label;
    private String    email;
    private String    website;
    private String    notes;
    private String    profilePicture;
    // MM-dd or MM-dd-yyyy
    private String    birthday;
    private String    processedProfilePicture;
    // MM-dd-yyyy
    private LocalDate processedBirthday;
    private Address   address;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(final String website) {
        this.website = website;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(final String notes) {
        this.notes = notes;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(final String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(final String birthday) {
        this.birthday = birthday;
    }

    public String getProcessedProfilePicture() {
        return processedProfilePicture;
    }

    public void setProcessedProfilePicture(final String processedProfilePicture) {
        this.processedProfilePicture = processedProfilePicture;
    }

    public LocalDate getProcessedBirthday() {
        return processedBirthday;
    }

    public void setProcessedBirthday(final LocalDate processedBirthday) {
        this.processedBirthday = processedBirthday;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("%d, %s, %s, %s %s, %s", id, firstName, lastName, phone, label, address);
    }
}
