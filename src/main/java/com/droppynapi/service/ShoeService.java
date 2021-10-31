package com.droppynapi.service;

import com.droppynapi.exception.ResourceNotFoundException;
import com.droppynapi.model.Shoe;
import com.droppynapi.repo.BrandRepo;
import com.droppynapi.repo.ShoeRepo;
import com.droppynapi.dao.ShoeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoeService implements ShoeRepo {
    @Autowired
    private ShoeDao shoeDao;

    @Autowired
    private BrandRepo brandRepo;

    @Override
    public Shoe addShoe(Shoe shoe, String idBrand){
        shoe.setBrand(brandRepo.getBrandById(idBrand));
        return shoeDao.save(shoe);
    }

    @Override
    public List<Shoe> getAllShoes(){
        return shoeDao.findAll();
    }

    @Override
    public Shoe getShoeById(String id){
        return shoeDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("no shoe with id: "+id));
    }

    @Override
    public void deleteShoe(String id){
        shoeDao.deleteById(id);
    }

    @Override
    public Page<Shoe> getShoesWithPaging(Pageable pageable) {
        return shoeDao.findAll(pageable);
    }
}
