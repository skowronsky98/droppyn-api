package com.droppynapi.service;

import com.droppynapi.exception.ResourceNotFoundException;
import com.droppynapi.model.Brand;
import com.droppynapi.repo.BrandRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BrandService {
    @Autowired
    private BrandRepo brandRepo;

    public Brand addBrand(Brand brand){
        return brandRepo.save(brand);
    }

    public Collection<Brand> getAllBrands(){
        return brandRepo.findAll();
    }

    public Brand getBrandById(String id){
        return brandRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("no brand with id: "+id));
    }

    //TODO remove references in SizeChart & Shoe documents
    public void deleteBrandById(String id){
        brandRepo.deleteById(id);
    }

}
