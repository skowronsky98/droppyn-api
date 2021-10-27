package com.droppynapi.controller;

import com.droppynapi.converter.SizeChartConverter;
import com.droppynapi.dto.SizeChartDTO;
import com.droppynapi.model.SizeChart;
import com.droppynapi.repo.SizeChartRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sizechart")
@RequiredArgsConstructor
public class SizeChartController {
    @Autowired
    private SizeChartRepo sizeChartRepo;

    @GetMapping("/all")
    public List<SizeChartDTO> getAllSizeChart(){
        return sizeChartRepo.getAllSizeChart()
                .stream()
                .map(SizeChartConverter::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<SizeChartDTO> getSizeChartByBrandId( @RequestParam(value = "brandId") String brandId) {
        return sizeChartRepo.getSizeChartByBrandId(brandId).stream()
                .map(SizeChartConverter::toDTO).collect(Collectors.toList());
    }

//    @GetMapping
//    public SizeChartDTO getSizeChartByBrandId( @RequestParam(value = "brandId") String brandId) {
//        return SizeChartConverter.toDTO(sizeChartService.getSizeChartByBrandId(brandId));
//    }

    @PostMapping
    public SizeChartDTO addSizeChart(@RequestBody SizeChart size,
                                     @RequestParam(value = "brandId") String brandId){
        return SizeChartConverter.toDTO(sizeChartRepo.addSizeChart(size,brandId));
    }
}
