package ml.walrus.service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

/**
 * Decompiled from walrus-jar...
 * 
 * @author ml
 * 
 */
public class MailService {
    JavaMailSender mailSender;

    public void sendEmail(String emailTo, String subject, String text,
            String emailFrom, String emailFromName) throws MailException {
        final String body = text;
        final String to = emailTo;
        final String fSubject = subject;
        final String from = emailFrom;
        final String fromName = emailFromName;
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.addHeader("Content-Type",
                        "text/plain; charset=\"UTF-8\"");
                mimeMessage.addHeader("Content-Transfer-Encoding", "8bit");
                mimeMessage.setText(body, "UTF-8");
                mimeMessage.setSubject(fSubject, "UTF-8");
                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(to));
                mimeMessage.setSender(new InternetAddress(from, fromName,
                        "UTF-8"));
                mimeMessage
                        .setFrom(new InternetAddress(from, fromName, "UTF-8"));
            }
        };
        this.mailSender.send(preparator);
    }

    public JavaMailSender getMailSender() {
        return this.mailSender;
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
}