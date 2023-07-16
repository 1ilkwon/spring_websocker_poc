package com.simsim.chat.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    public enum Messagetype{
        ENTER, TALK
    }
    private Messagetype type;
    private String roomId;
    private String sender;
    private String message;
}
