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

    public ContactRecord() {
    }

    public ContactRecord(int id, String firstname, String lastname, String mobile) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobile = mobile;
    }
}
