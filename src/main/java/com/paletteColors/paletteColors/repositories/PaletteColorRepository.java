package com.paletteColors.paletteColors.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paletteColors.paletteColors.models.PaletteColor;

@Repository
public interface PaletteColorRepository extends JpaRepository<PaletteColor, Long> {

}
