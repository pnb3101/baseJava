package ru.javawebinar.basejava.model;

import java.time.YearMonth;
import java.util.Objects;

public class Organization {
    private String link;
    private final String position;
    private final YearMonth dateOfStart;
    private final YearMonth dateOfFinish;
    private final String info;

    public Organization(String link, String position, YearMonth dateOfStart, YearMonth dateOfFinish, String info) {
        this.link = link;
        this.position = position;
        this.dateOfStart = dateOfStart;
        this.dateOfFinish = dateOfFinish;
        this.info = info;
    }

    public Organization(String position, YearMonth dateOfStart, YearMonth dateOfFinish, String info) {
        this.position = position;
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
                position.equals(that.position) &&
                dateOfStart.equals(that.dateOfStart) &&
                dateOfFinish.equals(that.dateOfFinish) &&
                info.equals(that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link, position, dateOfStart, dateOfFinish, info);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "link='" + link + '\'' +
                ", title='" + position + '\'' +
                ", dateOfStart=" + dateOfStart +
                ", dateOfFinish=" + dateOfFinish +
                ", info='" + info + '\'' +
                '}';
    }
}
