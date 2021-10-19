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

@Service
public class MailService {
    
    public boolean sendMail(MailBody mailBody) throws Exception {
        Email from = new Email("denis.lukasczyk@gmail.com");
        String subject = mailBody.getSubject();
        Email to = new Email(mailBody.getRecipient());
        Content content = new Content("text/plain", mailBody.getContent());
        Mail mail = new Mail(from, subject, to, content);
        SendGrid sg = new SendGrid("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");
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
