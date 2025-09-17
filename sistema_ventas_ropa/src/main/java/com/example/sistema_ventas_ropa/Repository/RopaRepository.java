package com.example.sistema_ventas_ropa.Repository;

import com.example.sistema_ventas_ropa.Entity.Ropa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RopaRepository extends JpaRepository<Ropa, Long> {
    boolean existsByNombre(String nombre);
}
