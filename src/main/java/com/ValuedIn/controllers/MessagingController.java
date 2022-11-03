package com.ValuedIn.controllers;


import com.ValuedIn.models.dto.requests.messaging.NewChat;
import com.ValuedIn.models.dto.requests.messaging.NewMessage;
import com.ValuedIn.models.dto.responses.ChattingMessage;
import com.ValuedIn.models.dto.responses.UserParticipatedChat;
import com.ValuedIn.services.data.ChatService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/chats")
public class MessagingController {

  private final ChatService chatService;

  @GetMapping("/")
  public List<UserParticipatedChat> getAllChats() {
    return chatService.retrieveAllAvailableChats();
  }

  @GetMapping("/{chatId}")
  public List<ChattingMessage> getAllChatMessages(@PathVariable("chatId") Long chatId) {
    return chatService.retrieveChatMessages(chatId);
  }

  @PostMapping("/new")
  public long createNewChat(@RequestBody NewChat newChat){
    return chatService.createNewChat(newChat);
  }

  @PostMapping("/message")
  public void sendNewMessage(@RequestBody NewMessage newMessage){
    chatService.sendNewMessage(newMessage);
  }

}
