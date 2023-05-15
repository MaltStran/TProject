package ru.tinkoff.edu.java.scrapper.Model;


import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tinkoff.edu.java.scrapper.Model.Jpa.LinkJpa;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class Link {
    private long id;
    private String url;
    private Timestamp update;

    public Link(long id, String url, Timestamp update) {
        this.id = id;
        this.url = url;
        this.update = update;
    }

    public Link(String url, Timestamp update) {
        this.url = url;
        this.update = update;
    }

    public Link(LinkJpa linkJpa){
        this.id = linkJpa.getId();
        this.url = linkJpa.getUrl();
        this.update = linkJpa.getUpdate();
    }
}
