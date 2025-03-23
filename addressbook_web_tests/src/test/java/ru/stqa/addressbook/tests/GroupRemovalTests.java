package ru.stqa.addressbook.tests;

import io.qameta.allure.Allure;
import ru.stqa.addressbook.model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        Allure.step("Checking precondition", step ->{
            if (app.hbm().getGroupCount()==0){
                app.hbm().createGroup(new Group("", "test group name 1", "test group header 1", "test group footer 1"));
            }
        });
        var oldGroups=app.hbm().getGroupList();
        var rnd=new Random();
        var index=rnd.nextInt(oldGroups.size());
        app.groups().removeGroup(oldGroups.get(index));
        var newGroups=app.hbm().getGroupList();
        var expectedGroups=new ArrayList<Group>(oldGroups);
        expectedGroups.remove(index);
        Allure.step("Result validation", step ->{
            Assertions.assertEquals(newGroups,expectedGroups);
        });
    }
    @Test
    public void canRemoveAllGroupsAtOnce() {
        if (app.hbm().getGroupCount()==0){
            app.hbm().createGroup(new Group("", "test group name 1", "test group header 1", "test group footer 1"));
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0,app.hbm().getGroupCount());
    }
}
