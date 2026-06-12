package cl.cordillera.msgestionkpi.controller;

import cl.cordillera.msgestionkpi.dto.KpiDTO;
import cl.cordillera.msgestionkpi.model.EstadoKpi;
import cl.cordillera.msgestionkpi.service.KpiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

// NOTA: @CrossOrigin eliminado — CORS gestionado en CorsConfig global
@RestController
@RequestMapping("/api/kpis")
@RequiredArgsConstructor
public class KpiController {

    private final KpiService service;

    @GetMapping
    public ResponseEntity<List<KpiDTO>> obtenerTodos() { return ResponseEntity.ok(service.obtenerTodos()); }
    @GetMapping("/{id}")
    public ResponseEntity<KpiDTO> obtenerPorId(@PathVariable String id) { return ResponseEntity.ok(service.obtenerPorId(id)); }
    @GetMapping("/area/{area}")
    public ResponseEntity<List<KpiDTO>> obtenerPorArea(@PathVariable String area) { return ResponseEntity.ok(service.obtenerPorArea(area)); }
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<KpiDTO>> obtenerPorEstado(@PathVariable EstadoKpi estado) { return ResponseEntity.ok(service.obtenerPorEstado(estado)); }
    @GetMapping("/area/{area}/estado/{estado}")
    public ResponseEntity<List<KpiDTO>> obtenerPorAreaYEstado(@PathVariable String area, @PathVariable EstadoKpi estado) {
        return ResponseEntity.ok(service.obtenerPorAreaYEstado(area, estado));
    }
    @PostMapping
    public ResponseEntity<KpiDTO> crear(@Valid @RequestBody KpiDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<KpiDTO> actualizar(@PathVariable String id, @Valid @RequestBody KpiDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }
    @PatchMapping("/{id}/valor")
    public ResponseEntity<KpiDTO> actualizarValor(@PathVariable String id, @RequestBody Map<String, Double> body) {
        Double v = body.get("valor");
        if (v == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(service.actualizarValor(id, v));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
