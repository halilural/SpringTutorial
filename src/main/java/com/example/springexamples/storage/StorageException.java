package com.example.springexamples.storage;

/**
 * @author VKT-HALILU
 * @date 3/5/2020
 */
public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
