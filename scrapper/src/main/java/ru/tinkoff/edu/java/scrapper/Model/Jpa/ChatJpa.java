package ru.tinkoff.edu.java.scrapper.Model.Jpa;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "chat")
@NoArgsConstructor
public class ChatJpa {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "chat_id")
    private Long chat_id;
    @Column(name = "name")
    private String name;

    public ChatJpa(Long id, String name) {
        this.chat_id = id;
        this.name = name;
    }

    public ChatJpa(Long tgChatId) {
        this.chat_id = id;
    }
}
