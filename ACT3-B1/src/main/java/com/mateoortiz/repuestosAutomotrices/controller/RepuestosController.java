package com.mateoortiz.repuestosAutomotrices.controller;

import com.mateoortiz.repuestosAutomotrices.entity.Repuestos;
import com.mateoortiz.repuestosAutomotrices.service.RepuestosService;
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
@RequestMapping("/api/repuestos")
public class RepuestosController {

    private final RepuestosService repuestosService;

    public RepuestosController(RepuestosService repuestosService) {
        this.repuestosService = repuestosService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllRepuestos() {
        try {
            List<Repuestos> repuestos = repuestosService.getAllRepuestos();
            return ResponseEntity.ok(repuestos);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al obtener repuestos");
            error.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getRepuestoById(@PathVariable Integer id) {
        try {
            Repuestos repuesto = repuestosService.getRepuestoById(id);
            if (repuesto == null) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Repuesto no encontrado");
                error.put("mensaje", "No existe un repuesto con el ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
            return ResponseEntity.ok(repuesto);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al buscar repuesto");
            error.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PostMapping
    public ResponseEntity<Object> createRepuesto(@Valid @RequestBody Repuestos repuesto) {
        try {
            if (repuesto.getProveedor() == null || repuesto.getProveedor().getIdProveedor() == null) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Proveedor inválido");
                error.put("mensaje", "Debe proporcionar un ID de proveedor válido");
                return ResponseEntity.badRequest().body(error);
            }

            Repuestos createdRepuesto = repuestosService.saveRepuesto(repuesto);
            return new ResponseEntity<>(createdRepuesto, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al crear repuesto");
            error.put("mensaje", e.getMessage());

            if (e.getMessage().contains("foreign key") || e.getMessage().contains("constraint")) {
                error.put("mensaje", "El proveedor especificado no existe en la base de datos");
            }

            return ResponseEntity.badRequest().body(error);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRepuesto(@PathVariable Integer id, @Valid @RequestBody Repuestos repuesto) {
        try {
            Repuestos repuestoExistente = repuestosService.getRepuestoById(id);
            if (repuestoExistente == null) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Repuesto no encontrado");
                error.put("mensaje", "No existe un repuesto con el ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }

            if (repuesto.getProveedor() == null || repuesto.getProveedor().getIdProveedor() == null) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Proveedor inválido");
                error.put("mensaje", "Debe proporcionar un ID de proveedor válido");
                return ResponseEntity.badRequest().body(error);
            }

            Repuestos updatedRepuesto = repuestosService.updateRepuesto(id, repuesto);
            return ResponseEntity.ok(updatedRepuesto);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al actualizar repuesto");
            error.put("mensaje", e.getMessage());

            if (e.getMessage().contains("foreign key") || e.getMessage().contains("constraint")) {
                error.put("mensaje", "El proveedor especificado no existe en la base de datos");
            }

            return ResponseEntity.badRequest().body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRepuesto(@PathVariable Integer id) {
        try {
            Repuestos repuestoExistente = repuestosService.getRepuestoById(id);
            if (repuestoExistente == null) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Repuesto no encontrado");
                error.put("mensaje", "No existe un repuesto con el ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
            repuestosService.deleteRepuesto(id);
            Map<String, String> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Repuesto eliminado exitosamente");
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al eliminar repuesto");
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