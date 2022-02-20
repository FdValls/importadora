package com.fdvalls.importadora.service;

import com.fdvalls.importadora.dto.SocialNetworkDTO;
import com.fdvalls.importadora.model.SocialNetwork;
import com.fdvalls.importadora.repository.NetworkRepository;

public class SocialNetworkService {

    private NetworkRepository networkRepository;

    public SocialNetworkService(NetworkRepository networkRepository) {
        this.networkRepository = networkRepository;
    }

    public SocialNetworkDTO findNetworkById(Long id) {
        SocialNetwork network = this.networkRepository.findNetworkById(id);

        return this.transformModelToDTO(network);
    }

    private SocialNetworkDTO transformModelToDTO(SocialNetwork n) {
        return new SocialNetworkDTO(n.getId(), n.getDescription(), n.getUrl(), n.getDealer(), n.getImporter());
    }

    public void saveNetwork(SocialNetworkDTO dto) {
        if (this.networkRepository.findNetworkById(dto.getId()) != null) {
            throw new IllegalArgumentException();
        }
        this.networkRepository.save(SocialNetwork.builder()
                .id(dto.getId())
                .description(dto.getDescription())
                .url(dto.getUrl())
                .dealer(dto.getDealer())
                .importer(dto.getImporter())
                .build());
    }
    
}
