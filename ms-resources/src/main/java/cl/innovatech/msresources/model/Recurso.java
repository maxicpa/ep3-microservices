package cl.innovatech.msresources.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "recursos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Recurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del recurso es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "El tipo de recurso es obligatorio")
    @Column(nullable = false)
    private String tipo;

    @NotBlank(message = "La disponibilidad es obligatoria")
    @Column(nullable = false)
    private String disponibilidad;

    @Column(nullable = false)
    private Long proyectoId;
}