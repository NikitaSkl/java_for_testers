package ru.stqa.addressbook.model;

public record Contact(String id, String firstName, String lastName, String mobileNumber, String photo) {

    public Contact() {
        this("", "", "", "","");
    }
    public Contact withFirstName(String firstName) {
        return new Contact(this.id, firstName, this.lastName, this.mobileNumber,this.photo);
    }
    public Contact withLastName(String lastName) {
        return new Contact(this.id, this.firstName, lastName, this.mobileNumber,this.photo);
    }
    public Contact withMobile(String mobileNumber) {
        return new Contact(this.id, this.firstName, this.lastName, mobileNumber,this.photo);
    }
    public Contact withId(String id) {
        return new Contact(id, this.firstName, this.lastName, this.mobileNumber,this.photo);
    }
    public Contact withPhoto(String photo) {
        return new Contact(this.id, this.firstName, this.lastName, this.mobileNumber,photo);
    }

}
