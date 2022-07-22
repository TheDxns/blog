package io.github.thedxns.blog.email;

public class MailBody {
    private String recipient;
    private String subject;
    private String content;

    public MailBody() {
    }

    public MailBody(String recipient, String subject, String content) {
        this.recipient = recipient;
        this.subject = subject;
        this.content = content;
    }

    String getRecipient() {
        return recipient;
    }

    void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    String getSubject() {
        return subject;
    }

    void setTitle(String subject) {
        this.subject = subject;
    }

    String getContent() {
        return content;
    }

    void setContent(String content) {
        this.content = content;
    }
}
