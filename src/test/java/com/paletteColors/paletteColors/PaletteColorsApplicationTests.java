package com.paletteColors.paletteColors;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.paletteColors.paletteColors.DTOs.PaletteColorDTO;
import com.paletteColors.paletteColors.DTOs.PaletteDTO;
import com.paletteColors.paletteColors.models.Palette;
import com.paletteColors.paletteColors.models.PaletteColor;
import com.paletteColors.paletteColors.repositories.PaletteColorRepository;
import com.paletteColors.paletteColors.repositories.PaletteRepository;
import com.paletteColors.paletteColors.services.PaletteColorService;
import com.paletteColors.paletteColors.services.PaletteService;

@SpringBootTest
class PaletteColorsApplicationTests {

	@Test
	void testgetAllPaletteColors() {
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
	void testGetPaletteColorById() {
		// Arrange
		var paletteRepositoryMock = Mockito.mock(PaletteColorRepository.class);
		Mockito.when(paletteRepositoryMock.findById(Mockito.anyLong()))
				.thenReturn(java.util.Optional.of(new PaletteColor()));
		var modelMapperMock = Mockito.mock(ModelMapper.class);
		Mockito.when(modelMapperMock.map(Mockito.any(), Mockito.any())).thenReturn(new PaletteColorDTO());
		var paletteService = new PaletteColorService(paletteRepositoryMock, modelMapperMock);

		// Act
		var paletteColor = paletteService.getPaletteColorById(1L);

		// Assert
		Assert.notNull(paletteColor, "Palette color should not be null");
		
	}

	@Test
	void testSaveOrUpdatePaletteColor() {
		// Arrange
		var paletteRepositoryMock = Mockito.mock(PaletteColorRepository.class);
		var modelMapperMock = Mockito.mock(ModelMapper.class);

		// Mock the PaletteDTO to Palette mapping
		var paletteColorDTO = new PaletteColorDTO();
		var paletteColor = new PaletteColor();
		Mockito.when(modelMapperMock.map(paletteColorDTO, PaletteColor.class)).thenReturn(paletteColor);

		// Mock the save operation
		Mockito.when(paletteRepositoryMock.save(paletteColor)).thenReturn(paletteColor);

		var paletteService = new PaletteColorService(paletteRepositoryMock, modelMapperMock);

		// Act
		var savedPaletteColor = paletteService.saveOrUpdatePaletteColor(paletteColorDTO);

		// Assert
		Assert.notNull(savedPaletteColor, "Palette color should not be null");
}


	@Test
	void deletePaletteColor() {
		// Arrange
		var paletteRepositoryMock = Mockito.mock(PaletteColorRepository.class);
		var modelMapperMock = Mockito.mock(ModelMapper.class);

		// Mock the PaletteDTO to Palette mapping
		var paletteColorDTO = new PaletteColorDTO();
		var paletteColor = new PaletteColor();
		Mockito.when(modelMapperMock.map(paletteColorDTO, PaletteColor.class)).thenReturn(paletteColor);

		// Mock the save operation
		Mockito.when(paletteRepositoryMock.save(paletteColor)).thenReturn(paletteColor);

		var paletteService = new PaletteColorService(paletteRepositoryMock, modelMapperMock);

		// Act
		paletteService.deletePaletteColor(1L);

		// Assert
		Mockito.verify(paletteRepositoryMock).deleteById(1L);
	}
	

	
}
