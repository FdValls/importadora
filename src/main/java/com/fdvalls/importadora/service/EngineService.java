package com.fdvalls.importadora.service;

import java.util.List;

import com.fdvalls.importadora.dto.EngineDTO;
import com.fdvalls.importadora.exception.NotExist;
import com.fdvalls.importadora.model.Engine;
import com.fdvalls.importadora.repository.EngineRepository;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
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

    public EngineDTO saveEngine(EngineDTO dto) throws Exception {
        if (this.engineRepository.findEngineBySerialNumber(dto.getSerialNumber()) != null) {
            throw new Exception("Serial number already exists: " + dto.getSerialNumber());
        }
        return this.transformModelToDTO(this.engineRepository.save(Engine.builder()
                .id(dto.getId())
                .brand(dto.getBrand())
                .serialNumber(dto.getSerialNumber())
                .horsePower(dto.getHorsePower())
                .build()));

    }

    public List<Engine> findAllEngines() throws Exception {
        if (this.engineRepository.findAll() == null) {
            throw new Exception("List null");
        }
        return this.engineRepository.findAll();
    }

    public EngineDTO delete(Long id) throws Exception {
        Engine engineDelete = this.engineRepository.findEngineById(id);
        if (engineDelete == null) {
            throw new NotExist("id not exist");
        }
        this.engineRepository.delete(engineDelete);
        return this.transformModelToDTO(engineDelete);
    }

    public EngineDTO update(Long id, EngineDTO dto) {
        Engine engineUpdate = this.engineRepository.findEngineById(id);
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        } else if (engineUpdate == null) {
            throw new IllegalArgumentException("customer not exist");
        } else {
            return this.transformModelToDTO(this.engineRepository.save(Engine.builder()
                    .id(engineUpdate.getId())
                    .brand(dto.getBrand())
                    .serialNumber(dto.getSerialNumber())
                    .horsePower(dto.getHorsePower())
                    .build()));
        }

    }

}
    