package ru.tinkoff.edu.java.scrapper.repository.Jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.Model.Jpa.ChatJpa;


@Repository
public interface ChatJpaRepository extends JpaRepository<ChatJpa, Long> {


}
