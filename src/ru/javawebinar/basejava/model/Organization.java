package ru.javawebinar.basejava.model;

import ru.javawebinar.basejava.util.YearMonthAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    private Link homePage;
    private List<Position> positions = new ArrayList<>();
    public static final Organization EMPTY = new Organization("", "", Position.EMPTY);

    public Organization(String name, String url, Position... positions) {
        this(new Link(name, url), Arrays.asList(positions));
    }

    public Organization(Link homePage, List<Position> positions) {
        this.homePage = homePage;
        this.positions = positions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(homePage, that.homePage) &&
                Objects.equals(positions, that.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, positions);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homePage=" + homePage +
                ", positions=" + positions +
                '}';
    }

    public Link getHomePage() {
        return homePage;
    }

    public List<Position> getPositions() {
        return positions;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Position implements Serializable {
        private String position;
        @XmlJavaTypeAdapter(YearMonthAdapter.class)
        private YearMonth dateOfStart;
        @XmlJavaTypeAdapter(YearMonthAdapter.class)
        private YearMonth dateOfFinish;
        private String info;
        public static final Position EMPTY = new Position();

        public Position() {
        }

        public Position(String position, YearMonth dateOfStart, YearMonth dateOfFinish, String info) {
            Objects.requireNonNull(dateOfStart, "dateOfStart must not be null");
            Objects.requireNonNull(dateOfFinish, "dateOfFinish must not be null");
            Objects.requireNonNull(position, "position must not be null");
            this.position = position;
            this.dateOfStart = dateOfStart;
            this.dateOfFinish = dateOfFinish;
            this.info = info;
        }

        public String getPosition() {
            return position;
        }

        public YearMonth getDateOfStart() {
            return dateOfStart;
        }

        public YearMonth getDateOfFinish() {
            return dateOfFinish;
        }

        public String getInfo() {
            return info;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position1 = (Position) o;
            return position.equals(position1.position) &&
                    dateOfStart.equals(position1.dateOfStart) &&
                    dateOfFinish.equals(position1.dateOfFinish) &&
                    Objects.equals(info, position1.info);
        }

        @Override
        public int hashCode() {
            return Objects.hash(position, dateOfStart, dateOfFinish, info);
        }

        @Override
        public String toString() {
            return "Position{" +
                    "position='" + position + '\'' +
                    ", dateOfStart=" + dateOfStart +
                    ", dateOfFinish=" + dateOfFinish +
                    ", info='" + info + '\'' +
                    '}';
        }
    }
}
