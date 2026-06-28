package cl.innovatech.msproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "proyectos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del proyecto es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    @NotBlank(message = "El estado es obligatorio")
    @Column(nullable = false)
    private String estado;

    @Positive(message = "El presupuesto debe ser mayor que cero")
    private Double presupuesto;

}