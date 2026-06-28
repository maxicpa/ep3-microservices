package cl.innovatech.mscollaboration.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "mensajes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Mensaje {

    @Id
    private String id;

    private Long proyectoId;

    @NotBlank(message = "El usuario es obligatorio")
    private String usuario;

    @NotBlank(message = "El mensaje es obligatorio")
    private String mensaje;

    private LocalDateTime fechaHora;
}