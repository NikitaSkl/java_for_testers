import model.Group;
import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        openGroupsPage();
        if (!isGroupPresent()){
            createGroup(new Group("test group name 1", "test group header 1", "test group footer 1"));
        }
        removeGroup();
    }

}
