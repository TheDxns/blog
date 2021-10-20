package io.github.thedxns.blog.logic;

import org.springframework.stereotype.Service;

import io.github.thedxns.blog.pojos.MailBody;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Service
public class MailService {
    
    private final String apiKey;

    @Autowired
    public MailService(@Value("${sendgrid.key}") String apiKey) {
        this.apiKey = apiKey;
    }

    public boolean sendMail(MailBody mailBody) throws Exception {
        Email from = new Email("denis.lukasczyk@gmail.com");
        String subject = mailBody.getSubject();
        Email to = new Email(mailBody.getRecipient());
        Content content = new Content("text/plain", mailBody.getContent());
        Mail mail = new Mail(from, subject, to, content);
        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();
        try {
          request.setMethod(Method.POST);
          request.setEndpoint("mail/send");
          request.setBody(mail.build());
          Response response = sg.api(request);
          System.out.println(response.getStatusCode());
          System.out.println(response.getBody());
          System.out.println(response.getHeaders());
          return true;
        } catch (IOException ex) {
          throw ex;
        }
        }
}
