package tests;

import model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class GroupCreationTests extends TestBase {

    public static List<Group> groupProvider() {
        var result=new ArrayList<Group>();
        for (var name:List.of("","group name")){
            for (var header:List.of("","group header")){
                for (var footer:List.of("","group header")){
                    result.add(new Group(name, header, footer));
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new Group(randomString(i*10),randomString(i*10),randomString(i*10)));
        }
        return result;
    }

    /*@ParameterizedTest //пример использования ValueSource, как альтернативный вариант передачи данных в параметр - значения только фиксированные
    @ValueSource(strings = {"test group name", "test group name'"})
    public void canCreateGroup(String name) {
        int groupCount=app.groups().getCount();
        app.groups().createGroup(new Group(name, "test group header 1", "test group footer 1"));
        Assertions.assertEquals(groupCount+1,app.groups().getCount());
    }*/
    @ParameterizedTest
    @MethodSource("groupProvider")
    public void canCreateMultipleGroups(Group group) {
        int groupCount=app.groups().getCount();
        app.groups().createGroup(group);
        Assertions.assertEquals(groupCount+1,app.groups().getCount());
    }
    public static List<Group> negativeGroupProvider() {
        var result=new ArrayList<Group>(List.of(new Group("test group name'","","")));
        return result;
    }
    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void canNotCreateGroup(Group group) {
        int groupCount=app.groups().getCount();
        app.groups().createGroup(group);
        Assertions.assertEquals(groupCount,app.groups().getCount());
    }
}
