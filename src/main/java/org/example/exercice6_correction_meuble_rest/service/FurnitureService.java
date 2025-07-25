package org.example.exercice6_correction_meuble_rest.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.exercice6_correction_meuble_rest.interfaces.IFurnitureService;
import org.example.exercice6_correction_meuble_rest.mapper.FurnitureMapper;
import org.example.exercice6_correction_meuble_rest.model.dto.FurnitureDTO;
import org.example.exercice6_correction_meuble_rest.model.entity.Furniture;
import org.example.exercice6_correction_meuble_rest.repository.FurnitureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FurnitureService implements IFurnitureService {
    private FurnitureRepository furnitureRepository;

    public FurnitureService(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }


    @Override
    public List<FurnitureDTO> getAllFurniture() {
        return FurnitureMapper.toDTOs(furnitureRepository.findAll());
    }

    @Override
    public FurnitureDTO getFurnitureById(UUID id) {
        Furniture furniture = furnitureRepository.findById(id).orElse(null);
        if(furniture == null)
            throw new EntityNotFoundException("Furniture with id " + id + " not found");

        return FurnitureMapper.toDTO(furniture);
    }

    @Override
    public FurnitureDTO saveFurniture(FurnitureDTO furniture) {
        Furniture furnitureEntity = FurnitureMapper.toEntity(furniture);
        Furniture furnitureSaved = furnitureRepository.save(furnitureEntity);
        return FurnitureMapper.toDTO(furnitureSaved);
    }

    @Override
    public boolean deleteFurniture(UUID id) {
        if(!furnitureRepository.existsById(id))
            throw new EntityNotFoundException("Furniture with id " + id + " not found");

        furnitureRepository.deleteById(id);
        return true;
    }
}
