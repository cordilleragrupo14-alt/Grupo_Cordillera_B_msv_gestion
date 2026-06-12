package cl.cordillera.msgestionkpi.factory;
import cl.cordillera.msgestionkpi.dto.KpiDTO;
import cl.cordillera.msgestionkpi.model.*;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

/** PATRÓN: Factory Method */
@Component
public class KpiFactory {
    public Kpi crearDesdeDTO(KpiDTO dto) {
        return Kpi.builder().nombre(dto.getNombre()).descripcion(dto.getDescripcion())
                .area(dto.getArea()).meta(dto.getMeta()).valorActual(dto.getValorActual())
                .unidad(dto.getUnidad()).estado(calcularEstado(dto.getValorActual(), dto.getMeta()))
                .periodicidad(dto.getPeriodicidad()).sucursal(dto.getSucursal())
                .fechaVencimiento(dto.getFechaVencimiento())
                .fechaCreacion(LocalDateTime.now()).fechaActualizacion(LocalDateTime.now()).build();
    }
    public KpiDTO crearDTO(Kpi kpi) {
        return KpiDTO.builder().id(kpi.getId()).nombre(kpi.getNombre()).descripcion(kpi.getDescripcion())
                .area(kpi.getArea()).meta(kpi.getMeta()).valorActual(kpi.getValorActual())
                .unidad(kpi.getUnidad()).estado(kpi.getEstado())
                .porcentajeCumplimiento(calcularPorcentaje(kpi.getValorActual(), kpi.getMeta()))
                .periodicidad(kpi.getPeriodicidad()).sucursal(kpi.getSucursal())
                .fechaVencimiento(kpi.getFechaVencimiento()).fechaActualizacion(kpi.getFechaActualizacion()).build();
    }
    public EstadoKpi calcularEstado(Double valorActual, Double meta) {
        if (meta == null || meta == 0) return EstadoKpi.EN_PROGRESO;
        double p = (valorActual / meta) * 100;
        if (p >= 100) return EstadoKpi.EN_META;
        if (p >= 75) return EstadoKpi.EN_PROGRESO;
        if (p >= 50) return EstadoKpi.EN_RIESGO;
        return EstadoKpi.CRITICO;
    }
    public double calcularPorcentaje(Double valorActual, Double meta) {
        if (meta == null || meta == 0) return 0.0;
        return Math.min(Math.round((valorActual / meta) * 10000.0) / 100.0, 100.0);
    }
}
