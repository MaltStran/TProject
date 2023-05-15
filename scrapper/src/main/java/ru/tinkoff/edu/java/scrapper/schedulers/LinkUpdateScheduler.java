package ru.tinkoff.edu.java.scrapper.schedulers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.services.LinkUpdater;

import java.net.URISyntaxException;

@Slf4j
@Component
public class LinkUpdateScheduler {
    private final LinkUpdater linkUpdater;

    public LinkUpdateScheduler(LinkUpdater linkUpdater) {
        this.linkUpdater = linkUpdater;
    }

    @Scheduled(fixedDelayString = "#{@schedulerTimer}")
    public void update(){
        log.info("work");
        linkUpdater.update();
    }
}
