package servingwebcontent.repos;

import org.springframework.data.repository.CrudRepository;
import servingwebcontent.domain.Message;

import java.util.List;

public interface MessageRep extends CrudRepository<Message, Integer> {
    List<Message> findByTag(String tag);
}
