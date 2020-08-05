package com.kowalczyk.hurtownia.email;

import com.kowalczyk.hurtownia.model.entities.client.Client;
import com.kowalczyk.hurtownia.model.entities.client.OrderSupply;
import com.kowalczyk.hurtownia.model.entities.wholesalers.Product;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

@Service
public class EmailService implements EmailInterface{

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(String to, String title, String content) {
        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.setReplyTo("firmaxyz59@gmail.com");
            helper.setFrom("firmaxyz59@gmail.com");
            helper.setSubject(title);
            helper.setText(content);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mail);
    }

    public String createContent(Map<Product,Long>  wholesaleProducts
            , OrderSupply orderSupply, Client client) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Product List");
        stringBuilder.append(System.getProperty("line.separator"));

        wholesaleProducts.entrySet().forEach(product ->
        {
            stringBuilder.append(product.getKey().toString());
            stringBuilder.append(" Quantity : ");
            stringBuilder.append(product.getValue());
            stringBuilder.append(System.getProperty("line.separator"));
            stringBuilder.append(" Price : ");
            stringBuilder.append(product.getValue()*product.getKey().getPricePerItem());
            stringBuilder.append("zł");
            stringBuilder.append(System.getProperty("line.separator"));
            stringBuilder.append("------------------------");
            stringBuilder.append(System.getProperty("line.separator"));

        });

        stringBuilder.append("Contact Details");
        stringBuilder.append(System.getProperty("line.separator"));
        stringBuilder.append(client.getAddress());
        stringBuilder.append(System.getProperty("line.separator"));
        stringBuilder.append("Phone number : ");
        stringBuilder.append(client.getContactDetails().getPhoneNumber());
        stringBuilder.append(System.getProperty("line.separator"));
        stringBuilder.append("time : ");
        stringBuilder.append(orderSupply.getCreatedAt());

        return stringBuilder.toString();
    }

}
