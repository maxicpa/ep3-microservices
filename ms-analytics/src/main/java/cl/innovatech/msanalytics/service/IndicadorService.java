package cl.innovatech.msanalytics.service;

import cl.innovatech.msanalytics.model.Indicador;
import cl.innovatech.msanalytics.repository.IndicadorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class IndicadorService {

    private final IndicadorRepository indicadorRepository;

    public IndicadorService(IndicadorRepository indicadorRepository) {
        this.indicadorRepository = indicadorRepository;
    }

    public List<Indicador> listarTodos() {
        return indicadorRepository.findAll();
    }

    public Optional<Indicador> buscarPorId(Long id) {
        return indicadorRepository.findById(id);
    }

    public Indicador guardar(Indicador indicador) {
        indicador.setFechaGeneracion(LocalDate.now());
        return indicadorRepository.save(indicador);
    }

    public Indicador actualizar(Long id, Indicador indicadorActualizado) {

        return indicadorRepository.findById(id)
                .map(indicador -> {

                    indicador.setNombre(indicadorActualizado.getNombre());
                    indicador.setValor(indicadorActualizado.getValor());
                    indicador.setDescripcion(indicadorActualizado.getDescripcion());

                    return indicadorRepository.save(indicador);

                }).orElse(null);

    }

    public void eliminar(Long id) {
        indicadorRepository.deleteById(id);
    }
}