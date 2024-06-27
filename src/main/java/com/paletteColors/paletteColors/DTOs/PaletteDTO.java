package com.paletteColors.paletteColors.DTOs;

import com.paletteColors.paletteColors.models.PaletteColor;
import lombok.Data;

import java.util.List;

@Data
public class PaletteDTO {
    private Long id;
    private String name;
    private List<PaletteColor> colors;
}
