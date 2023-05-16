package ru.tinkoff.edu.java.scrapper.Model.Jpa.Ids;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.tinkoff.edu.java.scrapper.Model.Jpa.ChatJpa;
import ru.tinkoff.edu.java.scrapper.Model.Jpa.LinkJpa;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class ChatLinkId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "chat_id")
    private ChatJpa chatId;

    @ManyToOne
    @JoinColumn(name = "Link_id")
    private LinkJpa linkId;

    public ChatLinkId(ChatJpa chatId, LinkJpa linkId) {
        this.chatId = chatId;
        this.linkId = linkId;
    }
}
