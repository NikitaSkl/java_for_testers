package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class GroupModificationTests extends TestBase{
    @Test
    public void canModifyGroup(){
        if (!app.groups().isGroupPresent()){
            app.groups().createGroup(new Group("", "test group name 1", "test group header 1", "test group footer 1"));
        }
        var oldGroups=app.groups().getList();
        var rnd=new Random();
        var index=rnd.nextInt(oldGroups.size());
        var testData = new Group().withName("modified name");
        app.groups().modifyGroup(oldGroups.get(index), testData); //отредактировали группу с случайно сгенерированным индексом (в пределах размера списка групп) на группу с новым именем
        var newGroups=app.groups().getList();
        var expectedGroups=new ArrayList<Group>(oldGroups);
        expectedGroups.set(index,testData.withId((oldGroups.get(index)).id())); //заменили группу, по тому же индексу в старом списке, присвоив тот же айди и новое имя последовательно
        Comparator<Group> groupComparatorById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(groupComparatorById);
        expectedGroups.sort(groupComparatorById);
        Assertions.assertEquals(newGroups,expectedGroups);
    }
}
