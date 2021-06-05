package com.droppynapi.controller;

import com.droppynapi.converter.BrandConverter;
import com.droppynapi.dto.BrandDTO;
import com.droppynapi.model.Brand;
import com.droppynapi.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping("/all")
    public List<BrandDTO> getAllBrands(){
        return brandService.getAllBrands().stream().map(BrandConverter::toDTO).collect(Collectors.toList());
    }

    @PostMapping
    public BrandDTO addBrand(@RequestBody Brand brand){
        return BrandConverter.toDTO(brandService.addBrand(brand));
    }

//    @DeleteMapping
//    public void deleteBrandById(@RequestParam(value = "id") String id){
//        brandService.deleteBrandById(id);
//    }

}
