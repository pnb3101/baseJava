package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class WorkAndStuding extends Section {
    private final List<Organization> infoAboutWorkAndStuding;

    public WorkAndStuding(List<Organization> infoAboutWorkAndStuding) {
        this.infoAboutWorkAndStuding = infoAboutWorkAndStuding;
    }

    public List<Organization> getInfoAboutWorkAndStuding() {
        return infoAboutWorkAndStuding;
    }

    @Override
    public String toString() {
        return "WorkAndStuding{" +
                "infoAboutWorkAndStuding=" + infoAboutWorkAndStuding +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkAndStuding that = (WorkAndStuding) o;
        return infoAboutWorkAndStuding.equals(that.infoAboutWorkAndStuding);
    }

    @Override
    public int hashCode() {
        return Objects.hash(infoAboutWorkAndStuding);
    }
}
