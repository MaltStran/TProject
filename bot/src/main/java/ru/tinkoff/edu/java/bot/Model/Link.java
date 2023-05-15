package ru.tinkoff.edu.java.bot.Model;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URI;

@Data
@NoArgsConstructor
public class Link {
    private long id;
    private URI url;

    public Link(long id, URI url) {
        this.id = id;
        this.url = url;
    }
}
