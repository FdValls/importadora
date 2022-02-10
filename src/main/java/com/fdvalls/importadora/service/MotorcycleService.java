package com.fdvalls.importadora.service;

import com.fdvalls.importadora.dto.MotorcycleDTO;
import com.fdvalls.importadora.model.Engine;
import com.fdvalls.importadora.model.Motorcycle;
import com.fdvalls.importadora.repository.MotorcycleRepository;

public class MotorcycleService {

    private final MotorcycleRepository motorcycleRepository;

    public MotorcycleService(MotorcycleRepository motorcycleRepository) {
        this.motorcycleRepository = motorcycleRepository;
    }

    public MotorcycleDTO findMotorcycleByDomain(String domain) {
        /**
         * Buscar una Motorcycle en el repositorio (supuestamente una DB)
         * Convertir esa motorcycle al dto
         */
        Motorcycle motorcycle = this.motorcycleRepository.findMotorcycleByDomain(domain);

        return this.transformModelToDTO(motorcycle);
    }

    private MotorcycleDTO transformModelToDTO(Motorcycle model) {
        return new MotorcycleDTO(model.getId(), model.getMarca(), model.getEngine().getBrand(), model.getEngine().getHorsePower(),
                model.getPatente());
    }

    public void saveMotorcycle(MotorcycleDTO dto) {
        if (this.motorcycleRepository.findMotorcycleByDomain(dto.getDomain()) != null) {
            throw new IllegalArgumentException();
        }
        if (dto.getBrand() == null) {
            throw new IllegalArgumentException();
        }
        this.motorcycleRepository.save(Motorcycle.builder()
                .id(dto.getId())
                .marca(dto.getBrand())
                .engine(Engine.builder()
                        .brand(dto.getEngineBrand())
                        .horsePower(dto.getEnginePower())
                        .build())
                .build());
    }

}
