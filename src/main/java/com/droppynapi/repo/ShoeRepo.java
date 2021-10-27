package com.droppynapi.repo;

import com.droppynapi.model.Shoe;

import java.util.Collection;

public interface ShoeRepo {
    Shoe addShoe(Shoe shoe, String idBrand);
    Collection<Shoe> getAllShoes();
    Shoe getShoeById(String id);
    void deleteShoe(String id);
}
