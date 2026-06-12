package cl.cordillera.msgestionkpi.service;
import cl.cordillera.msgestionkpi.dto.KpiDTO;
import cl.cordillera.msgestionkpi.factory.KpiFactory;
import cl.cordillera.msgestionkpi.model.*;
import cl.cordillera.msgestionkpi.repository.KpiRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KpiServiceTest {
    @Mock KpiRepository repository;
    @Mock KpiFactory factory;
    @InjectMocks KpiService service;

    private Kpi kpi;
    private KpiDTO dto;

    @BeforeEach void setUp() {
        kpi = Kpi.builder().id("1").nombre("Ventas").area("ventas").meta(10000000.0).valorActual(8500000.0).estado(EstadoKpi.EN_PROGRESO).build();
        dto = KpiDTO.builder().id("1").nombre("Ventas").area("ventas").meta(10000000.0).valorActual(8500000.0).estado(EstadoKpi.EN_PROGRESO).porcentajeCumplimiento(85.0).build();
    }

    @Test void obtenerTodos_debeRetornarLista() {
        when(repository.findAll()).thenReturn(List.of(kpi));
        when(factory.crearDTO(kpi)).thenReturn(dto);
        assertEquals(1, service.obtenerTodos().size());
    }
    @Test void obtenerPorId_cuandoExiste_debeRetornarDTO() {
        when(repository.findById("1")).thenReturn(Optional.of(kpi));
        when(factory.crearDTO(kpi)).thenReturn(dto);
        assertEquals("1", service.obtenerPorId("1").getId());
    }
    @Test void obtenerPorId_cuandoNoExiste_debeLanzarExcepcion() {
        when(repository.findById("X")).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> service.obtenerPorId("X"));
    }
    @Test void crear_debeGuardarYRetornarDTO() {
        when(factory.crearDesdeDTO(dto)).thenReturn(kpi);
        when(repository.save(kpi)).thenReturn(kpi);
        when(factory.crearDTO(kpi)).thenReturn(dto);
        assertNotNull(service.crear(dto));
    }
    @Test void actualizarValor_debeRecalcularEstado() {
        when(repository.findById("1")).thenReturn(Optional.of(kpi));
        when(factory.calcularEstado(10000000.0, 10000000.0)).thenReturn(EstadoKpi.EN_META);
        when(repository.save(any())).thenReturn(kpi);
        when(factory.crearDTO(any())).thenReturn(dto);
        service.actualizarValor("1", 10000000.0);
        verify(factory).calcularEstado(10000000.0, 10000000.0);
    }
    @Test void eliminar_cuandoNoExiste_debeLanzarExcepcion() {
        when(repository.existsById("X")).thenReturn(false);
        assertThrows(RuntimeException.class, () -> service.eliminar("X"));
    }
}
