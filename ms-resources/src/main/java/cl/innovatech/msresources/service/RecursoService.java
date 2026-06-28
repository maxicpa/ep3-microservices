package cl.innovatech.msresources.service;

import cl.innovatech.msresources.model.Recurso;
import cl.innovatech.msresources.repository.RecursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecursoService {

    private final RecursoRepository recursoRepository;
    private final SqsService sqsService;

    public RecursoService(RecursoRepository recursoRepository,
                          SqsService sqsService) {
        this.recursoRepository = recursoRepository;
        this.sqsService = sqsService;
    }

    public List<Recurso> listarTodos() {
        return recursoRepository.findAll();
    }

    public Optional<Recurso> buscarPorId(Long id) {
        return recursoRepository.findById(id);
    }

    public Recurso guardar(Recurso recurso) {

        Recurso recursoGuardado = recursoRepository.save(recurso);

        String mensaje =
                "Nuevo recurso registrado: " +
                recursoGuardado.getNombre() +
                " | Tipo: " +
                recursoGuardado.getTipo();

        sqsService.enviarMensaje(mensaje);

        return recursoGuardado;
    }

    public Recurso actualizar(Long id, Recurso recursoActualizado) {

        return recursoRepository.findById(id)
                .map(recurso -> {

                    recurso.setNombre(recursoActualizado.getNombre());
                    recurso.setTipo(recursoActualizado.getTipo());
                    recurso.setDisponibilidad(recursoActualizado.getDisponibilidad());
                    recurso.setProyectoId(recursoActualizado.getProyectoId());

                    return recursoRepository.save(recurso);

                }).orElse(null);

    }

    public void eliminar(Long id) {
        recursoRepository.deleteById(id);
    }

}