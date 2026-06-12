package cl.cordillera.msgestionkpi.dto;
import cl.cordillera.msgestionkpi.model.EstadoKpi;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class KpiDTO {
    private String id;
    @NotBlank(message = "El nombre del KPI es obligatorio") private String nombre;
    private String descripcion;
    @NotBlank(message = "El área es obligatoria") private String area;
    @NotNull(message = "La meta no puede ser nula") private Double meta;
    @NotNull(message = "El valor actual no puede ser nulo") private Double valorActual;
    private String unidad;
    private EstadoKpi estado;
    private Double porcentajeCumplimiento;
    private String periodicidad;
    private String sucursal;
    private LocalDateTime fechaVencimiento;
    private LocalDateTime fechaActualizacion;
}
