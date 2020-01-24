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
                        dos.writeUTF(((StringSection) section).getInfo());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writer(dos, ((ListSection) section).getInfo(), dos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writer(dos, ((OrganizationSection) section).getOrganizations(), org -> {
                            dos.writeUTF(org.getHomePage().getName());
                            if (org.getHomePage().getUrl() != null) {
                                dos.writeUTF(org.getHomePage().getUrl());
                            } else {
                                dos.writeUTF("");
                            }
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
            readWithException(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));

            readWithException(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resume.addSection(sectionType, new StringSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        resume.addSection(sectionType, new ListSection(readList(dis, () -> dis.readUTF())));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> listOrg = new ArrayList<>();
                        readWithException(dis, () -> listOrg.add(new Organization(new Link(dis.readUTF(), dis.readUTF()),
                                readList(dis, () -> new Organization.Position(dis.readUTF(), YearMonth.parse(dis.readUTF()),
                                        YearMonth.parse(dis.readUTF()), dis.readUTF()))
                        )));
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

    private interface ReaderWithType<T> {
        T readElement() throws IOException;
    }

    private interface Reader {
        void read() throws IOException;
    }

    private void readWithException(DataInputStream dis, Reader reader) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            reader.read();
        }
    }

    private <T> List<T> readList(DataInputStream dis, ReaderWithType<T> reader) throws IOException {
        List<T> list = new ArrayList<>();
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            list.add(reader.readElement());
        }
        return list;
    }
}

