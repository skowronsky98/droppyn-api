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

    @GetMapping
    public Collection<SizeChartDTO> getAllSizeChart(){
        return sizeChartService.getAllSizeChart()
                .stream()
                .map(SizeChartConverter::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public SizeChartDTO addSizeChart(@RequestBody SizeChart size,
                                     @RequestParam(value = "idBrand") String brandId){
        return SizeChartConverter.toDTO(sizeChartService.addSizeChart(size,brandId));
    }
}
