package ru.stqa.addressbook.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

    public static List<Group> groupProvider() throws IOException {
        var result=new ArrayList<Group>();
        for (var name:List.of("","group name")){
            for (var header:List.of("","group header")){
                for (var footer:List.of("","group header")){
                    result.add(new Group().withName(name).withHeader(header).withFooter(footer));
                }
            }
        }

//        var json="";
//        try (var reader=new FileReader("groups.json");
//        var breader=new BufferedReader(reader)){
//            var line = breader.readLine();
//            while (line!=null){
//                json=json+line;
//                line = breader.readLine();
//            }
//        }

        //var json= Files.readString(Path.of("groups.json")); //для чтения файла формата json
        //var mapper = new ObjectMapper(); //для чтения файла формата json
        var xml=Files.readString(Path.of("groups.xml"));;
        var mapper=new XmlMapper();
        var value = mapper.readValue(xml, new TypeReference<List<Group>>() {}); //TypeRef - класс без реализации, только с декларацией - в {} его реализация (пустой)
        result.addAll(value);
        return result;
    }
    public static List<Group> singleRandomGroup() throws IOException {
        return List.of(new Group().withName(CommonFunctions.randomString(10))
                .withHeader(CommonFunctions.randomString(15))
                .withFooter(CommonFunctions.randomString(20)));
    }
    @ParameterizedTest
    @MethodSource("singleRandomGroup")
    public void canCreateGroup(Group group) {
        //var oldGroups=app.groups().getList();
        var oldGroups=app.jdbc().getGroupList();
        app.groups().createGroup(group);
        //var newGroups=app.groups().getList();
        var newGroups=app.jdbc().getGroupList();
        var expectedGroups=new ArrayList<Group>(oldGroups);
        Comparator<Group> groupComparatorById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(groupComparatorById);
        var maxId=newGroups.get(newGroups.size()-1).id();

        //expectedGroups.add(group.withId(maxId).withHeader("").withFooter("")); // добавляем объект группу, созданный провайдером, где могут быть заполнены любые поля объекта, а с помощью getList() забираем только имя и id, но сравнение объектов группа происходит по всем полям, поэтому тесты падают
        expectedGroups.add(group.withId(maxId));
        expectedGroups.sort(groupComparatorById);
        Assertions.assertEquals(expectedGroups,newGroups);

        var newUiGroups=app.groups().getList();
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
