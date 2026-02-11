package com.mateoortiz.repuestosAutomotrices.repository;

import com.mateoortiz.repuestosAutomotrices.entity.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentasRepository extends JpaRepository<Ventas, Integer> {
}
