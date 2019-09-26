package ru.javawebinar.basejava.model;

public enum ContactType {
    PHONE_NUMBER("Тел."),
    SKYPE("Skype"),
    EMAIL("Элуктронная почта"),
    LINKED_PROFILE("Профиль LinkedIn"),
    GITHUB_PROFILE("Профиль GitHub"),
    STACKOVERFLOW_PROFILE("Профиль Stackoverflow"),
    HOME_PAGE("Домашняя страница");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
