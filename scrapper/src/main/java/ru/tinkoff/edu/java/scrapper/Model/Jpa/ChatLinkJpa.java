package ru.tinkoff.edu.java.scrapper.Model.Jpa;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tinkoff.edu.java.scrapper.Model.Jpa.Ids.ChatLinkId;

@Entity
@Data
@Table(name = "chat_link")
@NoArgsConstructor
public class ChatLinkJpa {

    @EmbeddedId
    private ChatLinkId id;

}
