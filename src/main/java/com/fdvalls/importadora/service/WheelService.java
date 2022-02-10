package com.fdvalls.importadora.service;

import com.fdvalls.importadora.dto.WheelDTO;
import com.fdvalls.importadora.model.Wheel;
import com.fdvalls.importadora.repository.WheelRepository;

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
        return new WheelDTO(wheel.getId(), wheel.getMarca(), wheel.getRodado());
    }

    public void saveWheel(WheelDTO dto) {
        if (this.wheelRepository.findWheelById(dto.getId()) != null) {
            throw new IllegalArgumentException();
        }
        this.wheelRepository.save(Wheel.builder()
                .id(dto.getId())
                .marca(dto.getBrand())
                .rodado(dto.getRolling())
                .build()
        );
    }

}
