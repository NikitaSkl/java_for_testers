package ru.stqa.addressbook.model;

public record Group(String id, String name, String header, String footer) {
    public Group() {
        this("", "", "", "");
    }

    public Group withName(String name) {
        return new Group(this.id, name, this.header, this.footer);
    }

    public Group withHeader(String header) {
        return new Group(this.id, this.name, header, this.footer);
    }

    public Group withFooter(String footer) {
        return new Group(this.id, this.name, this.header, footer);
    } //this.xxx - это поле будет таким же как у текущего объекта (сохранится)

    public Group withId(String id) {
        return new Group(id, this.name, this.header, this.footer);
    }
}
