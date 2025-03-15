package ru.stqa.addressbook.manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "addressbook")
public class ContactRecord {
    @Id
    @Column
    public int id;
    @Column
    public String firstname;
    @Column
    public String lastname;
    @Column
    public String mobile;
    @Column
    public String middlename = "";
    @Column
    public String nickname = "";
    @Column
    public String company = "";
    @Column
    public String title = "";
    @Column
    public String address = "";
    @Column
    public String home;
    @Column
    public String work;
    @Column
    public String phone2;
    @Column
    public String fax = "";
    @Column
    public String email = "";
    @Column
    public String email2 = "";
    @Column
    public String email3 = "";
    @Column
    public String homepage = "";

    public ContactRecord() {
    }

    public ContactRecord(int id, String firstname, String lastname, String mobile) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobile = mobile;
    }
}
