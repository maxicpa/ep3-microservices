package cl.innovatech.msanalytics.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "indicadores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Indicador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del indicador es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @Positive(message = "El valor debe ser mayor que cero")
    @Column(nullable = false)
    private Double valor;

    private String descripcion;

    private LocalDate fechaGeneracion;
}