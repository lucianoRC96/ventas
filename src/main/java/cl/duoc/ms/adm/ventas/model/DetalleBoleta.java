package cl.duoc.ms.adm.ventas.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class DetalleBoleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productoId;
    private Double precioUnitario;
    private Integer cantidad;
}
