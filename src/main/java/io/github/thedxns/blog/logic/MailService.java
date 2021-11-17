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
import com.sendgrid.helpers.mail.objects.Personalization;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    public boolean notifySubscribers(MailBody mailBody) throws Exception {
      SendGrid sg = new SendGrid(apiKey);
      Email from = new Email("denis.lukasczyk@gmail.com");
      String subject = mailBody.getSubject();
      Content content = new Content("text/plain", mailBody.getContent());
      Personalization personalization = new Personalization();
      Request getRequest = new Request();
      getRequest.setMethod(Method.GET);
      getRequest.setEndpoint("/marketing/contacts");
      getRequest.addQueryParam("page", "1");
      getRequest.addQueryParam("page_size", "1");

      Response response = null;
      try {
          response = sg.api(getRequest);
      } catch (IOException e) {
          e.printStackTrace();
      }
      if (response != null) {
          System.out.println(response.getBody());
      }

      JSONObject getResponse = new JSONObject(response);
      JSONObject responseBody = new JSONObject(getResponse.getString("body"));
      JSONArray recipientsData = new JSONArray(responseBody.getJSONArray("result").toString());
      for (int i=0; i<recipientsData.length(); i++) {
        personalization.addTo(new Email(recipientsData.getJSONObject(i).getString("email")));
      }
      
      Mail mail = new Mail();
      mail.addPersonalization(personalization);
      mail.setFrom(from);
      mail.setSubject(subject);
      mail.addContent(content);
      Request postRequest = new Request();
      try {
        postRequest.setMethod(Method.POST);
        postRequest.setEndpoint("mail/send");
        postRequest.setBody(mail.build());
        response = sg.api(postRequest);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
        System.out.println(response.getHeaders());
        return true;
      } catch (IOException ex) {
        throw ex;
      }
  }
}
