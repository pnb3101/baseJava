package ru.javawebinar.basejava.model;

import java.util.Objects;

public class StringsData extends AbstractSection {
    private final String info;


    public StringsData(String info) {
        Objects.requireNonNull(info, "section must not be null");
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringsData that = (StringsData) o;
        return info.equals(that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(info);
    }

    @Override
    public String toString() {
        return "StringsData{" +
                "info='" + info + '\'' +
                '}';
    }
}
