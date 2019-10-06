package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.*;

import java.time.YearMonth;

public class ResumeTestData {

    static Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        resume.addContact(ContactType.PHONE_NUMBER, "9101500202");
        resume.addContact(ContactType.EMAIL, "personalmail@gmail.com");
        resume.addSection(SectionType.PERSONAL, new StringSection("responsible"));
        resume.addSection(SectionType.ACHIEVEMENT, new ListSection("Integration with Twiliio"));
        resume.addSection(SectionType.EXPERIENCE, new OrganizationSection(new Organization("www.company.com",
                "engineer", YearMonth.parse("2005-10"), YearMonth.parse("2015-02"), "working on different projects")));
        return resume;
    }
}
