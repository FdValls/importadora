package com.fdvalls.importadora.service;

import java.util.List;

import com.fdvalls.importadora.dto.WheelDTO;
import com.fdvalls.importadora.model.Wheel;
import com.fdvalls.importadora.repository.WheelRepository;

import org.springframework.stereotype.Service;

@Service
public class WheelService {

    private final WheelRepository wheelRepository;

    public WheelService(WheelRepository wheelRepository) {
        this.wheelRepository = wheelRepository;
    }

    public WheelDTO findWheelById(Long id) {
        Wheel wheel = this.wheelRepository.findWheelById(id);

        return this.transformModelToDTO(wheel);
    }

    private WheelDTO transformModelToDTO(Wheel wheel) {
        return new WheelDTO(wheel.getId(), wheel.getBrand(), wheel.getFrontDiameter(), wheel.getBackDiameter());
    }

    public WheelDTO saveWheel(WheelDTO dto) {
        return this.transformModelToDTO(this.wheelRepository.save(Wheel.builder()
                .id(dto.getId())
                .brand(dto.getBrand())
                .frontDiameter(dto.getFrontDiameter())
                .backDiameter(dto.getBackDiameter())
                .build()));

    }

    public List<WheelDTO> findAllWheels() {
        return this.wheelRepository.findAll().stream().map(this::transformModelToDTO).toList();
    }



}
