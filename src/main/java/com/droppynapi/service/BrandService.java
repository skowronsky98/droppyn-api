package com.droppynapi.service;

import com.droppynapi.exception.ResourceNotFoundException;
import com.droppynapi.model.Brand;
import com.droppynapi.repo.BrandRepo;
import com.droppynapi.repodb.BrandDatabaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService implements BrandRepo {
    @Autowired
    private BrandDatabaseRepo brandDatabaseRepo;

    @Override
    public Brand addBrand(Brand brand){
        return brandDatabaseRepo.save(brand);
    }

    @Override
    public List<Brand> getAllBrands(){
        return brandDatabaseRepo.findAll();
    }

    @Override
    public Brand getBrandById(String id){
        return brandDatabaseRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("no brand with id: "+id));
    }

    //TODO remove references in SizeChart & Shoe documents
    public void deleteBrandById(String id){
        brandDatabaseRepo.deleteById(id);
    }

}
