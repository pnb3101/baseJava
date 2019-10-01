package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class OrganizationSection extends AbstractSection {
    private final List<Organization> info;

    public OrganizationSection(List<Organization> info) {
        Objects.requireNonNull(info, "organization must not be null");
        this.info = info;
    }

    public List<Organization> getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return "OrganizationData{" +
                "info=" + info +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationSection that = (OrganizationSection) o;
        return info.equals(that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(info);
    }
}