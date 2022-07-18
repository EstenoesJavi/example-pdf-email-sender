package com.creatupage.api.example.pdf.service;

import com.creatupage.api.example.pdf.domain.EmailBodyDto;

import javax.mail.MessagingException;

public interface EmailService {

    boolean sendEmail(EmailBodyDto emailBody) throws MessagingException;
}
