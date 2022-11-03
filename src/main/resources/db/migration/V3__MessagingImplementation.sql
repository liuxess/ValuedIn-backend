
CREATE TABLE tbl_Messaging_Chats (
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(30) NULL,
    chatID BIGINT PRIMARY KEY AUTO_INCREMENT
);

CREATE TABLE tbl_Messaging_ChatParticipation(
  created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  created_by VARCHAR(30) NULL,
  id BIGINT PRIMARY KEY,
  login VARCHAR(30),
  chatID BIGINT,
  FOREIGN KEY (login) REFERENCES tbl_user_credentials(login),
  FOREIGN KEY (chatID) REFERENCES tbl_Messaging_Chats(chatID)
);

CREATE TABLE tbl_Messaging_Messages(
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(30) NULL,
    chatID BIGINT,
    messageID BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(30),
    message TEXT,
    FOREIGN KEY (chatID) REFERENCES tbl_Messaging_Chats(chatID)
)