package com.paletteColors.paletteColors.services;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.util.Assert;

import com.paletteColors.paletteColors.DTOs.PaletteDTO;
import com.paletteColors.paletteColors.models.Palette;
import com.paletteColors.paletteColors.repositories.PaletteRepository;

class PaletteServiceTests {
    @Test
    void testGetAllPaletteColors() {
        // Arrange
        var paletteRepositoryMock = Mockito.mock(PaletteRepository.class);
        Mockito.when(paletteRepositoryMock.findAll()).thenReturn(List.of(new Palette()));
        var modelMapperMock = Mockito.mock(ModelMapper.class);
        Mockito.when(modelMapperMock.map(Mockito.any(), Mockito.any())).thenReturn(new PaletteDTO());
        var paletteService = new PaletteService(paletteRepositoryMock, modelMapperMock);

        // Act
        var paletteColors = paletteService.getAllPalettes();

        // Assert
        Assert.notEmpty(paletteColors, "Palette colors should not be empty");
        Assert.noNullElements(paletteColors, "Palette colors should not contain null elements");
    }

    @Test
    void testGetPaletteById() {
        // Arrange
        var paletteRepositoryMock = Mockito.mock(PaletteRepository.class);
        Mockito.when(paletteRepositoryMock.findById(Mockito.anyLong()))
                .thenReturn(java.util.Optional.of(new Palette()));
        var modelMapperMock = Mockito.mock(ModelMapper.class);
        Mockito.when(modelMapperMock.map(Mockito.any(), Mockito.any())).thenReturn(new PaletteDTO());
        var paletteService = new PaletteService(paletteRepositoryMock, modelMapperMock);

        // Act
        var paletteColor = paletteService.getPaletteById(1L);

        // Assert
        Assert.notNull(paletteColor, "Palette color should not be null");
    }

    @Test
void testSaveOrUpdatePalette() {
    // Arrange
    var paletteRepositoryMock = Mockito.mock(PaletteRepository.class);
    var modelMapperMock = Mockito.mock(ModelMapper.class);

    // Mock the PaletteDTO to Palette mapping
    var paletteDTO = new PaletteDTO();
    var palette = new Palette();
    Mockito.when(modelMapperMock.map(paletteDTO, Palette.class)).thenReturn(palette);

    // Mock the save operation
    Mockito.when(paletteRepositoryMock.save(palette)).thenReturn(palette);

    // Mock the Palette to PaletteDTO mapping
    Mockito.when(modelMapperMock.map(palette, PaletteDTO.class)).thenReturn(paletteDTO);

    var paletteService = new PaletteService(paletteRepositoryMock, modelMapperMock);

    // Act
    var result = paletteService.saveOrUpdatePalette(paletteDTO);

    // Assert
    Assert.notNull(result, "Palette color should not be null");
    Mockito.verify(modelMapperMock).map(paletteDTO, Palette.class);
    Mockito.verify(paletteRepositoryMock).save(palette);
    Mockito.verify(modelMapperMock).map(palette, PaletteDTO.class);
}


    @Test
    void testDeletePalette() {
        // Arrange
        var paletteRepositoryMock = Mockito.mock(PaletteRepository.class);
        var modelMapperMock = Mockito.mock(ModelMapper.class);
        var paletteService = new PaletteService(paletteRepositoryMock, modelMapperMock);

        // Act
        paletteService.deletePalette(1L);

        // Assert
        Mockito.verify(paletteRepositoryMock, Mockito.times(1)).deleteById(1L);
    }
}
