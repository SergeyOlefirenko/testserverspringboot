package project.messenger.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Data
public class Message {
    public static final Object CHAT_STRING = "Chat";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String senderName;
    private String receiverName;
    private String message;

    @Enumerated(EnumType.STRING)
    private Status status;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id") // Имя поля, связанное с User
    private User user;
    private String receiver;

    public void setSender(User sender) {
    }

    public void setReceiver(User receiver) {
    }

    public String getReceiver() {
        return this.receiver;
    }

    public String getUsername() {
        return this.senderName;
    }
//    public String getReceiver() {
//        return this.receiver;
//    }


    // Геттеры и сеттеры
}




//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@ToString
//public class Message {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String senderName;
//    private String receiverName;
//    private String message;
//
//    @Enumerated(EnumType.STRING)
//    private Status status;
//
//    @CreationTimestamp
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "created", updatable = false)
//    private Date createdAt;
//
//    @UpdateTimestamp
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "updated")
//    private Date updatedAt;
//
//    public void setSender(User sender) {
//    }
//
//    public void setReceiver(User receiver) {
//    }
//
//    public RowSet getReceiver() {
//        return getReceiver();
//    }
//}


//import lombok.*;
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@ToString
//public class Message {
//    private String senderName;
//    private String receiverName;
//    private String message;
//    private String date;
//    private Status status;
//}
