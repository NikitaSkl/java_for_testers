package ru.stqa.addressbook.manager;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import ru.stqa.addressbook.manager.hbm.ContactRecord;
import ru.stqa.addressbook.manager.hbm.GroupRecord;
import ru.stqa.addressbook.model.Contact;
import ru.stqa.addressbook.model.Group;

import java.util.List;
import java.util.stream.Collectors;

public class HibernateHelper extends HelperBase{
    private SessionFactory sessionFactory;
    public HibernateHelper(ApplicationManager manager) {
        super(manager);
        sessionFactory = new Configuration()
                .addAnnotatedClass(GroupRecord.class)
                .addAnnotatedClass(ContactRecord.class) //добавили
                .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull")
                // Credentials
                .setProperty(AvailableSettings.USER, "root")
                .setProperty(AvailableSettings.PASS, "")
                .buildSessionFactory();
    }
    static List<Group> convertGroupList(List<GroupRecord> records) {
        return records.stream()
                .map(HibernateHelper::convertGroup)
                .collect(Collectors.toList());
        /*List<Group> result=new ArrayList<>();
        for (var record:records) {
            result.add(convertGroup(record));
        }
        return result;*/
    }

    private static Group convertGroup(GroupRecord record) {
        return new Group(String.valueOf(record.id), record.name, record.header, record.footer);
    }
    private static GroupRecord convertGroup(Group data) {
        var id=data.id();
        if ("".equals(data.id())){
            id="0";
            //чтобы parseInt не сломался при пустом id - добавим проверку
        }
        return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
    }


    public List<Group> getGroupList(){
        return convertGroupList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord",GroupRecord.class).list(); //здесь возвращаются объекты типа GroupRecord, значит нужно преобразование к Group
        }));
    }

    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from GroupRecord",Long.class).getSingleResult();
        });
    }

    public void createGroup(Group group) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convertGroup(group)); //чтобы сохранить объект вызваем метод persist (save устаревший)
            session.getTransaction().commit();
        });
    }

    public List<Contact> getContactsInGroup(Group group) {
        return sessionFactory.fromSession(session -> {
            return convertContactList(session.get(GroupRecord.class,group.id()).contacts); //session.get для получения объекта по идентификатору
        });
    }
    static List<Contact> convertContactList(List<ContactRecord> records) {
        return records.stream()
                .map(HibernateHelper::convertContact)
                .collect(Collectors.toList());
    }

    private static Contact convertContact(ContactRecord record) {
        return new Contact().withId(String.valueOf(record.id))
                .withFirstName(record.firstname)
                .withLastName(record.lastname)
                .withMobile(record.mobile)
                .withHome(record.home)
                .withWork(record.work)
                .withSecondary(record.phone2);
    }
    private static ContactRecord convertContact(Contact data) {
        var id=data.id();
        if ("".equals(data.id())){
            id="0";
            //чтобы parseInt не сломался при пустом id - добавим проверку
        }
        return new ContactRecord(Integer.parseInt(id), data.firstName(), data.lastName(), data.mobile());
    }

    public List<Contact> getContactsList() {
        return convertContactList(sessionFactory.fromSession(session -> {
            return session.createQuery("from ContactRecord",ContactRecord.class).list();
        }));
    }

    public long getContactCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from ContactRecord",Long.class).getSingleResult();
        });
    }

    public void createContact(Contact contact) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convertContact(contact));
            session.getTransaction().commit();
        });
    }
}
