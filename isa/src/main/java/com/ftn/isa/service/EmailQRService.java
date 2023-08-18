package com.ftn.isa.service;

import org.springframework.stereotype.Service;

import com.ftn.isa.model.ScheduleCalendar;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

@Service
public class EmailQRService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailQRService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmailWithQRCode(ScheduleCalendar scheduleCalendar, String recipientEmail) {
        String qrCodeText = generateQRCodeText(scheduleCalendar);
        byte[] qrCodeImageBytes = generateQRCodeImage(qrCodeText);

        try {
            MimeMessage message = createEmailMessage(recipientEmail);
            attachQRCodeToEmail(message, qrCodeImageBytes);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private String generateQRCodeText(ScheduleCalendar scheduleCalendar) {
        return "Schedule Date: " + scheduleCalendar.getDate().toString() + "\n"
                + "Schedule Time: " + scheduleCalendar.getStartTime().toString() + "\n"
                + "Schedule Duration: " + scheduleCalendar.getDuration() + "\n"
                + "Username: " + scheduleCalendar.getUser().getUsername() + "\n";
    }

    private byte[] generateQRCodeImage(String qrCodeText) {
        return QRCode.from(qrCodeText).to(ImageType.PNG).stream().toByteArray();
    }

    private MimeMessage createEmailMessage(String recipientEmail) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(recipientEmail);
        helper.setSubject("Schedule Calendar QR Code");
        helper.setText("Please find the QR code for your schedule calendar attached.");

        return message;
    }

    private void attachQRCodeToEmail(MimeMessage message, byte[] qrCodeImageBytes) throws MessagingException {
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        ByteArrayResource qrCodeResource = new ByteArrayResource(qrCodeImageBytes);
        helper.addAttachment("QRCode.png", qrCodeResource, "image/png");
        helper.setText("", true);
    }
}
