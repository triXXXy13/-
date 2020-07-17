package servingwebcontent.repos;

import org.springframework.data.repository.CrudRepository;
import servingwebcontent.domain.Message;

public interface MessageRep extends CrudRepository<Message, Integer> {
}
