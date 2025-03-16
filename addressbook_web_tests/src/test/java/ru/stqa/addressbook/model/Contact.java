package ru.stqa.addressbook.model;

public record Contact(
        String id,
        String firstName,
        String lastName,
        String mobile,
        String photo,
        String home,
        String work,
        String secondary,
        String address,
        String email,
        String email2,
        String email3) {

    public Contact() {
        this("", "", "", "","", "", "", "", "", "", "", "");
    }

    public Contact withFirstName(String firstName) {
        return new Contact(this.id, firstName, this.lastName, this.mobile,this.photo, this.home, this.work, this.secondary, this.address, this.email, this.email2, this.email3);
    }
    public Contact withLastName(String lastName) {
        return new Contact(this.id, this.firstName, lastName, this.mobile,this.photo, this.home, this.work, this.secondary, this.address, this.email, this.email2, this.email3);
    }
    public Contact withMobile(String mobileNumber) {
        return new Contact(this.id, this.firstName, this.lastName, mobileNumber,this.photo, this.home, this.work, this.secondary, this.address, this.email, this.email2, this.email3);
    }
    public Contact withId(String id) {
        return new Contact(id, this.firstName, this.lastName, this.mobile,this.photo, this.home, this.work, this.secondary, this.address, this.email, this.email2, this.email3);
    }
    public Contact withPhoto(String photo) {
        return new Contact(this.id, this.firstName, this.lastName, this.mobile,photo, this.home, this.work, this.secondary, this.address, this.email, this.email2, this.email3);
    }
    public Contact withHome(String home) {
        return new Contact(this.id, this.firstName, this.lastName, this.mobile, this.photo, home, this.work, this.secondary, this.address, this.email, this.email2, this.email3);
    }
    public Contact withWork(String work) {
        return new Contact(this.id, this.firstName, this.lastName, this.mobile, this.photo, this.home, work, this.secondary, this.address, this.email, this.email2, this.email3);
    }
    public Contact withSecondary(String secondary) {
        return new Contact(this.id, this.firstName, this.lastName, this.mobile, this.photo, this.home, this.work, secondary, this.address, this.email, this.email2, this.email3);
    }
    public Contact withAddress(String address) {
        return new Contact(this.id, this.firstName, this.lastName, this.mobile, this.photo, this.home, this.work, this.secondary, address, this.email, this.email2, this.email3);
    }
    public Contact withEmail1(String email) {
        return new Contact(this.id, this.firstName, this.lastName, this.mobile, this.photo, this.home, this.work, this.secondary, this.address, email, this.email2, this.email3);
    }
    public Contact withEmail2(String email2) {
        return new Contact(this.id, this.firstName, this.lastName, this.mobile, this.photo, this.home, this.work, this.secondary, this.address, this.email, email2, this.email3);
    }
    public Contact withEmail3(String email3) {
        return new Contact(this.id, this.firstName, this.lastName, this.mobile, this.photo, this.home, this.work, this.secondary, this.address, this.email, this.email2, email3);
    }

}
