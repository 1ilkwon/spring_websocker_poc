package com.simsim.chat.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simsim.chat.model.ChatMessage;
import com.simsim.chat.model.ChatRoom;
import com.simsim.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebSocketChatHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    protected void handlerTextMessage(WebSocketSession session, TextMessage message)throws  Exception{
        String payload = message.getPayload();
        log.info("payload : " , payload);
        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
        ChatRoom room = chatService.findRoomById(chatMessage.getRoomId());
        room.handlerActions(session, chatMessage, chatService);
        // TextMessage textMessage = new TextMessage("서버에 접속하였습니다.");
        // session.sendMessage(textMessage);

    }

}
