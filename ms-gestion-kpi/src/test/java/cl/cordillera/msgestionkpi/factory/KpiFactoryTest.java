package cl.cordillera.msgestionkpi.factory;
import cl.cordillera.msgestionkpi.model.EstadoKpi;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class KpiFactoryTest {
    private KpiFactory factory;
    @BeforeEach void setUp() { factory = new KpiFactory(); }

    @Test void calcularEstado_cuandoAlcanzaMeta_debeRetornarEnMeta() { assertEquals(EstadoKpi.EN_META, factory.calcularEstado(10000.0, 10000.0)); }
    @Test void calcularEstado_cuandoSuperaMeta_debeRetornarEnMeta() { assertEquals(EstadoKpi.EN_META, factory.calcularEstado(12000.0, 10000.0)); }
    @Test void calcularEstado_cuandoAlcanza80Porciento_debeRetornarEnProgreso() { assertEquals(EstadoKpi.EN_PROGRESO, factory.calcularEstado(8000.0, 10000.0)); }
    @Test void calcularEstado_cuandoAlcanza60Porciento_debeRetornarEnRiesgo() { assertEquals(EstadoKpi.EN_RIESGO, factory.calcularEstado(6000.0, 10000.0)); }
    @Test void calcularEstado_cuandoMenorAl50_debeRetornarCritico() { assertEquals(EstadoKpi.CRITICO, factory.calcularEstado(4000.0, 10000.0)); }
    @Test void calcularPorcentaje_debeCalcularCorrectamente() { assertEquals(75.0, factory.calcularPorcentaje(7500.0, 10000.0)); }
    @Test void calcularPorcentaje_cuandoSuperaMeta_debeRetornar100() { assertEquals(100.0, factory.calcularPorcentaje(15000.0, 10000.0)); }
    @Test void calcularPorcentaje_cuandoMetaEsCero_debeRetornarCero() { assertEquals(0.0, factory.calcularPorcentaje(5000.0, 0.0)); }
}
