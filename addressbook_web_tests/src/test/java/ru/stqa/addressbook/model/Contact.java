package ru.stqa.addressbook.model;

public record Contact(
        String id,
        String firstName,
        String lastName,
        String mobile,
        String photo,
        String home,
        String work,
        String secondary) {

    public Contact() {
        this("", "", "", "","", "", "", "");
    }

    public Contact withFirstName(String firstName) {
        return new Contact(this.id, firstName, this.lastName, this.mobile,this.photo, this.home, this.work, this.secondary);
    }
    public Contact withLastName(String lastName) {
        return new Contact(this.id, this.firstName, lastName, this.mobile,this.photo, this.home, this.work, this.secondary);
    }
    public Contact withMobile(String mobileNumber) {
        return new Contact(this.id, this.firstName, this.lastName, mobileNumber,this.photo, this.home, this.work, this.secondary);
    }
    public Contact withId(String id) {
        return new Contact(id, this.firstName, this.lastName, this.mobile,this.photo, this.home, this.work, this.secondary);
    }
    public Contact withPhoto(String photo) {
        return new Contact(this.id, this.firstName, this.lastName, this.mobile,photo, this.home, this.work, this.secondary);
    }
    public Contact withHome(String home) {
        return new Contact(this.id, this.firstName, this.lastName, this.mobile, this.photo, home, this.work, this.secondary);
    }
    public Contact withWork(String work) {
        return new Contact(this.id, this.firstName, this.lastName, this.mobile, this.photo, this.home, work, this.secondary);
    }
    public Contact withSecondary(String secondary) {
        return new Contact(this.id, this.firstName, this.lastName, this.mobile, this.photo, this.home, this.work, secondary);
    }

}
