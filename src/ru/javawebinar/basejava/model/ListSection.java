package ru.javawebinar.basejava.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class ListSection extends AbstractSection {
    private List<String> info;

    public ListSection(String... info){
        this(Arrays.asList(info));
    }

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
