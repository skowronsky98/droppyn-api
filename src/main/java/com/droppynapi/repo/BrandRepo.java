package com.droppynapi.repo;

import com.droppynapi.model.Brand;

import java.util.Collection;

public interface BrandRepo {
    Collection<Brand> getAllBrands();
    Brand addBrand(Brand brand);
    Brand getBrandById(String id);
}
