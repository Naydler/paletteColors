package com.paletteColors.paletteColors.services;

import com.paletteColors.paletteColors.models.Palette;
import com.paletteColors.paletteColors.DTOs.PaletteDTO;
import com.paletteColors.paletteColors.repositories.PaletteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaletteService {

    private final PaletteRepository paletteRepository;
    private final ModelMapper modelMapper;


    public PaletteService(PaletteRepository paletteRepository, ModelMapper modelMapper) {
        this.paletteRepository = paletteRepository;
        this.modelMapper = modelMapper;
    }

    public List<PaletteDTO> getAllPalettes() {
        List<Palette> palettes = paletteRepository.findAll();
        return palettes.stream()
                .map(palette -> modelMapper.map(palette, PaletteDTO.class))
                .collect(Collectors.toList());
    }

    public PaletteDTO getPaletteById(Long id) {
        Optional<Palette> paletteOptional = paletteRepository.findById(id);
        return paletteOptional.map(palette -> modelMapper.map(palette, PaletteDTO.class))
                .orElse(null); // or throw an exception as needed
    }

    public PaletteDTO saveOrUpdatePalette(PaletteDTO paletteDTO) {
        Palette palette = modelMapper.map(paletteDTO, Palette.class);
        palette = paletteRepository.save(palette);
        return modelMapper.map(palette, PaletteDTO.class);
    }

    public void deletePalette(Long id) {
        paletteRepository.deleteById(id);
    }
}
