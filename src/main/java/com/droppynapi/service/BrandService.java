package com.droppynapi.service;

import com.droppynapi.exception.ResourceNotFoundException;
import com.droppynapi.model.Brand;
import com.droppynapi.repo.BrandRepo;
import com.droppynapi.dao.BrandDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService implements BrandRepo {
    @Autowired
    private BrandDao brandDao;

    @Override
    public Brand addBrand(Brand brand){
        return brandDao.save(brand);
    }

    @Override
    public List<Brand> getAllBrands(){
        return brandDao.findAll();
    }

    @Override
    public Brand getBrandById(String id){

        return brandDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("no brand with id: "+id));
    }

    //TODO remove references in SizeChart & Shoe documents
    public void deleteBrandById(String id){
        brandDao.deleteById(id);
    }

}
