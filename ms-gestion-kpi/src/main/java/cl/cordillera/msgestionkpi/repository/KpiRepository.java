package cl.cordillera.msgestionkpi.repository;
import cl.cordillera.msgestionkpi.model.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/** PATRÓN: Repository Pattern */
@Repository
public interface KpiRepository extends MongoRepository<Kpi, String> {
    List<Kpi> findByArea(String area);
    List<Kpi> findByEstado(EstadoKpi estado);
    List<Kpi> findBySucursal(String sucursal);
    List<Kpi> findByAreaAndEstado(String area, EstadoKpi estado);
}
