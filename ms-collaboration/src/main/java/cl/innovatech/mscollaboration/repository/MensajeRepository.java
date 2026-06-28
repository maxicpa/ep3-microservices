package cl.innovatech.mscollaboration.repository;

import cl.innovatech.mscollaboration.model.Mensaje;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensajeRepository extends MongoRepository<Mensaje, String> {

}