package tests;

import model.Group;
import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        if (!app.groups().isGroupPresent()){
            app.groups().createGroup(new Group("test group name 1", "test group header 1", "test group footer 1"));
        }
        app.groups().removeGroup();
    }

}
