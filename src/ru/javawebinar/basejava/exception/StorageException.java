package ru.javawebinar.basejava.exception;

public class StorageException extends RuntimeException {
    private String uuid;

    public StorageException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }
    public StorageException(String message, Exception e) {
        super(message);
    }

    public StorageException(String message, String uuid, Exception e) {
        super(message);
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }
}
