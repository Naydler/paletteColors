package com.paletteColors.paletteColors.services;


import com.paletteColors.paletteColors.DTOs.PaletteColorDTO;
import com.paletteColors.paletteColors.models.PaletteColor;
import com.paletteColors.paletteColors.repositories.PaletteColorRepository;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaletteColorService {

    private final PaletteColorRepository paletteColorRepository;
    private final ModelMapper modelMapper;

    public PaletteColorService(PaletteColorRepository paletteColorRepository, ModelMapper modelMapper) {
        this.paletteColorRepository = paletteColorRepository;
        this.modelMapper = modelMapper;
    }

    public List<PaletteColorDTO> getAllPaletteColors() {
        List<PaletteColor> paletteColors = paletteColorRepository.findAll();
        return paletteColors.stream()
                .map(paletteColor -> modelMapper.map(paletteColor, PaletteColorDTO.class))
                .collect(Collectors.toList());
    }

    public PaletteColorDTO getPaletteColorById(Long id) {
        Optional<PaletteColor> paletteColorOptional = paletteColorRepository.findById(id);
        return paletteColorOptional.map(paletteColor -> modelMapper.map(paletteColor, PaletteColorDTO.class))
                .orElse(null); // or throw an exception as needed
    }

    public PaletteColorDTO saveOrUpdatePaletteColor(PaletteColorDTO paletteColorDTO) {
        PaletteColor paletteColor = modelMapper.map(paletteColorDTO, PaletteColor.class);
        paletteColor = paletteColorRepository.save(paletteColor);
        return modelMapper.map(paletteColor, PaletteColorDTO.class);
    }

    public void deletePaletteColor(Long id) {
        paletteColorRepository.deleteById(id);
    }
}