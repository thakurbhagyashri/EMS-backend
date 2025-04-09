package com.example.ems.service;

public interface EmailService {

    void sendEmailWithHtml(String to, String subject, String htmlContent);
}
