package cl.duoc.ms.adm.ventas.Controller;

import org.springframework.web.bind.annotation.*;


import cl.duoc.ms.adm.ventas.dto.BoletaDTO;
import cl.duoc.ms.adm.ventas.dto.DetalleBoletaDTO;
import cl.duoc.ms.adm.ventas.model.Boleta;
import cl.duoc.ms.adm.ventas.model.DetalleBoleta;
import cl.duoc.ms.adm.ventas.service.BoletaService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/boletas")
public class BoletaController {
    private final BoletaService boletaService;

    public BoletaController(BoletaService boletaService) {
        this.boletaService = boletaService;
    }

    @GetMapping
    public List<BoletaDTO> listar() {
        return boletaService.listarBoletas().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public BoletaDTO agregar(@RequestBody BoletaDTO boletaDTO) {
        Boleta boleta = toEntity(boletaDTO);
        Boleta guardada = boletaService.guardarBoleta(boleta);
        return toDTO(guardada);
    }

    

    @GetMapping("/{id}")
    public BoletaDTO obtenerPorId(@PathVariable Long id) {
        return boletaService.buscarPorId(id)
                .map(this::toDTO)
                .orElse(null); // Puedes lanzar una excepción personalizada si prefieres
    }

    @PutMapping("/{id}")
    public BoletaDTO actualizar(@PathVariable Long id, @RequestBody BoletaDTO boletaDTO) {
        Boleta boleta = toEntity(boletaDTO);
        boleta.setId(id);
        Boleta actualizada = boletaService.guardarBoleta(boleta);
        return toDTO(actualizada);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        boletaService.eliminarPorId(id);
    }
    // Métodos de conversión
    private BoletaDTO toDTO(Boleta boleta) {
        BoletaDTO dto = new BoletaDTO();
        dto.setId(boleta.getId());
        dto.setFecha(boleta.getFecha() != null ? boleta.getFecha().toString() : null);
        dto.setTotal(boleta.getTotal());
        if (boleta.getDetalles() != null) {
            dto.setDetalles(
                boleta.getDetalles().stream()
                    .map(this::toDetalleDTO)
                    .collect(Collectors.toList())
            );
        }
        return dto;
    }

    private Boleta toEntity(BoletaDTO dto) {
        Boleta boleta = new Boleta();
        boleta.setId(dto.getId());
        boleta.setFecha(dto.getFecha() != null ? java.time.LocalDateTime.parse(dto.getFecha()) : null);
        boleta.setTotal(dto.getTotal());
        if (dto.getDetalles() != null) {
            boleta.setDetalles(
                dto.getDetalles().stream()
                    .map(this::toDetalleEntity)
                    .collect(Collectors.toList())
            );
        }
        return boleta;
    }

    private DetalleBoletaDTO toDetalleDTO(DetalleBoleta detalle) {
        DetalleBoletaDTO dto = new DetalleBoletaDTO();
        dto.setId(detalle.getId());
        dto.setProductoId(detalle.getProductoId());
        dto.setCantidad(detalle.getCantidad());
        dto.setPrecioUnitario(detalle.getPrecioUnitario());
        return dto;
    }

    private DetalleBoleta toDetalleEntity(DetalleBoletaDTO dto) {
        DetalleBoleta detalle = new DetalleBoleta();
        detalle.setId(dto.getId());
        detalle.setProductoId(dto.getProductoId());
        detalle.setCantidad(dto.getCantidad());
        detalle.setPrecioUnitario(dto.getPrecioUnitario());
        return detalle;
    }
}
