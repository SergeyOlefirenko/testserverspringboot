package project.messenger.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    @OneToMany(mappedBy = "user")
    private List<Message> messages;

//    public Message orElse(User user) {
//        return user;
//    }

    // Геттеры и сеттеры
}


//import jakarta.persistence.*;
//import lombok.*;
//
//import java.util.List;
//@Data
////@RequiredArgsConstructor
////@NoArgsConstructor
//@Entity
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String username;
//    @OneToMany(mappedBy = "user")
//    private List<Message> messages;
//
//    // Геттеры и сеттеры
//}

