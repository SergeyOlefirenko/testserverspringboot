package project.messenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import project.messenger.model.Message;
import project.messenger.model.User;
import project.messenger.repository.MessageRepository;
import project.messenger.repository.UserRepository;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;


//    @CrossOrigin
//    @GetMapping(value = "user/userName")
//    public String getUser (@PathVariable String userName){
////        User user = userRepository.findByUsername(userName).orElse(new User());
//        User user = messageRepository.findBySenderName(userName).orElse(new User());
//        return user.getUsername();
//    }
//    @CrossOrigin
//    @GetMapping("/user/{userName}")
//    public String getUserName(@PathVariable String userName) {
//        User user = messageRepository.findBySenderName(userName).orElse(new User());
//        return user.getUsername();
//    }


//@CrossOrigin
//@GetMapping("/users")
//public List<User> getAllUsers() {
//    return userRepository.findAll();
//}

    @MessageMapping("/public/message")
    @SendTo("/chatroom/public")
    public Message receivePublicMessage(@Payload Message message) {
        // Обработка общего сообщения
        // Сохранение сообщения в базе данных
        // Определяем отправителя
        User sender = userRepository.findByUsername(message.getSenderName());

        if (sender == null) {
            // Обработка ошибки - отправитель не найден
            return null;
        }

        // Определяем получателя
        User receiver = userRepository.findByUsername(message.getReceiverName());

        if (receiver == null) {
            // Обработка ошибки - получатель не найден
//            return null;
            Object chatString = Message.CHAT_STRING;
            return (Message) chatString;

        }

        // Сохраняем сообщение с указанием отправителя и получателя
        message.setSender(sender);
        message.setReceiver(receiver);
        Message savedMessage = messageRepository.save(message);

        return savedMessage;
    }

    @MessageMapping("/private/message")
    @SendToUser("/private")
    public Message receivePrivateMessage(@Payload Message message, Principal principal) {
        // Обработка приватного сообщения
        // Сохранение приватного сообщения в базе данных
        // Определяем отправителя (получаем из авторизованного пользователя)
        User sender = userRepository.findByUsername(principal.getName());

        if (sender == null) {
            // Обработка ошибки - отправитель не найден
            return null;
        }
        // Определяем получателя
        User receiver = userRepository.findByUsername(message.getReceiverName());
        if (receiver == null) {
            // Обработка ошибки - получатель не найден
            Object chatString = Message.CHAT_STRING;
            return (Message) chatString;
        }


        // Сохраняем сообщение с указанием отправителя и получателя
        message.setSender(sender);
        message.setReceiver(receiver);
        Message savedMessage = messageRepository.save(message);

        simpMessagingTemplate.convertAndSendToUser(savedMessage.getReceiver().intern(), "/private", savedMessage);

        return savedMessage;
    }
    // Добавить методы для редактирования, удаления и получения сообщений
}


//    @MessageMapping("/message")
//    @SendTo("/chatroom/public")
//    public Message commonMessages(@Payload Message message) {
//        // Сохранение сообщения в базе данных
//        // Определяем отправителя
//        User sender = userRepository.findByUsername(message.getSenderName());
//
//        if (sender == null) {
//            // Обработка ошибки - отправитель не найден
//            return null;
//        }
//
//        // Определяем получателя
//        User receiver = userRepository.findByUsername(message.getReceiverName());
//
//        if (receiver == null) {
//            // Обработка ошибки - получатель не найден
//            return null;
//        }
//
//        // Сохраняем сообщение с указанием отправителя и получателя
//        message.setSender(sender);
//        message.setReceiver(receiver);
//        Message savedMessage = messageRepository.save(message);
//
//        return savedMessage;
//    }

//    @MessageMapping("/private-message")
//    @SendToUser("/private")
//    public Message privateMessages(@Payload Message message, Principal principal) {
//        // Сохранение приватного сообщения в базе данных
//        // Определяем отправителя (получаем из авторизованного пользователя)
//        User sender = userRepository.findByUsername(principal.getName());
//
//        if (sender == null) {
//            // Обработка ошибки - отправитель не найден
//            return null;
//        }
//
//        // Определяем получателя
//        User receiver = userRepository.findByUsername(message.getReceiverName());
//
//        if (receiver == null) {
//            // Обработка ошибки - получатель не найден
//            return null;
//        }
//
//        // Сохраняем сообщение с указанием отправителя и получателя
//        message.setSender(sender);
//        message.setReceiver(receiver);
//        Message savedMessage = messageRepository.save(message);
//
//        simpMessagingTemplate.convertAndSendToUser(savedMessage.getReceiver().getUsername(), "/private", savedMessage);
//
//        return savedMessage;
//    }
//
//    // Добавить методы для редактирования, удаления и получения сообщений
//}
