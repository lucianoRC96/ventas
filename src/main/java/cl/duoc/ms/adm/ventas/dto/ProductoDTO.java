package cl.duoc.ms.adm.ventas.dto;


import lombok.Data;

@Data
public class ProductoDTO {
    private Long id;
    private String nombre;
    private Double precio;
}