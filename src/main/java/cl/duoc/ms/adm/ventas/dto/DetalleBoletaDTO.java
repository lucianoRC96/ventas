package cl.duoc.ms.adm.ventas.dto;


import lombok.Data;

@Data
public class DetalleBoletaDTO {
    private Long id;
    private Long productoId;
    private Integer cantidad;
    private Double precioUnitario;
}