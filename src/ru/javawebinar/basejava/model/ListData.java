package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class ListData extends AbstractSection {
    private final List<String> info;

    public ListData(List<String> info) {
        Objects.requireNonNull(info, "section must not be null");
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
        ListData that = (ListData) o;
        return info.equals(that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(info);
    }
}
