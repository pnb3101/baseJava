package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {
    private final List<String> info;

    public ListSection(List<String> info) {
        Objects.requireNonNull(info, "info must not be null");
        this.info = info;
    }

    public List<String> getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return "ListData{" +
                "info=" + info +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return info.equals(that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(info);
    }
}
