package com.droppynapi.controller;

import com.droppynapi.converter.BrandConverter;
import com.droppynapi.dto.BrandDTO;
import com.droppynapi.model.Brand;
import com.droppynapi.repo.BrandRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
public class BrandController {
    @Autowired
    private BrandRepo brandRepo;

    @GetMapping("/all")
    public List<BrandDTO> getAllBrands(){
        return brandRepo.getAllBrands().stream().map(BrandConverter::toDTO).collect(Collectors.toList());
    }

    @PostMapping
    public BrandDTO addBrand(@RequestBody Brand brand){
        return BrandConverter.toDTO(brandRepo.addBrand(brand));
    }

//    @DeleteMapping
//    public void deleteBrandById(@RequestParam(value = "id") String id){
//        brandService.deleteBrandById(id);
//    }

}
