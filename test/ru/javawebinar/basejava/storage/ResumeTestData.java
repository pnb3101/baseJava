package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.*;

import java.time.YearMonth;

public class ResumeTestData {

    static Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);

        resume.addContact(ContactType.PHONE_NUMBER, "+7(921) 855-0482");
        resume.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
        resume.addContact(ContactType.GITHUB_PROFILE, "Профиль LinkedIn");
        resume.addContact(ContactType.STACKOVERFLOW_PROFILE, "Профиль Stackoverflow");
        resume.addContact(ContactType.HOME_PAGE, "Домашняя страница");
        resume.addSection(SectionType.OBJECTIVE, new StringSection("Позиция\n" +
                "                \"Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        resume.addSection(SectionType.PERSONAL, new StringSection("Личные качества\n" +
                "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        resume.addSection(SectionType.ACHIEVEMENT,
                new ListSection("Достижения\n",
                        "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven.\n " +
                                "Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\".\n " +
                                "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay,\n Сбербанк)," +
                                " Белоруcсии(Erip, Osmp) и Никарагуа."));

        resume.addSection(SectionType.QUALIFICATIONS,
                new ListSection("Квалификация\n",
                        "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2 ",
                        "Version control: Subversion, Git, Mercury, ClearCase, Perforce",
                        "Родной русский, английский \"upper intermediate\""));

        resume.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Java Online Projects\n", "https://www.javaonprojects.ru",
                                new Organization.Position("автор курса", YearMonth.parse("2013-10"), YearMonth.now(),
                                "Создание, организация и проведение Java онлайн проектов и стажировок."))));

        resume.addSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Coursera\n", "", new Organization.Position("слушатель", YearMonth.parse("2013-03"),
                                YearMonth.parse("2013-05"), "\"Functional Programming Principles in Scala\" by Martin Odersky")),
                        new Organization("Заочная физико-техническая школа при МФТИ\n", "",
                                new Organization.Position("аспирант", YearMonth.parse("1984-09"),
                                YearMonth.parse("1987-06"), "Закончил с отличием"))));
        return resume;

    }
}
