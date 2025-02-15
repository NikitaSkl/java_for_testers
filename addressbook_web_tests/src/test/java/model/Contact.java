package model;

public record Contact(String id, String firstName, String lastName, String mobileNumber) {

    public Contact() {
        this("", "", "", "");
    }
    public Contact withFirstName(String firstName) {
        return new Contact(this.id, firstName, this.lastName, this.mobileNumber);
    }
    public Contact withLastName(String lastName) {
        return new Contact(this.id, this.firstName, lastName, this.mobileNumber);
    }
    public Contact withMobile(String mobileNumber) {
        return new Contact(this.id, this.firstName, this.lastName, mobileNumber);
    }
    public Contact withId(String id) {
        return new Contact(id, this.firstName, this.lastName, this.mobileNumber);
    }
}
