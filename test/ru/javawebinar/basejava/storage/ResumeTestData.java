package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.*;

import java.time.YearMonth;

public class ResumeTestData {
    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";
    protected static final String UUID_4 = "uuid4";

    protected static final Resume RESUME_1;
    protected static final Resume RESUME_2;
    protected static final Resume RESUME_3;
    protected static final Resume RESUME_4;

    static {
        RESUME_1 = new Resume(UUID_1, "Name1");
        RESUME_2 = new Resume(UUID_2, "Name2");
        RESUME_3 = new Resume(UUID_3, "Name3");
        RESUME_4 = new Resume(UUID_4, "Григорий Кислин");

        RESUME_4.addContact(ContactType.PHONE_NUMBER, "+7(921) 855-0482");
        RESUME_4.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
        RESUME_4.addContact(ContactType.LINKED_PROFILE, "Профиль LinkedIn");
        RESUME_4.addContact(ContactType.GITHUB_PROFILE, "Профиль LinkedIn");
        RESUME_4.addContact(ContactType.STACKOVERFLOW_PROFILE, "Профиль Stackoverflow");
        RESUME_4.addContact(ContactType.HOME_PAGE, "Домашняя страница");

        RESUME_4.addSection(SectionType.OBJECTIVE, new StringSection("Позиция\\n\" +\n" +
                "                \"Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));

        RESUME_4.addSection(SectionType.PERSONAL, new StringSection("Личные качества\n" +
                "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));

        RESUME_4.addSection(SectionType.ACHIEVEMENT,
                new ListSection("Достижения\n",
                        "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven.\n " +
                                "Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\".\n " +
                                "Организация онлайн стажировок и ведение проектов. Более 1000 выпускников. ",
                        "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio,\n" +
                                " DuoSecurity, Google Authenticator, Jira, Zendesk. ",
                        "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM,\n" +
                                " CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO\n " +
                                "аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера. ",
                        "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT,\n " +
                                "ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга. ",
                        "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура,\n " +
                                "JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios.\n " +
                                "Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django). ",
                        "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay,\n Сбербанк)," +
                                " Белоруcсии(Erip, Osmp) и Никарагуа."));

        RESUME_4.addSection(SectionType.QUALIFICATIONS,
                new ListSection("Квалификация\n",
                        "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2 ",
                        "Version control: Subversion, Git, Mercury, ClearCase, Perforce",
                        "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, ",
                        "MySQL, SQLite, MS SQL, HSQLDB ",
                        "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy, ",
                        "XML/XSD/XSLT, SQL, C/C++, Unix shell scripts, ",
                        "Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA " +
                                "(Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit," +
                                " Selenium (htmlelements). ", "Python: Django. ",
                        "JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js ",
                        "Scala: SBT, Play2, Specs2, Anorm, Spray, Akka ",
                        "Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX," +
                                " JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT. ",
                        "Инструменты: Maven + plugin development, Gradle, настройка Ngnix, ",
                        "администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita," +
                                " pgBouncer. ",
                        "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML,"
                                + " функционального программирования ",
                        "Родной русский, английский \"upper intermediate\""));

        RESUME_4.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Java Online Projects\n", "Автор проекта", YearMonth.parse("2013-10"), YearMonth.now(),
                                "Создание, организация" + " и проведение Java онлайн проектов и стажировок."),
                        new Organization("Wrike\n", "Старший разработчик (backend)", YearMonth.parse("2014-10"),
                                YearMonth.parse("2016-01"), "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring,\n" +
                                " MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1,\n" +
                                " OAuth2, JWT SSO."),
                        new Organization("RIT Center\n", "Java архитектор", YearMonth.parse("2012-04"),
                                YearMonth.parse("2014-10"), "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование,\n" +
                                " ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx),\n" +
                                " AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS,\n" +
                                " BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco\n" +
                                " JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache\n" +
                                " Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell\n" +
                                " remote scripting via ssh tunnels, PL/Python"),
                        new Organization("Luxoft (Deutsche Bank)\n", "Ведущий программист", YearMonth.parse("2010-12"),
                                YearMonth.parse("2012-04"), "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper,\n" +
                                " Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для \n" +
                                "администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring,\n" +
                                " Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5."),
                        new Organization("Yota\n", "Ведущий специалист", YearMonth.parse("2008-06"),
                                YearMonth.parse("2010-12"), "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J,\n" +
                                " EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и\n" +
                                " мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)"),
                        new Organization("Enkata\n", "Разработчик ПО", YearMonth.parse("2007-03"),
                                YearMonth.parse("2008-06"), "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного\n" +
                                " J2EE приложения (OLAP, Data mining)."),
                        new Organization("Siemens AG\n", "Разработчик ПО", YearMonth.parse("2005-01"),
                                YearMonth.parse("2007-02"), "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной\n" +
                                " IN платформе Siemens @vantage (Java, Unix)."),
                        new Organization("Alcatel\n", "Инженер по аппаратному и программному тестированию", YearMonth.parse("1997-09"),
                                YearMonth.parse("2005-01"), "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12" +
                                " (CHILL, ASM).")
                ));

        RESUME_4.addSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Coursera\n", "", YearMonth.parse("2013-03"),
                                YearMonth.parse("2013-05"), "\"Functional Programming Principles in Scala\" by Martin Odersky"),
                        new Organization("Luxoft\n", "", YearMonth.parse("2011-03"),
                                YearMonth.parse("2011-04"), "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\""),
                        new Organization("Siemens AG\n", "", YearMonth.parse("2005-01"),
                                YearMonth.parse("2005-04"), "3 месяца обучения мобильным IN сетям (Берлин)"),
                        new Organization("Alcatel\n", "", YearMonth.parse("1997-09"),
                                YearMonth.parse("1998-03"), "6 месяцев обучения цифровым телефонным сетям (Москва)"),
                        new Organization("Санкт-Петербургский национальный исследовательский университет информационных " +
                                "технологий, механики и оптики\n", "", YearMonth.parse("1993-09"),
                                YearMonth.parse("1996-07"), "Аспирантура (программист С, С++)"),
                        new Organization("", YearMonth.parse("1987-09"),
                                YearMonth.parse("1993-07"), "Инженер (программист Fortran, C)"),
                        new Organization("Заочная физико-техническая школа при МФТИ\n", "", YearMonth.parse("1984-09"),
                                YearMonth.parse("1987-06"), "Закончил с отличием")));
    }
}
