package com.ValuedIn.services.data;

import com.ValuedIn.models.dto.requests.messaging.NewChat;
import com.ValuedIn.models.dto.requests.messaging.NewMessage;
import com.ValuedIn.models.dto.responses.UserParticipatedChat;
import com.ValuedIn.models.dto.responses.ChattingMessage;
import com.ValuedIn.models.entities.messaging.Chat;
import com.ValuedIn.models.entities.messaging.ChatMessage;
import com.ValuedIn.models.entities.messaging.ChatParticipant;
import com.ValuedIn.models.entities.messaging.MessagingUser;
import com.ValuedIn.repositories.ChatMessageRepository;
import com.ValuedIn.repositories.ChatParticipantRepository;
import com.ValuedIn.repositories.ChatRepository;
import com.ValuedIn.repositories.MessagingUserRepository;
import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
  private final ChatRepository chatRepository;
  private final ChatParticipantRepository chatParticipantRepository;
  private final ChatMessageRepository chatMessageRepository;
  private final MessagingUserRepository messagingUserRepository;
  private final String USER_LOGIN = "SetupUser"; //TODO: once we have auth, this should be the actual login

  public List<UserParticipatedChat> retrieveAllAvailableChats(){
    MessagingUser currentUser = messagingUserRepository.getMessagingUserByLogin(USER_LOGIN);

    return
        currentUser.getParticipatedChats().stream().map(ChatParticipant::getChat).map(
        chat ->
            new UserParticipatedChat(
                chat.getChatId(),
                chat.getChatParticipants().stream().map(participant->participant.getMessagingUser().getFullName()).toList()
            )
        ).toList();
  }

  public List<ChattingMessage> retrieveChatMessages(long chatId){
    Chat chat = chatRepository.findById(chatId).orElseThrow();
    Collection<MessagingUser> messagingUsers = chat.getChatParticipants().stream().map(ChatParticipant::getMessagingUser).toList();
    Collection<ChatMessage> chatMessages = chat.getChatMessages();

    return
        chatMessages.parallelStream().map(
            message -> new ChattingMessage(
                messagingUsers.stream().filter(user-> user.getLogin().equals(message.getLogin())).findFirst().orElseThrow().getFullName(),
                message.getMessage(),
                message.getCreated()
            )
        ).toList();

  }

  public long createNewChat(NewChat newChat){
    Collection<MessagingUser> participatingUsers = messagingUserRepository.getMessagingUsersByLoginIn(newChat.getParticipatingUsers());
    Chat chat = new Chat();

    List<ChatParticipant> chatParticipants =
        participatingUsers.stream().map(
            user -> new ChatParticipant(chat, user)
        ).toList();

    chat.setChatParticipants(chatParticipants);

    Chat savedChat = chatRepository.save(chat);
    chatParticipantRepository.saveAll(chatParticipants);

    return savedChat.getChatId();
  }

  public void sendNewMessage(NewMessage newMessage){
    ChatMessage chatMessage = new ChatMessage();
    chatMessage.setChatId(newMessage.getChatId());
    chatMessage.setLogin(USER_LOGIN);
    chatMessage.setMessage(newMessage.getMessage());
    chatMessageRepository.save(chatMessage);
  }


}
