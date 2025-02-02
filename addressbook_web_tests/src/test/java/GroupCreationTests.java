import model.Group;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void canCreateGroup() {
        openGroupsPage();
        createGroup(new Group("test group name 1", "test group header 1", "test group footer 1"));
    }

    @Test
    public void canCreateGroupWithEmptyName() {
        openGroupsPage();
        createGroup(new Group());
    }
    @Test
    public void canCreateGroupWithNameOnly() {
        openGroupsPage();
        var emptyGroup=new Group();
        var groupWithName=emptyGroup.withName("some name");
        createGroup(groupWithName);
    }
}
