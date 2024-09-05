package com.example.server;

import com.example.server.entity.Chat;
import com.example.server.entity.User;
import com.example.server.entity.Conversation;

interface IMessageService {
    List<Conversation> getConversation();
    List<User> getUsers();
    List<Chat> getChat(int conversationId);

    Chat getChatById(long chatId);
    User getUserById(int userId);
    User fetchCurrentUser();

    void switchUser(int userId);
    void sendMessage(in Chat message);

    void removeConversationById(in int conversationId);
    void removeUserById(in int userId);

    void updateConversation(in int conversationId, in String lastMessage, in long lastMessageId, in String timestamp);
    void updateConversationV2(in int conversationId, in long timeDeleteSender, in long timeDeleteReceiver);

    void hideChat(long chatId);
    void hideConversation(in Conversation conversation);

    List<Conversation> getConversationsForUser(in int userId);
}