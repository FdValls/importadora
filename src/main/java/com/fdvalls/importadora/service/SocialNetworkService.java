package com.fdvalls.importadora.service;

import com.fdvalls.importadora.dto.SocialNetworkDTO;
import com.fdvalls.importadora.model.SocialNetwork;
import com.fdvalls.importadora.repository.SocialNetworkRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SocialNetworkService {

    private SocialNetworkRepository socialNetworkRepository;

    public SocialNetworkService(SocialNetworkRepository socialNetworkRepository) {
        this.socialNetworkRepository = socialNetworkRepository;
    }

    public SocialNetworkDTO findNetworkById(Long id) {
        SocialNetwork network = this.socialNetworkRepository.findNetworkById(id);
        return this.transformModelToDTO(network);
    }

    public SocialNetworkDTO findSocialNetworkByUrl(String url) {
        SocialNetwork network = this.socialNetworkRepository.findByUrl(url);
        return this.transformModelToDTO(network);
    }

    private SocialNetworkDTO transformModelToDTO(SocialNetwork n) {
        return new SocialNetworkDTO(n.getId(), n.getDescription(), n.getUrl());
    }

    public SocialNetworkDTO saveSocialNetwork(SocialNetworkDTO network) throws Exception {
        if (this.socialNetworkRepository.findByUrl(network.getUrl()) != null) {
            throw new Exception("cuil already exist");
        }
        return this.transformModelToDTO(this.socialNetworkRepository.save(SocialNetwork.builder()
            .id(network.getId())
            .description(network.getDescription())
            .url(network.getUrl())
            .build()));
    }
}