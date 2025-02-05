package model;

public record Contact(String firstName, String lastName, String mobileNumber) {

    public Contact() {
        this("","","");
    }
}
