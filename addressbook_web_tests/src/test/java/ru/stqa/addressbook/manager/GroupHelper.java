package ru.stqa.addressbook.manager;

import ru.stqa.addressbook.model.Group;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

//Помощник по работе с группами (Создание, удаление, редактирование)
public class GroupHelper extends HelperBase{
    public GroupHelper(ApplicationManager manager) {
        super(manager);
    }
    public void createGroup(Group group) {
        openGroupsPage();
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupsPage();
    }
    public void removeGroup(Group group) { //доработали метод, передавая объект группу, которую хотим удалить
        openGroupsPage();
        selectGroup(group); //передали в выбор группы аргумент функции
        removeSelectedGroups();
        returnToGroupsPage();
    }
    public void modifyGroup(Group group, Group modifiedGroup) {
        openGroupsPage();
        selectGroup(group);
        initGroupModification();
        fillGroupForm(modifiedGroup);
        submitGroupModification();
        returnToGroupsPage();
    }
    public void removeAllGroups() {
        openGroupsPage();
        selectAllGroups();
        removeSelectedGroups();
    }
    /*public void createMultipleGroups(int numberOfGroups) {
        openGroupsPage();
        for (int i = 0; i < numberOfGroups; i++) {
            initGroupCreation();
            fillGroupForm(new Group());
            submitGroupCreation();
            returnToGroupsPage();
        }
    }*/
    public void openGroupsPage() {
        if (!manager.isElementPresent(By.name("new"))) { //проверка того, не находимся ли уже на странице с группами
            click(By.linkText("groups"));
        }
    }
    public boolean isGroupPresent() {
        openGroupsPage();
        return manager.isElementPresent(By.name(("selected[]")));
    }

    private void submitGroupCreation() {
        click(By.name("submit"));
    }

    private void initGroupCreation() {
        click(By.name("new"));
    }

    private void removeSelectedGroups() {
        click(By.name("delete"));
    }

    private void returnToGroupsPage() {
        click(By.linkText("group page"));
    }

    private void submitGroupModification() {
        click(By.name("update"));
    }

    private void fillGroupForm(Group group) {
        type(By.name("group_name"), group.name());
        type(By.name("group_header"), group.header());
        type(By.name("group_footer"), group.footer());
    }

    private void initGroupModification() {
        click(By.name("edit"));
    }

    private void selectGroup(Group group) {
        click(By.cssSelector(String.format("input[value='%s']", group.id()))); //доработали выбор группы с помощью поиска группы по id через cssSelector
    }

    public int getCount() {
        openGroupsPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    private void selectAllGroups() {
        var checkboxes=manager.driver.findElements(By.name("selected[]"));
        for (var checkbox:checkboxes){
            checkbox.click();
        }
    }

    public List<Group> getList() {
        openGroupsPage();
        var groups= new ArrayList<Group>();
        var spans=manager.driver.findElements(By.cssSelector("span.group"));
        for (var span:spans){
            var name=span.getText();
            var checkbox=span.findElement(By.name("selected[]")); //поиск выполняется только внутри элемента span, а когда вызываем драйвер, то на всей странице
            var id=checkbox.getAttribute("value");
            groups.add(new Group().withName(name).withId(id));
        }
        return groups;
    }
}
