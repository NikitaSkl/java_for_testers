package ru.stqa.addressbook.manager;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import ru.stqa.addressbook.manager.hbm.GroupRecord;
import ru.stqa.addressbook.model.Group;

import java.util.ArrayList;
import java.util.List;

public class HibernateHelper extends HelperBase{
    private SessionFactory sessionFactory;
    public HibernateHelper(ApplicationManager manager) {
        super(manager);
        sessionFactory = new Configuration()
                .addAnnotatedClass(GroupRecord.class)
                //.addAnnotatedClass(Author.class)
                .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook")
                // Credentials
                .setProperty(AvailableSettings.USER, "root")
                .setProperty(AvailableSettings.PASS, "")
                .buildSessionFactory();
    }
    static List<Group> convertList(List<GroupRecord> records) {
        List<Group> result=new ArrayList<>();
        for (var record:records) {
            result.add(convertGroup(record));
        }
        return result;
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
        return convertList(sessionFactory.fromSession(session -> {
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
}
