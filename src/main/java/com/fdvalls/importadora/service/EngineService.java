package com.fdvalls.importadora.service;

import com.fdvalls.importadora.dto.EngineDTO;
import com.fdvalls.importadora.model.Engine;
import com.fdvalls.importadora.repository.EngineRepository;

public class EngineService {

    private final EngineRepository engineRepository;

    public EngineService(EngineRepository engineRepository) {
        this.engineRepository = engineRepository;
    }

    public EngineDTO fEngineById(Long id) {
        Engine engine = this.engineRepository.findEngineById(id);

        return this.transformModelToDTO(engine);
    }

    private EngineDTO transformModelToDTO(Engine engine) {
        return new EngineDTO(engine.getId(),
                             engine.getBrand(),
                             engine.getSerialNumber(),
                             engine.getHorsePower());
    }

    public void saveEngine(EngineDTO dto) {
        if (this.engineRepository.findEngineById(dto.getId()) != null) {
            throw new IllegalArgumentException();
        }
        this.engineRepository.save(Engine.builder()
                .id(dto.getId())
                .brand(dto.getBrand())
                .serialNumber(dto.getSerialNumber())
                .horsePower(dto.getHorsePower())
                .build());
    }
    
}
