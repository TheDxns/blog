package io.github.thedxns.blog.email;

import org.springframework.stereotype.Service;

import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;

import org.json.JSONArray;
import org.json.JSONObject;

@Service
public class MailService {
    
    private final String apiKey;

    @Autowired
    public MailService(@Value("${sendgrid.key}") String apiKey) {
        this.apiKey = apiKey;
    }

    public boolean sendMail(MailBody mailBody) throws Exception {
        final Email from = new Email("ablogbythedxns@gmail.com");
        final String subject = mailBody.getSubject();
        final Email to = new Email(mailBody.getRecipient());
        final Content content = new Content("text/plain", mailBody.getContent());
        final Mail mail = new Mail(from, subject, to, content);
        final SendGrid sg = new SendGrid(apiKey);
        final Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        final Response response = sg.api(request);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
        System.out.println(response.getHeaders());
        return true;
    }

    @Async
    public Boolean contactSubscribers(final String title, final String recipient,
                                      final String mailContent) throws Exception {
        final MailBody mailBody = new MailBody(title, recipient, mailContent);
        final SendGrid sg = new SendGrid(apiKey);
        final Email from = new Email("ablogbythedxns@gmail.com");
        final String subject = mailBody.getSubject();
        final Content content = new Content("text/html", mailBody.getContent());
        final Personalization personalization = new Personalization();
        final Request getRequest = new Request();
        getRequest.setMethod(Method.GET);
        getRequest.setEndpoint("/marketing/contacts");
        getRequest.addQueryParam("page", "1");
        getRequest.addQueryParam("page_size", "1");

        Response response = new Response();
        try {
          response = sg.api(getRequest);
        } catch (IOException e) {
          e.printStackTrace();
        }
        if (response != null) {
            System.out.println("zero" + response.getBody());
        }

        final JSONObject getResponse = new JSONObject(response);
        final JSONObject responseBody = new JSONObject(getResponse.getString("body"));
        final JSONArray recipientsData = new JSONArray(responseBody.getJSONArray("result").toString());
        for (int i=0; i<recipientsData.length(); i++) {
            personalization.addBcc(new Email(recipientsData.getJSONObject(i).getString("email")));
        }
        personalization.addTo(new Email("ablogbythedxns@gmail.com"));
        final Mail mail = new Mail();
        mail.addPersonalization(personalization);
        mail.setFrom(from);
        mail.setSubject(subject);
        mail.addContent(content);
        final Request postRequest = new Request();
        postRequest.setMethod(Method.POST);
        postRequest.setEndpoint("mail/send");
        postRequest.setBody(mail.build());
        response = sg.api(postRequest);
        System.out.println(response.getStatusCode() + "one");
        System.out.println(response.getBody() + "two");
        System.out.println(response.getHeaders() + "three");
        return true;
    }
}
