package ru.stqa.addressbook.manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "group_list")
public class GroupRecord {


    @Id
    @Column(name="group_id")
    public int id;
    @Column(name="group_name")
    public String name;
    @Column(name="group_header")
    public String header;
    @Column(name="group_footer")
    public String footer;

    //public String deprecated = "0000-00-00 00:00:00";

    public GroupRecord() {
    }

    public GroupRecord(int id, String name, String header, String footer) {
        this.id = id;
        this.name = name;
        this.header = header;
        this.footer = footer;
    }
}
