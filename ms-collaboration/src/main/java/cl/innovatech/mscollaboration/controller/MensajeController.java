package cl.innovatech.mscollaboration.controller;

import cl.innovatech.mscollaboration.model.Mensaje;
import cl.innovatech.mscollaboration.service.MensajeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mensajes")
public class MensajeController {

    private final MensajeService mensajeService;

    public MensajeController(MensajeService mensajeService) {
        this.mensajeService = mensajeService;
    }

    @GetMapping
    public ResponseEntity<List<Mensaje>> listarTodos() {
        return ResponseEntity.ok(mensajeService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mensaje> buscarPorId(@PathVariable String id) {

        return mensajeService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<Mensaje> guardar(@Valid @RequestBody Mensaje mensaje) {

        Mensaje nuevoMensaje = mensajeService.guardar(mensaje);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoMensaje);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {

        if (mensajeService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        mensajeService.eliminar(id);

        return ResponseEntity.noContent().build();

    }

}