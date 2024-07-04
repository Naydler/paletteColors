package com.paletteColors.paletteColors.services;


import com.paletteColors.paletteColors.models.Company;
import com.paletteColors.paletteColors.DTOs.CompanyDTO;
import com.paletteColors.paletteColors.repositories.CompanyRepository;

import lombok.Data;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;
    
    
    public CompanyService(CompanyRepository companyRepository, ModelMapper modelMapper) {
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
    }

    public List<CompanyDTO> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        return companies.stream()
                .map(company -> modelMapper.map(company, CompanyDTO.class))
                .collect(Collectors.toList());
    }

    public CompanyDTO getCompanyById(Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        return companyOptional.map(company -> modelMapper.map(company, CompanyDTO.class))
                .orElse(null); // or throw an exception as needed
    }

    public CompanyDTO saveOrUpdateCompany(CompanyDTO companyDto) {
        Company company = modelMapper.map(companyDto, Company.class);
        company = companyRepository.save(company);
        return modelMapper.map(company, CompanyDTO.class);
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

}
