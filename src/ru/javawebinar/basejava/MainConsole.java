package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.util.Map;

public class MainConsole {

    private static Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);

        resume.addContact(ContactType.PHONE_NUMBER, "+7(921) 856-0482");
        resume.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
        resume.addContact(ContactType.GITHUB_PROFILE, "Профиль LinkedIn");
        resume.addContact(ContactType.STACKOVERFLOW_PROFILE, "Профиль Stackoverflow");
        resume.addContact(ContactType.HOME_PAGE, "Домашняя страница");
        resume.addSection(SectionType.OBJECTIVE, new StringSection("Позиция\n" +
                "                \"Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        resume.addSection(SectionType.PERSONAL, new StringSection("Личные качества\n" +
                "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        return resume;
    }
    public static void main(String[] args) {
    Resume resume = createResume("uuid_1", "Name1");
   /* private static final Resume RESUME_2 = createResume(UUID_2, "Name2");
    private static final Resume RESUME_3 = createResume(UUID_3, "Name3");
    private static final Resume RESUME_4 = createResume(UUID_4, "Григорий Кислин");*/

    Map<SectionType, AbstractSection> sections = resume.getSections();

            for (Map.Entry<SectionType, AbstractSection> entry : sections.entrySet()) {
                AbstractSection section = entry.getValue();
            if (section.getClass().equals(StringSection.class)) {
                System.out.println(section.getClass());
                System.out.println(StringSection.class.toString());
                System.out.println("true");
            }
           /* dos.writeUTF(StringSection.class.toString());
            lengthStringSection(dos, (StringSection) section);
            write(dos, (StringSection) section);
        } else if (section.getClass().equals(ListSection.class)) {
            dos.writeUTF(ListSection.class.toString());
            lengthListSection(dos, (ListSection) section);
            write(dos, (ListSection) section);
        } else {
            dos.writeUTF(OrganizationSection.class.toString());
            lengthOrganizationSection(dos, (OrganizationSection) section);
            write(dos, (OrganizationSection) section);*/
        }
    }
}
