package cl.innovatech.msresources.controller;

import cl.innovatech.msresources.model.Recurso;
import cl.innovatech.msresources.service.RecursoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recursos")
public class RecursoController {

    private final RecursoService recursoService;

    public RecursoController(RecursoService recursoService) {
        this.recursoService = recursoService;
    }

    @GetMapping
    public ResponseEntity<List<Recurso>> listarTodos() {
        return ResponseEntity.ok(recursoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recurso> buscarPorId(@PathVariable Long id) {

        return recursoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<Recurso> guardar(@Valid @RequestBody Recurso recurso) {

        Recurso nuevoRecurso = recursoService.guardar(recurso);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRecurso);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Recurso> actualizar(@PathVariable Long id,
                                              @Valid @RequestBody Recurso recurso) {

        Recurso actualizado = recursoService.actualizar(id, recurso);

        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(actualizado);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        if (recursoService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        recursoService.eliminar(id);

        return ResponseEntity.noContent().build();

    }

}