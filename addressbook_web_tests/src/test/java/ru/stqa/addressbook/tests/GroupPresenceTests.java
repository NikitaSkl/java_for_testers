package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.Group;

import java.util.ArrayList;
import java.util.Comparator;


public class GroupPresenceTests extends TestBase{
    @Test
    public void sameGroupsPresence() {
        var uiGroups=app.groups().getList();
        var dbGroups=app.jdbc().getGroupList();
        for (int i = 0; i < dbGroups.size(); i++) {
            dbGroups.set(i,dbGroups.get(i).withFooter("").withHeader(""));
        }
        Comparator<Group> groupComparatorById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        uiGroups.sort(groupComparatorById);
        dbGroups.sort(groupComparatorById);
        Assertions.assertEquals(uiGroups,dbGroups);
    }
}
