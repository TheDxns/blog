package io.github.thedxns.blog;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import io.github.thedxns.blog.controller.MailController;
import io.github.thedxns.blog.logic.MailService;
import io.github.thedxns.blog.pojos.MailBody;

@SpringBootTest
public class MailControllerTests {
    @Mock
	private MailController mailController;

	@Test
    @DisplayName("The context should not be null")
	public void contextLoads() throws Exception {
		Assertions.assertNotNull(mailController);
    }

    @Test
    @DisplayName("Send mail endpoint should return 200 if the mail was sent")
    public void sendMailWhenTheMailWasSentReturns200Response() throws Exception {
        MailService mockMailService = mock(MailService.class);
        when(mockMailService.sendMail(any(MailBody.class))).thenReturn(true);
        MailController testMailController = new MailController(mockMailService);
        Assertions.assertEquals(ResponseEntity.ok().build(), testMailController.contact(new MailBody()));
    }

    @Test
    @DisplayName("Send mail endpoint should return 500 if the mail not was sent")
    public void sendMailWhenTheMailWasNotSentReturns500Response() throws Exception {
        MailService mockMailService = mock(MailService.class);
        when(mockMailService.sendMail(any(MailBody.class))).thenReturn(false);
        MailController testMailController = new MailController(mockMailService);
        Assertions.assertEquals(ResponseEntity.internalServerError().build(), testMailController.contact(new MailBody()));
    }

    @Test
    @DisplayName("Contact subscribers endpoint should return 200 if the mail was sent")
    public void contactSubscribersWhenTheMailWasSentReturns200Response() throws Exception {
        MailService mockMailService = mock(MailService.class);
        when(mockMailService.contactSubscribers(any(MailBody.class))).thenReturn(true);
        MailController testMailController = new MailController(mockMailService);
        Assertions.assertEquals(ResponseEntity.ok().build(), testMailController.contactSubscribers());
    }

    @Test
    @DisplayName("Contact subscribers endpoint should return 500 if the mail not was sent")
    public void contactSubscribersWhenTheMailWasNotSentReturns500Response() throws Exception {
        MailService mockMailService = mock(MailService.class);
        when(mockMailService.contactSubscribers(any(MailBody.class))).thenReturn(false);
        MailController testMailController = new MailController(mockMailService);
        Assertions.assertEquals(ResponseEntity.internalServerError().build(), testMailController.contactSubscribers());
    }
}