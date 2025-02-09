package manager;

import model.Group;
import org.openqa.selenium.By;

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
    public void removeGroup() {
        openGroupsPage();
        selectGroup();
        removeSelectedGroups();
        returnToGroupsPage();
    }
    public void modifyGroup(Group modifiedGroup) {
        openGroupsPage();
        selectGroup();
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

    private void selectGroup() {
        click(By.name("selected[]"));
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
}
