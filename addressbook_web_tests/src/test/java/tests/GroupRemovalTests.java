package tests;

import model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        if (app.groups().getCount()==0){
            app.groups().createGroup(new Group("", "test group name 1", "test group header 1", "test group footer 1"));
        }
        var oldGroups=app.groups().getList();
        var rnd=new Random();
        var index=rnd.nextInt(oldGroups.size());
        app.groups().removeGroup(oldGroups.get(index));
        var currentGroups=app.groups().getList();
        var expectedGroups=new ArrayList<Group>(oldGroups);
        expectedGroups.remove(index);
        Assertions.assertEquals(currentGroups,expectedGroups);
    }
    @Test
    public void canRemoveAllGroupsAtOnce() {
        if (app.groups().getCount()==0){
            app.groups().createGroup(new Group("", "test group name 1", "test group header 1", "test group footer 1"));
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0,app.groups().getCount());
    }
}
