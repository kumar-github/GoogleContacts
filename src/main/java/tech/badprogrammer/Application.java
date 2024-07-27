package tech.badprogrammer;

import tech.badprogrammer.model.Contact;

public class Application {

    public static void main(String[] args) {
        final Contact john = new Contact();
        john.setId(1001);
        john.setFirstName("John");
        john.setLastName("Doe");
        john.setPhone("+910000000000");
        System.out.println(john);
    }
}
