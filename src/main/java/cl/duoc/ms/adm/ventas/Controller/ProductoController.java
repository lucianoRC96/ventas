package cl.duoc.ms.adm.ventas.controller;

import org.springframework.web.bind.annotation.*;

import cl.duoc.ms.adm.ventas.dto.ProductoDTO;
import cl.duoc.ms.adm.ventas.model.Producto;
import cl.duoc.ms.adm.ventas.service.ProductoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<ProductoDTO> listar() {
        return productoService.listarProductos().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ProductoDTO agregar(@RequestBody ProductoDTO productoDTO) {
        Producto producto = toEntity(productoDTO);
        Producto guardado = productoService.guardarProducto(producto);
        return toDTO(guardado);
    }

    private ProductoDTO toDTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setPrecio(producto.getPrecio());
        return dto;
    }

    private Producto toEntity(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setId(dto.getId());
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        return producto;
    }
}