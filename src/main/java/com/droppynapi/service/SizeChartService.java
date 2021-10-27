package com.droppynapi.service;

import com.droppynapi.exception.ResourceNotFoundException;
import com.droppynapi.model.Brand;
import com.droppynapi.model.SizeChart;
import com.droppynapi.repo.BrandRepo;
import com.droppynapi.repo.SizeChartRepo;
import com.droppynapi.repodb.SizeChartDatabaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SizeChartService implements SizeChartRepo {
    @Autowired
    private SizeChartDatabaseRepo sizeChartRepo;

    @Autowired
    private BrandRepo brandRepo;

    @Override
    public SizeChart addSizeChart(SizeChart sizeChart, String brandId){
        Brand brand = brandRepo.getBrandById(brandId);
        sizeChart.setBrand(brand);
        return sizeChartRepo.save(sizeChart);
    }

    @Override
    public List<SizeChart> getSizeChartByBrandId(String brandId){
        return sizeChartRepo.findAll().stream().filter(size -> size.getBrand()!=null && size.getBrand().get_id().equals(brandId))
                .collect(Collectors.toList());
    }

    @Override
    public SizeChart getSizeChartById(String id){
        return sizeChartRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("no size with id: "+id));
    }

    @Override
    public List<SizeChart> getAllSizeChart(){
        return sizeChartRepo.findAll();
    }

    //TODO remove reference in Offer documntes
    @Override
    public void removeSizeChartById(String id){
        sizeChartRepo.deleteById(id);
    }
}
