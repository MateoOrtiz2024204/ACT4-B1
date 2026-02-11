package com.mateoortiz.repuestosAutomotrices.service;

import com.mateoortiz.repuestosAutomotrices.entity.Repuestos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RepuestosService {

    List<Repuestos> getAllRepuestos();

    Repuestos getRepuestoById(Integer id);

    Repuestos saveRepuesto(Repuestos repuesto) throws RuntimeException;

    Repuestos updateRepuesto(Integer id, Repuestos repuesto);

    void deleteRepuesto(Integer id);
}
