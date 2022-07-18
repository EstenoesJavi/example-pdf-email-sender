package com.creatupage.api.example.pdf.rest;

import com.creatupage.api.example.pdf.domain.EmailBodyDto;
import com.creatupage.api.example.pdf.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "email")
public class PdfGenerate {

    private final EmailService emailService;

    @PostMapping
    @RequestMapping(value = "send")
    public void generatePdf(@RequestBody EmailBodyDto emailBodyDto) throws MessagingException {
        this.emailService.sendEmail(emailBodyDto);
    }
}
