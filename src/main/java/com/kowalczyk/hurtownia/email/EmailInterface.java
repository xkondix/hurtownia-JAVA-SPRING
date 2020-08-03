package com.kowalczyk.hurtownia.email;

public interface EmailInterface {
    void sendEmail(String to, String title, String content);
}
