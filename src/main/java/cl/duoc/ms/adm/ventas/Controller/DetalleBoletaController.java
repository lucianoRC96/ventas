package cl.duoc.ms.adm.ventas.Controller;

import org.springframework.web.bind.annotation.*;

import cl.duoc.ms.adm.ventas.model.DetalleBoleta;
import cl.duoc.ms.adm.ventas.service.DetalleBoletaService;
import cl.duoc.ms.adm.ventas.dto.DetalleBoletaDTO;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/detalles-boleta")
public class DetalleBoletaController {
    private final DetalleBoletaService detalleBoletaService;

    public DetalleBoletaController(DetalleBoletaService detalleBoletaService) {
        this.detalleBoletaService = detalleBoletaService;
    }

    @GetMapping
    public List<DetalleBoletaDTO> listar() {
        return detalleBoletaService.listarDetalles().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public DetalleBoletaDTO agregar(@RequestBody DetalleBoletaDTO detalleDTO) {
        DetalleBoleta detalle = toEntity(detalleDTO);
        DetalleBoleta guardado = detalleBoletaService.guardarDetalle(detalle);
        return toDTO(guardado);
    }

    // Métodos de conversión
    private DetalleBoletaDTO toDTO(DetalleBoleta detalle) {
        DetalleBoletaDTO dto = new DetalleBoletaDTO();
        dto.setId(detalle.getId());
        dto.setProductoId(detalle.getProductoId());
        dto.setCantidad(detalle.getCantidad());
        dto.setPrecioUnitario(detalle.getPrecioUnitario());
        return dto;
    }

    private DetalleBoleta toEntity(DetalleBoletaDTO dto) {
        DetalleBoleta detalle = new DetalleBoleta();
        detalle.setId(dto.getId());
        detalle.setProductoId(dto.getProductoId());
        detalle.setCantidad(dto.getCantidad());
        detalle.setPrecioUnitario(dto.getPrecioUnitario());
        return detalle;
    }
}

