package com.droppynapi.service;

import com.droppynapi.exception.ResourceNotFoundException;
import com.droppynapi.model.Shoe;
import com.droppynapi.repo.BrandRepo;
import com.droppynapi.repo.ShoeRepo;
import com.droppynapi.repodb.ShoeDatabaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoeService implements ShoeRepo {
    @Autowired
    private ShoeDatabaseRepo shoeDatabaseRepo;

    @Autowired
    private BrandRepo brandRepo;

    @Override
    public Shoe addShoe(Shoe shoe, String idBrand){
        shoe.setBrand(brandRepo.getBrandById(idBrand));
        return shoeDatabaseRepo.save(shoe);
    }

    @Override
    public List<Shoe> getAllShoes(){
        return shoeDatabaseRepo.findAll();
    }

    @Override
    public Shoe getShoeById(String id){
        return shoeDatabaseRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("no shoe with id: "+id));
    }

    @Override
    public void deleteShoe(String id){
        shoeDatabaseRepo.deleteById(id);
    }
}
