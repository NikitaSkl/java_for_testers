package tests;

import model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        if (app.groups().getCount()==0){
            app.groups().createGroup(new Group("test group name 1", "test group header 1", "test group footer 1"));
        }
        int groupCount=app.groups().getCount();
        app.groups().removeGroup();
        Assertions.assertEquals(groupCount-1,app.groups().getCount());
    }
    @Test
    public void canRemoveAllGroupsAtOnce() {
        if (app.groups().getCount()==0){
            app.groups().createGroup(new Group("test group name 1", "test group header 1", "test group footer 1"));
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0,app.groups().getCount());
    }


}
