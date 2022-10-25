package com.ValuedIn.services;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class MailServiceTest {

  private final MailService mailService = new MailService();

  @Test
  void sendEmail() throws IOException {
    mailService.sendEmail("From Someone", "To someone", "This is Subject", "<h1>This is content</h1>");
  }
}