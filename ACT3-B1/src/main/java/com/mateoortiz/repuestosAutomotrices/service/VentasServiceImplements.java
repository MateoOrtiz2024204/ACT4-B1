package com.mateoortiz.repuestosAutomotrices.service;

import com.mateoortiz.repuestosAutomotrices.entity.Ventas;
import com.mateoortiz.repuestosAutomotrices.repository.VentasRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentasServiceImplements implements VentasService {
    private final VentasRepository ventasRepository;

    public VentasServiceImplements(VentasRepository ventasRepository) {
        this.ventasRepository = ventasRepository;
    }

    @Override
    public List<Ventas> getAllVentas() {
        return ventasRepository.findAll();
    }

    @Override
    public Ventas getVentasById(Integer id) {
        return ventasRepository.findById(id).orElse(null);
    }

    @Override
    public Ventas saveVentas(Ventas ventas) throws RuntimeException {
        return ventasRepository.save(ventas);
    }

    @Override
    public Ventas updateVentas(Integer id, Ventas venta) {
        Ventas ventasExist = ventasRepository.findById(id).orElse(null);
        if(ventasExist == null){
            return null;
        }
        ventasExist.setFechaVenta(venta.getFechaVenta());
        ventasExist.setCantidadRepuesto(venta.getCantidadRepuesto());
        ventasExist.setTotalRepuesto(venta.getTotalRepuesto());
        ventasExist.setEmpleado(venta.getEmpleado());
        ventasExist.setRepuestos(venta.getRepuestos());
        return ventasRepository.save(ventasExist);
    }

    @Override
    public void deleteVentas(Integer id) {
        ventasRepository.deleteById(id);
    }
}
