package tests;

import model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void canCreateGroup() {
        int groupCount=app.groups().getCount();
        app.groups().createGroup(new Group("test group name 1", "test group header 1", "test group footer 1"));
        Assertions.assertEquals(groupCount+1,app.groups().getCount());
    }

    @Test
    public void canCreateGroupWithEmptyName() {
        app.groups().createGroup(new Group());
    }
    @Test
    public void canCreateGroupWithNameOnly() {
        app.groups().createGroup(new Group().withName("some name"));
    }
}
