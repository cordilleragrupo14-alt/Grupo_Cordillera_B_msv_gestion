package cl.cordillera.msgestionkpi.service;
import cl.cordillera.msgestionkpi.dto.KpiDTO;
import cl.cordillera.msgestionkpi.factory.KpiFactory;
import cl.cordillera.msgestionkpi.model.*;
import cl.cordillera.msgestionkpi.repository.KpiRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j @Service @RequiredArgsConstructor
public class KpiService {
    private final KpiRepository repository;
    private final KpiFactory factory;

    public List<KpiDTO> obtenerTodos() { return repository.findAll().stream().map(factory::crearDTO).collect(Collectors.toList()); }
    public KpiDTO obtenerPorId(String id) { return factory.crearDTO(repository.findById(id).orElseThrow(() -> new RuntimeException("KPI no encontrado con id: " + id))); }
    public List<KpiDTO> obtenerPorArea(String area) { return repository.findByArea(area).stream().map(factory::crearDTO).collect(Collectors.toList()); }
    public List<KpiDTO> obtenerPorEstado(EstadoKpi estado) { return repository.findByEstado(estado).stream().map(factory::crearDTO).collect(Collectors.toList()); }
    public List<KpiDTO> obtenerPorAreaYEstado(String area, EstadoKpi estado) { return repository.findByAreaAndEstado(area, estado).stream().map(factory::crearDTO).collect(Collectors.toList()); }
    public KpiDTO crear(KpiDTO dto) { return factory.crearDTO(repository.save(factory.crearDesdeDTO(dto))); }
    public KpiDTO actualizar(String id, KpiDTO dto) {
        Kpi e = repository.findById(id).orElseThrow(() -> new RuntimeException("KPI no encontrado con id: " + id));
        e.setNombre(dto.getNombre()); e.setDescripcion(dto.getDescripcion()); e.setArea(dto.getArea());
        e.setMeta(dto.getMeta()); e.setValorActual(dto.getValorActual()); e.setUnidad(dto.getUnidad());
        e.setPeriodicidad(dto.getPeriodicidad()); e.setSucursal(dto.getSucursal());
        e.setFechaVencimiento(dto.getFechaVencimiento()); e.setFechaActualizacion(LocalDateTime.now());
        e.setEstado(factory.calcularEstado(dto.getValorActual(), dto.getMeta()));
        return factory.crearDTO(repository.save(e));
    }
    public KpiDTO actualizarValor(String id, Double nuevoValor) {
        Kpi e = repository.findById(id).orElseThrow(() -> new RuntimeException("KPI no encontrado con id: " + id));
        e.setValorActual(nuevoValor); e.setEstado(factory.calcularEstado(nuevoValor, e.getMeta()));
        e.setFechaActualizacion(LocalDateTime.now());
        return factory.crearDTO(repository.save(e));
    }
    public void eliminar(String id) {
        if (!repository.existsById(id)) throw new RuntimeException("KPI no encontrado con id: " + id);
        repository.deleteById(id);
    }
}
