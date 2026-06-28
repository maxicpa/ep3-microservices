package cl.innovatech.msanalytics.controller;

import cl.innovatech.msanalytics.model.Indicador;
import cl.innovatech.msanalytics.service.IndicadorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/indicadores")
public class IndicadorController {

    private final IndicadorService indicadorService;

    public IndicadorController(IndicadorService indicadorService) {
        this.indicadorService = indicadorService;
    }

    @GetMapping
    public ResponseEntity<List<Indicador>> listarTodos() {
        return ResponseEntity.ok(indicadorService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Indicador> buscarPorId(@PathVariable Long id) {

        return indicadorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<Indicador> guardar(@Valid @RequestBody Indicador indicador) {

        Indicador nuevo = indicadorService.guardar(indicador);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Indicador> actualizar(@PathVariable Long id,
                                                @Valid @RequestBody Indicador indicador) {

        Indicador actualizado = indicadorService.actualizar(id, indicador);

        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(actualizado);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        if (indicadorService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        indicadorService.eliminar(id);

        return ResponseEntity.noContent().build();

    }

}