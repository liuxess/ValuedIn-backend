package com.ValuedIn.events;

import com.ValuedIn.models.dto.requests.NewUser;
import com.ValuedIn.services.MailService;
import java.io.IOException;

public class NewUserEvent {

    private final MailService mailService = new MailService();
    private final static String from = "noreply@ValuedIn.com";

    public NewUserEvent(NewUser newUser){
      String subject = String.format("Account for %s in ValuedIn has been successfully registered", newUser.getLogin());
      String to = newUser.getEmail();
      String content = String.format("<h1>Congratulations, %s!</h1><br><div>New User (%s) has been successfully created for You</div>", newUser.getFirstName(), newUser.getLogin());
      try {
          mailService.sendEmail(from, to, subject, content);
      }
      catch (IOException ex){
          //TODO: add logging to catch the errors
        throw new RuntimeException(ex);
      }
    }
}
