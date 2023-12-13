package com.entity;

import io.getstream.chat.java.models.Message.MessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class message {
    MessageType type;
    String content;
    String sender;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }
}
