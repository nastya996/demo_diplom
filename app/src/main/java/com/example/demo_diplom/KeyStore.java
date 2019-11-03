package com.example.demo_diplom;

public interface KeyStore {

    boolean hasPassword();

    boolean checkPassword(String password);

    void saveNewPassword(String password);
}
