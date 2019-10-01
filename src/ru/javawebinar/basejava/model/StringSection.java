package ru.javawebinar.basejava.model;
import java.util.Objects;

public class StringSection extends AbstractSection {
    private final String info;


    public StringSection(String info) {
        Objects.requireNonNull(info, "info must not be null");
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringSection that = (StringSection) o;
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
