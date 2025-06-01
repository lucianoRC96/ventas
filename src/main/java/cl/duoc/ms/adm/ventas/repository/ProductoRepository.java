package cl.duoc.ms.adm.ventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.duoc.ms.adm.ventas.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {}

