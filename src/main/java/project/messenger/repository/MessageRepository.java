package project.messenger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.messenger.model.Message;
import project.messenger.model.User;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
//    User findByUsername(String userName);


    Message findBySenderName(String senderName);
    // Добавить необходимые методы для работы с сообщениями
}
