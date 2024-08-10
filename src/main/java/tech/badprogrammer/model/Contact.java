package tech.badprogrammer.model;

import org.apache.commons.lang3.tuple.Pair;

import java.io.Serializable;
import java.time.LocalDate;

public class Contact implements Serializable {

    private int                   id;
    private String                firstName;
    private String                lastName;
    private Pair<String, String>  phone;
    private String                label;
    private Pair<String, String>  email;
    private String                website;
    private String                notes;
    private String                profilePicture;
    private String                processedProfilePicture;
    // MM-dd or MM-dd-yyyy
    private String                birthday;
    // MM-dd-yyyy
    private LocalDate             processedBirthday;
    private Pair<Address, String> address;
    private String                significantDate;
    private LocalDate             processedSignificantDate;
    private String                company;
    private String                jobTitle;
    private String                customField;

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

    public Pair<String, String> getPhone() {
        return phone;
    }

    public void setPhone(final Pair<String, String> phone) {
        this.phone = phone;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(final String label) {
        this.label = label;
    }

    public Pair<String, String> getEmail() {
        return email;
    }

    public void setEmail(final Pair<String, String> email) {
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

    public String getProcessedProfilePicture() {
        return processedProfilePicture;
    }

    public void setProcessedProfilePicture(final String processedProfilePicture) {
        this.processedProfilePicture = processedProfilePicture;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(final String birthday) {
        this.birthday = birthday;
    }

    public LocalDate getProcessedBirthday() {
        return processedBirthday;
    }

    public void setProcessedBirthday(final LocalDate processedBirthday) {
        this.processedBirthday = processedBirthday;
    }

    public Pair<Address, String> getAddress() {
        return address;
    }

    public void setAddress(final Pair<Address, String> address) {
        this.address = address;
    }

    public String getSignificantDate() {
        return significantDate;
    }

    public void setSignificantDate(final String significantDate) {
        this.significantDate = significantDate;
    }

    public LocalDate getProcessedSignificantDate() {
        return processedSignificantDate;
    }

    public void setProcessedSignificantDate(final LocalDate processedSignificantDate) {
        this.processedSignificantDate = processedSignificantDate;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(final String company) {
        this.company = company;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(final String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCustomField() {
        return customField;
    }

    public void setCustomField(final String customField) {
        this.customField = customField;
    }

    @Override
    public String toString() {
        return String.format("%d, %s, %s, %s %s, %s, %s", id, firstName, lastName, phone, email, label, address);
    }
}
