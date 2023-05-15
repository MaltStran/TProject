package ru.tinkoff.edu.java.scrapper.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Chat {
    private Long id;
    private Long chat_id;
    private String name;

    public Chat(Long id, Long chat_id, String name) {
        this.id = id;
        this.chat_id = chat_id;
        this.name = name;
    }

    public Chat(Long chat_id, String name) {
        this.chat_id = chat_id;
        this.name = name;
    }

    public Chat(Long chat_id) {
        this.chat_id = chat_id;
    }
}
