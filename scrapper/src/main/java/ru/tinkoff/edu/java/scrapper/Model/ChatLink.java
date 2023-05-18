package ru.tinkoff.edu.java.scrapper.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatLink {
    private Long chat_id;
    private Long link_id;

    public ChatLink(Long chat_id, Long link_id) {
        this.chat_id = chat_id;
        this.link_id = link_id;
    }
}
