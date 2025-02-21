package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

    public static List<Group> groupProvider() {
        var result=new ArrayList<Group>();
        for (var name:List.of("","group name")){
            for (var header:List.of("","group header")){
                for (var footer:List.of("","group header")){
                    result.add(new Group().withName(name).withHeader(header).withFooter(footer));
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new Group().withName(CommonFunctions.randomString(i*10)).withFooter(CommonFunctions.randomString(i*10)).withHeader(CommonFunctions.randomString(i*10)));
        }
        return result;
    }
    @ParameterizedTest
    @MethodSource("groupProvider")
    public void canCreateMultipleGroups(Group group) {
        var oldGroups=app.groups().getList();
        app.groups().createGroup(group);
        var newGroups=app.groups().getList();
        var expectedGroups=new ArrayList<Group>(oldGroups);
        Comparator<Group> groupComparatorById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(groupComparatorById);
        expectedGroups.add(group.withId(newGroups.get(newGroups.size()-1).id()).withHeader("").withFooter("")); // добавляем объект группу, созданный провайдером, где могут быть заполнены любые поля объекта, а с помощью getList() забираем только имя и id, но сравнение объектов группа происходит по всем полям, поэтому тесты падают
        expectedGroups.sort(groupComparatorById);
        Assertions.assertEquals(expectedGroups,newGroups);
    }
    public static List<Group> negativeGroupProvider() {
        var result=new ArrayList<Group>(List.of(new Group("", "test group name'","","")));
        return result;
    }
    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void canNotCreateGroup(Group group) {
        var oldGroups=app.groups().getList();
        app.groups().createGroup(group);
        var newGroups=app.groups().getList();
        Assertions.assertEquals(oldGroups,newGroups);
    }
    /*@ParameterizedTest //пример использования ValueSource, как альтернативный вариант передачи данных в параметр - значения только фиксированные
    @ValueSource(strings = {"test group name", "test group name'"})
    public void canCreateGroup(String name) {
        int groupCount=app.groups().getCount();
        app.groups().createGroup(new Group(name, "test group header 1", "test group footer 1"));
        Assertions.assertEquals(groupCount+1,app.groups().getCount());
    }*/
}
