package com.mateoortiz.repuestosAutomotrices.controller;

import com.mateoortiz.repuestosAutomotrices.entity.Proveedor;
import com.mateoortiz.repuestosAutomotrices.service.ProveedoresService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedoresController {

    private final ProveedoresService proveedoresService;

    public ProveedoresController(ProveedoresService proveedoresService){
        this.proveedoresService = proveedoresService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllProveedores(){
        try {
            List<Proveedor> proveedores = proveedoresService.getAllProveedores();
            return ResponseEntity.ok(proveedores);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al obtener proveedores");
            error.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProveedorById(@PathVariable Integer id) {
        try {
            Proveedor proveedor = proveedoresService.getProveedorById(id);
            if (proveedor == null) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Proveedor no encontrado");
                error.put("mensaje", "No existe un proveedor con el ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
            return ResponseEntity.ok(proveedor);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al buscar proveedor");
            error.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PostMapping
    public ResponseEntity<Object> createProveedor(@Valid @RequestBody Proveedor proveedor){
        try {
            Proveedor createdProveedor = proveedoresService.saveProveedor(proveedor);
            return new ResponseEntity<>(createdProveedor, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al crear proveedor");
            error.put("mensaje", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProveedor(@PathVariable Integer id, @Valid @RequestBody Proveedor proveedor) {
        try {
            Proveedor proveedorExistente = proveedoresService.getProveedorById(id);
            if (proveedorExistente == null) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Proveedor no encontrado");
                error.put("mensaje", "No existe un proveedor con el ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
            Proveedor updatedProveedor = proveedoresService.updateProveedor(id, proveedor);
            return ResponseEntity.ok(updatedProveedor);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al actualizar proveedor");
            error.put("mensaje", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProveedor(@PathVariable Integer id){
        try {
            Proveedor proveedorExistente = proveedoresService.getProveedorById(id);
            if (proveedorExistente == null) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Proveedor no encontrado");
                error.put("mensaje", "No existe un proveedor con el ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
            proveedoresService.deleteProveedor(id);
            Map<String, String> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Proveedor eliminado exitosamente");
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al eliminar proveedor");
            error.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String nombreCampo = ((FieldError) error).getField();
            String mensajeError = error.getDefaultMessage();
            errores.put(nombreCampo, mensajeError);
        });
        return ResponseEntity.badRequest().body(errores);
    }
}