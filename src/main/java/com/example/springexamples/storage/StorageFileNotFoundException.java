package com.example.springexamples.storage;

/**
 * @author VKT-HALILU
 * @date 3/5/2020
 */
public class StorageFileNotFoundException extends StorageException {

    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}