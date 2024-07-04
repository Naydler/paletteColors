package com.paletteColors.paletteColors.DTOs;

import com.paletteColors.paletteColors.models.Palette;
import lombok.Data;

import java.util.List;

@Data
public class CompanyDTO {
    private Long id;
    private String name;
    private String url;
    private List<Palette> palettes;
}
