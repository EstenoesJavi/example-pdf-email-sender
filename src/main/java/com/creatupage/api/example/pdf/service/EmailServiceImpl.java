package com.creatupage.api.example.pdf.service;

import com.creatupage.api.example.pdf.domain.EmailBodyDto;
import com.creatupage.api.example.pdf.util.GeneratePdf;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{

        private final JavaMailSender sender;
        private final GeneratePdf generatePdf;

        @Override
        public boolean sendEmail(EmailBodyDto emailBody) throws MessagingException {
            log.info("EmailBody: {}",emailBody.toString());
            return sendEmailTool(emailBody.getContent(),emailBody.getEmail(), emailBody.getSubject());
        }
        private boolean sendEmailTool(String textMessage, String email,String subject) throws MessagingException {
            boolean send = false;
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            try {
                helper.setTo(email);
                helper.setText(textMessage, true);
                helper.setSubject(subject);
                FileSystemResource file
                        = new FileSystemResource(new File("src/main/resources/ListadeProductos.pdf"));
                helper.addAttachment("Lista de productos", file);
                this.generatePdf.generate();
                //sender.send(message);
                send = true;
                log.info("Mail enviado!");
                /*boolean result = Files.deleteIfExists(Paths.get("src/main/resources/ListadeProductos.pdf"));
                if(result){
                    log.info("Eliminado con exito");
                }else {
                    log.info("No se encontró el archivo o no pudo eliminarse");
                }*/
            } catch (MessagingException e) {
                log.error("Hubo un error al enviar el mail: {}", e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return send;
        }

}
