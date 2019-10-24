package com.example.demo_diplom;

public interface KeyStore {
    boolean checkPin(String pin);
    void saveNew(String pin);
}
