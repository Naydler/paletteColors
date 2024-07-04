package com.paletteColors.paletteColors.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paletteColors.paletteColors.models.Palette;

@Repository
public interface PaletteRepository extends JpaRepository<Palette, Long> {
    
}
