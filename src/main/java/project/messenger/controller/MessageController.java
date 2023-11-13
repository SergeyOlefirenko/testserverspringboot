package project.messenger.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import project.messenger.model.Message;
import project.messenger.model.User;
import project.messenger.repository.MessageRepository;

@Controller
public class MessageController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private MessageRepository messageRepository;
    @MessageMapping("/message")
//    @SendTo("/chatroom/public")
    @SendTo("/chatroom/public")
    public Message receiveMessage(@Payload Message message) {
        // Сохранение сообщения в базе данных
        Message savedMessage = messageRepository.save(message);
        return savedMessage;
    }
    @MessageMapping("/private-message")
    public Message recMessage(@Payload Message message) {
        // Сохранение приватного сообщения в базе данных
        Message savedMessage = messageRepository.save(message);
        simpMessagingTemplate.convertAndSendToUser(savedMessage.getReceiverName(), "/private", savedMessage);
        return savedMessage;
    }

    @CrossOrigin
    @GetMapping("/message/{userName}")
    public String getUserName(@PathVariable String userName) {
        String message = messageRepository.findBySenderName(userName).getUsername();
        return message.intern();
    }
    // Добавить методы для редактирования, удаления и получения сообщений
}

