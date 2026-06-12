package cl.cordillera.msgestionkpi.model;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
@Document(collection = "kpis")
public class Kpi {
    @Id private String id;
    @NotBlank(message = "El nombre del KPI es obligatorio") private String nombre;
    private String descripcion;
    @NotBlank(message = "El área es obligatoria") private String area;
    @NotNull(message = "La meta no puede ser nula") private Double meta;
    @NotNull(message = "El valor actual no puede ser nulo") private Double valorActual;
    private String unidad;
    @Builder.Default private EstadoKpi estado = EstadoKpi.EN_PROGRESO;
    private String periodicidad;
    private String sucursal;
    @Builder.Default private LocalDateTime fechaCreacion = LocalDateTime.now();
    private LocalDateTime fechaActualizacion;
    private LocalDateTime fechaVencimiento;
}
