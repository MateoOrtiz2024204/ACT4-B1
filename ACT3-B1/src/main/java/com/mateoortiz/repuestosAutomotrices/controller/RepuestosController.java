package com.mateoortiz.repuestosAutomotrices.controller;

import com.mateoortiz.repuestosAutomotrices.entity.Repuestos;
import com.mateoortiz.repuestosAutomotrices.service.RepuestosService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repuestos")
public class RepuestosController {

    private final RepuestosService repuestosService;

    public RepuestosController(RepuestosService repuestosService) {
        this.repuestosService = repuestosService;
    }

    @GetMapping
    public List<Repuestos> getAllRepuestos() {
        return repuestosService.getAllRepuestos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getRepuestoById(@PathVariable Integer id) {
        Repuestos repuesto = repuestosService.getRepuestoById(id);
        return ResponseEntity.ok(repuesto);
    }

    @PostMapping
    public ResponseEntity<Object> createRepuesto(@Valid @RequestBody Repuestos repuesto) {
        try {
            Repuestos createdRepuesto = repuestosService.saveRepuesto(repuesto);
            return new ResponseEntity<>(createdRepuesto, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Repuestos> updateRepuesto(@PathVariable Integer id, @RequestBody Repuestos repuesto) {
        Repuestos updatedRepuesto = repuestosService.updateRepuesto(id, repuesto);
        return ResponseEntity.ok(updatedRepuesto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRepuesto(@PathVariable Integer id) {
        repuestosService.deleteRepuesto(id);
        return ResponseEntity.ok().build();
    }
}
