package ru.stqa.addressbook.manager.hbm;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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
    @ManyToMany(fetch = FetchType.LAZY) //можно добавить доп. параметр fetch. Есть два значения параметра, по умолчанию стоит Lazy
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    public List<ContactRecord> contacts;

    public GroupRecord() {
    }

    public GroupRecord(int id, String name, String header, String footer) {
        this.id = id;
        this.name = name;
        this.header = header;
        this.footer = footer;
    }
}
