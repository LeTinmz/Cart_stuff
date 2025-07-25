package org.example.exercice6_correction_meuble_rest.interfaces;

import org.example.exercice6_correction_meuble_rest.model.dto.FurnitureDTO;

import java.util.List;
import java.util.UUID;

public interface IFurnitureService {
    List<FurnitureDTO> getAllFurniture();
    FurnitureDTO getFurnitureById(UUID id);
    FurnitureDTO saveFurniture(FurnitureDTO furniture);
    boolean deleteFurniture(UUID id);
}
