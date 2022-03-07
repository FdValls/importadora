package com.fdvalls.importadora.service;

import java.util.List;
import java.util.Optional;

import com.fdvalls.importadora.dto.MotorcycleDTO;
import com.fdvalls.importadora.model.Dealer;
import com.fdvalls.importadora.model.Engine;
import com.fdvalls.importadora.model.Importer;
import com.fdvalls.importadora.model.Motorcycle;
import com.fdvalls.importadora.model.Wheel;
import com.fdvalls.importadora.repository.DealerRepository;
import com.fdvalls.importadora.exception.DealerNotFoundException;
import com.fdvalls.importadora.repository.EngineRepository;
import com.fdvalls.importadora.repository.ImporterRepository;
import com.fdvalls.importadora.repository.MotorcycleRepository;
import com.fdvalls.importadora.repository.WheelRepository;

import org.springframework.stereotype.Service;

@Service
public class MotorcycleService {

    // dealer, engine, importer, wheel
    private final MotorcycleRepository motorcycleRepository;
    private final DealerRepository dealerRepository;
    private final EngineRepository engineRepository;
    private final ImporterRepository importerRepository;
    private final WheelRepository wheelRepository;

    public MotorcycleService(MotorcycleRepository motorcycleRepository, DealerRepository dealerRepository,
            EngineRepository engineRepository, ImporterRepository importerRepository,
            WheelRepository wheelRepository) {
        this.motorcycleRepository = motorcycleRepository;
        this.dealerRepository = dealerRepository;
        this.engineRepository = engineRepository;
        this.importerRepository = importerRepository;
        this.wheelRepository = wheelRepository;
    }

    public MotorcycleDTO findMotorcycleByDomain(String domain) {
        /**
         * Buscar una Motorcycle en el repositorio (supuestamente una DB)
         * Convertir esa motorcycle al dto
         */
        Motorcycle motorcycle = this.motorcycleRepository.findByDomain(domain);

        return this.transformModelToDTO(motorcycle);
    }

    private MotorcycleDTO transformModelToDTO(Motorcycle model) {
        return MotorcycleDTO.builder()
                .id(model.getId())
                .chasis(model.getChasis())
                .domain(model.getDomain())
                .brand(model.getBrand())
                .year(model.getYear())
                .kms(model.getKms())
                .isNew(model.getIsNew())
                .weight(model.getWeight())
                .build();

    }

    public MotorcycleDTO saveMotorcycle(MotorcycleDTO dto) throws Exception {
        if (this.motorcycleRepository.findByDomain(dto.getDomain()) != null) {
            throw new IllegalArgumentException();
        }
        if (dto.getBrand() == null) {
            throw new IllegalArgumentException();
        }

        Optional<Dealer> dealerOptional = this.dealerRepository.findById(dto.getDealerId());
        Dealer dealer = dealerOptional.orElseThrow(
                () -> new DealerNotFoundException("Dealer with id " + dto.getDealerId() + " does not exists"));

        Optional<Engine> engineOptional = this.engineRepository.findById(dto.getEngineId());
        Engine engine = engineOptional.orElseThrow(
                () -> new DealerNotFoundException("Engine with id " + dto.getEngineId() + " does not exists"));

        Optional<Importer> importerOptional = this.importerRepository.findById(dto.getImporterId());
        Importer importer = importerOptional.orElseThrow(
                () -> new DealerNotFoundException("Importer with id " + dto.getImporterId() + " does not exists"));

        Optional<Wheel> wheelOptional = this.wheelRepository.findById(dto.getWheelId());
        Wheel wheel = wheelOptional.orElseThrow(
                () -> new DealerNotFoundException("Wheel with id " + dto.getWheelId() + " does not exists"));

        Motorcycle motorcycle = Motorcycle.builder()
                .id(dto.getId())
                .chasis(dto.getChasis())
                .engine(engine)
                .wheel(wheel)
                .domain(dto.getDomain())
                .brand(dto.getBrand())
                .year(dto.getYear())
                .kms(dto.getKms())
                .isNew(dto.getIsNew())
                .weight(dto.getWeight())
                .dealer(dealer)
                .importer(importer)
                .build();

        motorcycle = this.motorcycleRepository.save(motorcycle);
        this.dealerRepository.save(dealer);
        this.engineRepository.save(engine);
        this.importerRepository.save(importer);
        this.wheelRepository.save(wheel);

        return this.transformModelToDTO(motorcycle);
    }

    public List<MotorcycleDTO> findAllMotorcycles() {
        return this.motorcycleRepository.findAll().stream().map(this::transformModelToDTO).toList();
    }

    public MotorcycleDTO update(Long id, MotorcycleDTO dto) throws DealerNotFoundException {
        Motorcycle motorUpdate = this.motorcycleRepository.findMotorcycleById(id);
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        } else if (motorUpdate == null) {
            throw new IllegalArgumentException("Motorcycle not exist");
        } else {

            Optional<Dealer> dealerOptional = this.dealerRepository.findById(dto.getDealerId());
            Dealer dealer = dealerOptional.orElseThrow(
                    () -> new DealerNotFoundException("Dealer with id " + dto.getDealerId() + " does not exists"));

            Optional<Engine> engineOptional = this.engineRepository.findById(dto.getEngineId());
            Engine engine = engineOptional.orElseThrow(
                    () -> new DealerNotFoundException("Engine with id " + dto.getEngineId() + " does not exists"));

            Optional<Importer> importerOptional = this.importerRepository.findById(dto.getImporterId());
            Importer importer = importerOptional.orElseThrow(
                    () -> new DealerNotFoundException("Importer with id " + dto.getImporterId() + " does not exists"));

            Optional<Wheel> wheelOptional = this.wheelRepository.findById(dto.getWheelId());
            Wheel wheel = wheelOptional.orElseThrow(
                    () -> new DealerNotFoundException("Wheel with id " + dto.getWheelId() + " does not exists"));

            Motorcycle motorcycle = Motorcycle.builder()
                    .id(dto.getId())
                    .chasis(dto.getChasis())
                    .engine(engine)
                    .wheel(wheel)
                    .domain(dto.getDomain())
                    .brand(dto.getBrand())
                    .year(dto.getYear())
                    .kms(dto.getKms())
                    .isNew(dto.getIsNew())
                    .weight(dto.getWeight())
                    .dealer(dealer)
                    .importer(importer)
                    .build();

            motorcycle = this.motorcycleRepository.save(motorcycle);
            this.dealerRepository.save(dealer);
            this.engineRepository.save(engine);
            this.importerRepository.save(importer);
            this.wheelRepository.save(wheel);

            return this.transformModelToDTO(motorcycle);
        }
    }

}
