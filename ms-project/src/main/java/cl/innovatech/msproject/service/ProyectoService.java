package cl.innovatech.msproject.service;

import cl.innovatech.msproject.model.Proyecto;
import cl.innovatech.msproject.repository.ProyectoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProyectoService {

    private final ProyectoRepository proyectoRepository;

    public ProyectoService(ProyectoRepository proyectoRepository) {
        this.proyectoRepository = proyectoRepository;
    }

    public List<Proyecto> listarTodos() {
        return proyectoRepository.findAll();
    }

    public Optional<Proyecto> buscarPorId(Long id) {
        return proyectoRepository.findById(id);
    }

    public Proyecto guardar(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    public Proyecto actualizar(Long id, Proyecto proyectoActualizado) {

        return proyectoRepository.findById(id)
                .map(proyecto -> {
                    proyecto.setNombre(proyectoActualizado.getNombre());
                    proyecto.setDescripcion(proyectoActualizado.getDescripcion());
                    proyecto.setFechaInicio(proyectoActualizado.getFechaInicio());
                    proyecto.setFechaFin(proyectoActualizado.getFechaFin());
                    proyecto.setEstado(proyectoActualizado.getEstado());
                    proyecto.setPresupuesto(proyectoActualizado.getPresupuesto());

                    return proyectoRepository.save(proyecto);
                })
                .orElse(null);
    }

    public void eliminar(Long id) {
        proyectoRepository.deleteById(id);
    }

}