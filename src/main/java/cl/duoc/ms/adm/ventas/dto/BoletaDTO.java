package cl.duoc.ms.adm.ventas.dto;

import lombok.Data;
import java.util.List;

@Data
public class BoletaDTO {
    private Long id;
    private String fecha;
    private Double total;
    private List<DetalleBoletaDTO> detalles;
}