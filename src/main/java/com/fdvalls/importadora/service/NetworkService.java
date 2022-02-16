package com.fdvalls.importadora.service;

import com.fdvalls.importadora.dto.NetworkDTO;
import com.fdvalls.importadora.model.Network;
import com.fdvalls.importadora.repository.NetworkRepository;

public class NetworkService {

    private NetworkRepository networkRepository;

    public NetworkService(NetworkRepository networkRepository) {
        this.networkRepository = networkRepository;
    }

    public NetworkDTO findDealerById(Long id) {
        Network network = this.networkRepository.findNetworkById(id);

        return this.transformModelToDTO(network);
    }

    private NetworkDTO transformModelToDTO(Network n) {
        return new NetworkDTO(n.getId(), n.getDescription(), n.getUrl());
    }

    public void saveNetwork(NetworkDTO dto) {
        if (this.networkRepository.findNetworkById(dto.getId()) != null) {
            throw new IllegalArgumentException();
        }
        this.networkRepository.save(Network.builder()
                .id(dto.getId())
                .description(dto.getDescription())
                .url(dto.getUrl())
                .build());
    }
    
}
