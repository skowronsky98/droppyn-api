package com.droppynapi.service;

import com.droppynapi.exception.ResourceNotFoundException;
import com.droppynapi.model.Shoe;
import com.droppynapi.repo.ShoeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ShoeService {
    @Autowired
    private ShoeRepo shoeRepo;

    @Autowired
    private BrandService brandService;

    public Shoe addShoe(Shoe shoe, String idBrand){
        shoe.setBrand(brandService.getBrandById(idBrand));
        return shoeRepo.save(shoe);
    }

    public Collection<Shoe> getAllShoes(){
        return shoeRepo.findAll();
    }

    public Shoe getShoeById(String id){
        return shoeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("no shoe with id: "+id));
    }

    public void deleteShoe(String id){
        shoeRepo.deleteById(id);
    }
}
