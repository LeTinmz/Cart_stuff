package org.example.exercice6_correction_meuble_rest.controller;

import org.example.exercice6_correction_meuble_rest.interfaces.IFurnitureService;
import org.example.exercice6_correction_meuble_rest.model.dto.FurnitureDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/furniture")
public class FurnitureController {
    private IFurnitureService furnitureService;

    public FurnitureController(IFurnitureService furnitureService) {
        this.furnitureService = furnitureService;
    }

    @GetMapping
    public ResponseEntity<List<FurnitureDTO>> getAllFurnitures() {
        return new ResponseEntity<>(furnitureService.getAllFurniture(),  HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FurnitureDTO> getFurniture(@PathVariable UUID id) {

        return ResponseEntity.ok(furnitureService.getFurnitureById(id));
        //return new ResponseEntity<>(furnitureService.getFurnitureById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FurnitureDTO> addFurniture(@RequestBody FurnitureDTO furnitureDTO) {
        return new ResponseEntity<>(furnitureService.saveFurniture(furnitureDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteFurniture(@PathVariable UUID id) {
        return new ResponseEntity<>(furnitureService.deleteFurniture(id), HttpStatus.OK);
    }

}
