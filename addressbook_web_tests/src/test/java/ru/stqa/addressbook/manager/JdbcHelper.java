package ru.stqa.addressbook.manager;

import ru.stqa.addressbook.model.Group;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcHelper extends HelperBase{
    public JdbcHelper(ApplicationManager manager) {
        super(manager);
    }

    public List<Group> getGroupList() {
        var groups=new ArrayList<Group>();
        //делаем запрос к базе данных и добавляем его в список groups
        try (var conn=DriverManager.getConnection("jdbc:mysql://localhost/addressbook","root","");
             var statement=conn.createStatement();
        var result=statement.executeQuery("SELECT group_id,group_name,group_header,group_footer FROM group_list")) //в данном случае мы хотим выполнить именно query - запрос на получение данных, если бы хотели сделать удаление или создание/ред, тогда надо использовать более общий метод execute
        {
            while (result.next()){
                groups.add(new Group()
                        .withId(result.getString("group_id"))
                        .withName(result.getString("group_name"))
                        .withHeader(result.getString("group_header"))
                        .withFooter(result.getString("group_footer")));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return groups;
    }

    public void checkConsistency() {
        var groups=new ArrayList<Group>();
        try (var conn=DriverManager.getConnection("jdbc:mysql://localhost/addressbook","root","");
             var statement=conn.createStatement();
             var result=statement.executeQuery("SELECT*FROM address_in_groups ag LEFT JOIN addressbook ab on ab.id=ag.id where ab.id is null"))
        {
            while (result.next()){
                throw new IllegalStateException("DB is corrupted");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
