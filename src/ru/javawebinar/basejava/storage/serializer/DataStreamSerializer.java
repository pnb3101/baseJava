package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamStrategy {

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)){
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
            for(Map.Entry<ContactType, String> entry : contacts.entrySet()){
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            Map<SectionType, AbstractSection> sections = resume.getSections();
            dos.writeInt(sections.size());
            for(Map.Entry<SectionType, AbstractSection> entry : sections.entrySet()){
                dos.writeUTF(entry.getKey().name());
                AbstractSection section = entry.getValue();
                if(section.getClass().equals(StringSection.class)){
                    dos.writeUTF(StringSection.class.toString());
                    lengthStringSection(dos, (StringSection)section);
                    write(dos, (StringSection)section);
                } else if(section.getClass().equals(ListSection.class)){
                    dos.writeUTF(ListSection.class.toString());
                    lengthListSection(dos, (ListSection)section);
                    write(dos, (ListSection)section);
                }else {
                    dos.writeUTF(OrganizationSection.class.toString());
                    lengthOrganizationSection(dos, (OrganizationSection)section);
                    write(dos, (OrganizationSection)section);
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for(int i = 0; i<size; i++){
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            int sizeSections = dis.readInt();
            if(sizeSections!=0) {
                String section = dis.readUTF();
                if (section.equals(StringSection.class.toString())) {
                    String sectionType = dis.readUTF();
                    int sizeSection = dis.readInt();
                    if (sizeSection != 0) {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < sizeSection; i++) {
                            sb.append(dis.readUTF());
                        }
                        StringSection stringSection = new StringSection(sb.toString());
                        resume.addSection(SectionType.valueOf(sectionType), stringSection);
                    }

                } else if (section.equals(ListSection.class.toString())) {
                    String sectionType = dis.readUTF();
                    int sizeSection = dis.readInt();
                    if (sizeSection != 0) {
                        List<String> list = new ArrayList<>();
                        for (int i = 0; i < sizeSection; i++) {
                            list.add(dis.readUTF());
                        }
                        ListSection listSection = new ListSection(list);
                        resume.addSection(SectionType.valueOf(sectionType), listSection);
                    }

                } else {
                    String sectionType = dis.readUTF();
                    int sizeSection = dis.readInt();
                    if (sizeSection != 0) {
                        List<Organization> list = new ArrayList<>();
                        for (int i = 0; i < sizeSection; i++) {
                             String link = dis.readUTF();
                             String position = dis.readUTF();
                             YearMonth dateOfStart = YearMonth.parse(dis.readUTF());
                             YearMonth dateOfFinish = YearMonth.parse(dis.readUTF());
                             String info = dis.readUTF();
                             int endOrganizationInfo = dis.readInt();
                             if(endOrganizationInfo==i) {
                                 list.add(new Organization(link, position, dateOfStart, dateOfFinish, info));
                             continue;
                             }
                        }
                        resume.addSection(SectionType.valueOf(sectionType), new OrganizationSection(list));
                    }
                }
            }
            return resume;
        }
    }
    private void write(DataOutputStream dos, StringSection section) throws IOException {
        dos.writeUTF(section.getInfo());
    }
    private void lengthStringSection (DataOutputStream dos, StringSection section) throws IOException {
        dos.writeInt(section.getInfo().length());
    }

    private void write(DataOutputStream dos, ListSection section) throws IOException {
        List<String> info = section.getInfo();
        for(String str : info){
            dos.writeUTF(str);
        }
    }
    private void lengthListSection (DataOutputStream dos, ListSection section) throws IOException {
        dos.writeInt(section.getInfo().size());
    }
    private void write(DataOutputStream dos, OrganizationSection section) throws IOException {
        List<Organization> organizations = section.getOrganizatios();
       /* for(Organization org : organizations){
            dos.writeUTF(org.getLink());
            dos.writeUTF(org.getPosition());
            dos.writeUTF(org.getDateOfStart().toString());
            dos.writeUTF(org.getDateOfFinish().toString());
            dos.writeUTF(org.getInfo());
        }*/
       for(int i =0; i< organizations.size(); i++){
           dos.writeUTF(organizations.get(i).getLink());
           dos.writeUTF(organizations.get(i).getPosition());
           dos.writeUTF(organizations.get(i).getDateOfStart().toString());
           dos.writeUTF(organizations.get(i).getDateOfFinish().toString());
           dos.writeUTF(organizations.get(i).getInfo());
           dos.writeInt(i);
       }
    }
    private void lengthOrganizationSection (DataOutputStream dos, OrganizationSection section) throws IOException {
        dos.writeInt(section.getOrganizatios().size());
    }
}
