package com.droppynapi.service;

import com.droppynapi.exception.ResourceNotFoundException;
import com.droppynapi.model.Brand;
import com.droppynapi.model.SizeChart;
import com.droppynapi.repo.SizeChartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.Collection;

@Service
public class SizeChartService {
    @Autowired
    private SizeChartRepo sizeChartRepo;

    @Autowired
    private BrandService brandService;

    public SizeChart addSizeChart(SizeChart sizeChart, String brandId){
        Brand brand = brandService.getBrandById(brandId);
        sizeChart.setBrand(brand);
        return sizeChartRepo.save(sizeChart);
    }

    public SizeChart getSizeChartById(String id){
        return sizeChartRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("no size with id: "+id));
    }

    public Collection<SizeChart> getAllSizeChart(){
        return sizeChartRepo.findAll();
    }

    //TODO remove reference in Offer documntes
    public void removeSizeChartById(String id){
        sizeChartRepo.deleteById(id);
    }
}
