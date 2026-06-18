package cl.cordillera.msgestionkpi.controller; // <-- ¡ESTA LÍNEA ES OBLIGATORIA!

import cl.cordillera.msgestionkpi.dto.KpiDTO;
import cl.cordillera.msgestionkpi.model.EstadoKpi;
import cl.cordillera.msgestionkpi.service.KpiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(KpiController.class)
class KpiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KpiService service;

    @Test
    void obtenerTodos_debeRetornarListaYStatusOk() throws Exception {
        KpiDTO mockKpi = KpiDTO.builder().id("1").nombre("Ventas Mensuales").estado(EstadoKpi.EN_META).build();
        when(service.obtenerTodos()).thenReturn(List.of(mockKpi));

        mockMvc.perform(get("/api/kpis").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Ventas Mensuales"))
                .andExpect(jsonPath("$[0].estado").value("EN_META"));
    }
}