package vera.repo;

import org.springframework.data.repository.CrudRepository;
import vera.models.Device;

import java.util.List;

public interface DeviceRepo extends CrudRepository<Device,Long> {
    Device findDevicesByUserIdOrderByIdD(Long id);

    List<Device> findAll();
    Device findBySerialNumber(String num);
}
