package cl.innovatech.mscollaboration.service;

import cl.innovatech.mscollaboration.model.Mensaje;
import cl.innovatech.mscollaboration.repository.MensajeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MensajeService {

    private final MensajeRepository mensajeRepository;

    public MensajeService(MensajeRepository mensajeRepository) {
        this.mensajeRepository = mensajeRepository;
    }

    public List<Mensaje> listarTodos() {
        return mensajeRepository.findAll();
    }

    public Optional<Mensaje> buscarPorId(String id) {
        return mensajeRepository.findById(id);
    }

    public Mensaje guardar(Mensaje mensaje) {

        mensaje.setFechaHora(LocalDateTime.now());

        return mensajeRepository.save(mensaje);

    }

    public void eliminar(String id) {
        mensajeRepository.deleteById(id);
    }

}