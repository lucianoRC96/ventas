package cl.duoc.ms.adm.ventas.service;

import org.springframework.stereotype.Service;
import cl.duoc.ms.adm.ventas.model.DetalleBoleta;
import cl.duoc.ms.adm.ventas.repository.DetalleBoletaRepository;
import java.util.List;
import java.util.Optional;

@Service
public class DetalleBoletaService {
    private final DetalleBoletaRepository detalleBoletaRepository;

    public DetalleBoletaService(DetalleBoletaRepository detalleBoletaRepository) {
        this.detalleBoletaRepository = detalleBoletaRepository;
    }

    public List<DetalleBoleta> listarDetalles() {
        return detalleBoletaRepository.findAll();
    }

    public DetalleBoleta guardarDetalle(DetalleBoleta detalleBoleta) {
        return detalleBoletaRepository.save(detalleBoleta);
    }

    public Optional<DetalleBoleta> buscarPorId(Long id) {
        return detalleBoletaRepository.findById(id);
    }

    public void eliminarPorId(Long id) {
        detalleBoletaRepository.deleteById(id);
    }
}

