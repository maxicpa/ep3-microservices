package cl.innovatech.msproject.controller;

import cl.innovatech.msproject.model.Proyecto;
import cl.innovatech.msproject.service.ProyectoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proyectos")
public class ProyectoController {

    private final ProyectoService proyectoService;

    public ProyectoController(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }

    @GetMapping
    public ResponseEntity<List<Proyecto>> listarTodos() {
        return ResponseEntity.ok(proyectoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proyecto> buscarPorId(@PathVariable Long id) {

        return proyectoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<Proyecto> guardar(@Valid @RequestBody Proyecto proyecto) {

        Proyecto nuevoProyecto = proyectoService.guardar(proyecto);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProyecto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Proyecto> actualizar(@PathVariable Long id,
                                               @Valid @RequestBody Proyecto proyecto) {

        Proyecto actualizado = proyectoService.actualizar(id, proyecto);

        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(actualizado);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        if (proyectoService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        proyectoService.eliminar(id);

        return ResponseEntity.noContent().build();

    }

}