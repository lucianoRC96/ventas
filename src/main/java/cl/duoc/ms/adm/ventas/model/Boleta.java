package cl.duoc.ms.adm.ventas.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Boleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fecha;
    private Double total;

    @OneToMany(cascade = CascadeType.ALL)
    private List<DetalleBoleta> detalles;
}
