package com.mateoortiz.repuestosAutomotrices.repository;

import com.mateoortiz.repuestosAutomotrices.entity.Repuestos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepuestosRepository extends JpaRepository<Repuestos, Integer> {
}