package org.example.exercice6_correction_meuble_rest.mapper;

import org.example.exercice6_correction_meuble_rest.model.dto.FurnitureDTO;
import org.example.exercice6_correction_meuble_rest.model.entity.Furniture;

import java.util.List;

public class FurnitureMapper {

    public static Furniture toEntity(FurnitureDTO furnitureDTO){
        return new Furniture(null, furnitureDTO.name(), furnitureDTO.description(), furnitureDTO.price(), furnitureDTO.stock());
    }

    public static FurnitureDTO toDTO(Furniture furniture){
        return new FurnitureDTO(furniture.getId(), furniture.getName(), furniture.getDescription(), furniture.getPrice(), furniture.getStock());
    }

    public static List<FurnitureDTO> toDTOs(List<Furniture> furnitures){
        return furnitures.stream().map(FurnitureMapper::toDTO).toList();
    }
}