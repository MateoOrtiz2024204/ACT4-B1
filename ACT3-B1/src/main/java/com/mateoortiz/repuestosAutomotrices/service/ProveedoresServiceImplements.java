package com.mateoortiz.repuestosAutomotrices.service;

import com.mateoortiz.repuestosAutomotrices.entity.Proveedor;
import com.mateoortiz.repuestosAutomotrices.repository.ProveedoresRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedoresServiceImplements implements ProveedoresService {
    private final ProveedoresRepository proveedoresRepository;

    public ProveedoresServiceImplements(ProveedoresRepository proveedoresRepository) {
        this.proveedoresRepository = proveedoresRepository;
    }

    @Override
    public List<Proveedor> getAllProveedores() { return proveedoresRepository.findAll(); }

    @Override
    public Proveedor getProveedorById(Integer id) { return proveedoresRepository.findById(id).orElse(null); }

    @Override
    public Proveedor saveProveedor(Proveedor proveedor) throws RuntimeException{
        return  proveedoresRepository.save(proveedor);
    }

    @Override
    public Proveedor updateProveedor(Integer id, Proveedor proveedor) {
        Proveedor proveedorExist = proveedoresRepository.findById(id).orElse(null);
        if (proveedorExist == null) {
            return null;
        }
        proveedorExist.setNombreProveedor(proveedor.getNombreProveedor());
        proveedorExist.setTelefonoProveedor(proveedor.getTelefonoProveedor());
        proveedorExist.setDireccionProveedor(proveedor.getDireccionProveedor());
        proveedorExist.setEmailProveedor(proveedor.getEmailProveedor());
        return proveedoresRepository.save(proveedorExist);
    }

    @Override
    public void deleteProveedor(Integer id) { proveedoresRepository.deleteById(id); }

}