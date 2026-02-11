package com.mateoortiz.repuestosAutomotrices.controller;

import com.mateoortiz.repuestosAutomotrices.entity.Proveedor;
import com.mateoortiz.repuestosAutomotrices.service.ProveedoresService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")

public class ProveedoresController {
    private final ProveedoresService proveedoresService;

    public ProveedoresController(ProveedoresService proveedoresService){ this.proveedoresService=proveedoresService; }

    @GetMapping
    public List<Proveedor> getAllProveedores(){ return proveedoresService.getAllProveedores(); }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProveedorById (@PathVariable Integer id) {
        Proveedor proveedor = proveedoresService.getProveedorById(id);
        return ResponseEntity.ok(proveedor);
    }

    @PostMapping
    public ResponseEntity<Object> createProveedor(@Valid @RequestBody Proveedor proveedor){
        try{
            Proveedor createdProveedor = proveedoresService.saveProveedor(proveedor);
            return new ResponseEntity<>(createdProveedor, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> updateProveedor(@PathVariable Integer id, @RequestBody Proveedor proveedor) {
        Proveedor updatedProveedor = proveedoresService.updateProveedor(id, proveedor);
        return ResponseEntity.ok(updatedProveedor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProveedor(@PathVariable Integer id){
        proveedoresService.deleteProveedor(id);
        return ResponseEntity.ok().build();
    }
}
