package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Objects;

public class Organization {
    private final String link;
    private final String title;
    private final LocalDate dateOfStart;
    private final LocalDate dateOfFinish;
    private final String info;

    public Organization(String link, String title, LocalDate dateOfStart, LocalDate dateOfFinish, String info) {
        this.link = link;
        this.title = title;
        this.dateOfStart = dateOfStart;
        this.dateOfFinish = dateOfFinish;
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return link.equals(that.link) &&
                title.equals(that.title) &&
                dateOfStart.equals(that.dateOfStart) &&
                dateOfFinish.equals(that.dateOfFinish) &&
                info.equals(that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link, title, dateOfStart, dateOfFinish, info);
    }
}
