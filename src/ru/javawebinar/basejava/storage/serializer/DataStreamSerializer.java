package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamStrategy {

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            writer(dos, contacts.entrySet(), entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });

            writer(dos, resume.getSections().entrySet(), entry -> {
                SectionType sectionType = entry.getKey();
                dos.writeUTF(sectionType.name());
                AbstractSection section = entry.getValue();
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeInt(((StringSection) section).getInfo().length());
                        dos.writeUTF(((StringSection) section).getInfo());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writer(dos, ((ListSection) section).getInfo(), dos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writer(dos, ((OrganizationSection) section).getOrganizatios(), org -> {
                            dos.writeUTF(org.getHomePage().getName());
                            dos.writeUTF(org.getHomePage().getUrl());
                            writer(dos, org.getPositions(), position -> {
                                dos.writeUTF(position.getPosition());
                                dos.writeUTF(position.getDateOfStart().toString());
                                dos.writeUTF(position.getDateOfFinish().toString());
                                if (position.getInfo() != null) {
                                    dos.writeUTF(position.getInfo());
                                } else {
                                    dos.writeUTF("");
                                }
                            });
                        });
                    break;
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int sizeContact = dis.readInt();
            for (int i = 0; i < sizeContact; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            readWithException(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                int sizeSection = dis.readInt();
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        StringSection stringSection = new StringSection(dis.readUTF());
                        resume.addSection(sectionType, stringSection);
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> list = new ArrayList<>();
                        for (int i = 0; i < sizeSection; i++) {
                            list.add(dis.readUTF());
                        }
                        ListSection listSection = new ListSection(list);
                        resume.addSection(sectionType, listSection);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> listOrg = new ArrayList<>();
                        for (int i = 0; i < sizeSection; i++) {
                            String nameOrganization = dis.readUTF();
                            String url = dis.readUTF();
                            int positionsSize = dis.readInt();
                            List<Organization.Position> positions = new ArrayList<>();
                            for (int j = 0; j < positionsSize; j++) {
                                String position = dis.readUTF();
                                YearMonth dateOfStart = YearMonth.parse(dis.readUTF());
                                YearMonth dateOfFinish = YearMonth.parse(dis.readUTF());
                                String info = dis.readUTF();
                                positions.add(new Organization.Position(position, dateOfStart, dateOfFinish, info));
                            }
                            listOrg.add(new Organization(new Link(nameOrganization, url), positions));
                        }
                        resume.addSection(sectionType, new OrganizationSection(listOrg));
                        break;
                    default:
                        throw new StorageException("Problems with read path by DataStreamSerializer", "");
                }
            });
            return resume;
        }
    }

    private interface Writer<T> {
        void write(T section) throws IOException;
    }

    private <T> void writer(DataOutputStream dos, Collection<T> collection, Writer<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            writer.write(item);
        }
    }

    private interface Reader {
        void readElement() throws IOException;
    }

    private void readWithException(DataInputStream dis, Reader reader) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            reader.readElement();
        }
    }
}

