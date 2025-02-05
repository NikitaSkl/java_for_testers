package tests;

import model.Group;
import org.junit.jupiter.api.Test;

public class GroupModificationTests extends TestBase{
    @Test
    public void canModifyGroup(){
        if (!app.groups().isGroupPresent()){
            app.groups().createGroup(new Group("test group name 1", "test group header 1", "test group footer 1"));
        }
        app.groups().modifyGroup(new Group().withName("modified name"));
    }
}
