package project.messenger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.messenger.model.Message;
import project.messenger.model.User;

@Repository
//public interface UserRepository extends JpaRepository<Message, Long> {
//    User findByUsername(String senderName);
//    // Добавьте необходимые методы для работы с сообщениями
//}
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
