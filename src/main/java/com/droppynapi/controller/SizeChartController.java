package com.droppynapi.controller;

import com.droppynapi.converter.SizeChartConverter;
import com.droppynapi.dto.SizeChartDTO;
import com.droppynapi.model.Shoe;
import com.droppynapi.model.SizeChart;
import com.droppynapi.service.SizeChartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sizechart")
@RequiredArgsConstructor
public class SizeChartController {
    @Autowired
    private SizeChartService sizeChartService;

    @GetMapping("/all")
    public Collection<SizeChartDTO> getAllSizeChart(){
        return sizeChartService.getAllSizeChart()
                .stream()
                .map(SizeChartConverter::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping
    public Collection<SizeChartDTO> getSizeChartByBrandId( @RequestParam(value = "brandId") String brandId) {
        return sizeChartService.getSizeChartByBrandId(brandId).stream()
                .map(SizeChartConverter::toDTO).collect(Collectors.toList());
    }

//    @GetMapping
//    public SizeChartDTO getSizeChartByBrandId( @RequestParam(value = "brandId") String brandId) {
//        return SizeChartConverter.toDTO(sizeChartService.getSizeChartByBrandId(brandId));
//    }

    @PostMapping
    public SizeChartDTO addSizeChart(@RequestBody SizeChart size,
                                     @RequestParam(value = "brandId") String brandId){
        return SizeChartConverter.toDTO(sizeChartService.addSizeChart(size,brandId));
    }
}
