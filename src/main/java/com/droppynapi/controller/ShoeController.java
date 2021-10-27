package com.droppynapi.controller;

import com.droppynapi.converter.ShoeConverter;
import com.droppynapi.dto.ShoeDTO;
import com.droppynapi.model.Shoe;
import com.droppynapi.repo.ShoeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shoe")
@RequiredArgsConstructor
public class ShoeController {

    @Autowired
    private ShoeRepo shoeRepo;

    @GetMapping("/all")
    public List<ShoeDTO> getAllShoes(){
        return shoeRepo.getAllShoes()
                .stream().map(ShoeConverter::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ShoeDTO addShoe(@RequestBody Shoe shoe,
                           @RequestParam(value = "idBrand") String brandId){
        return ShoeConverter.toDTO(shoeRepo.addShoe(shoe,brandId));
    }

    @GetMapping
    public ShoeDTO getShoeById(@RequestBody String id){
        return ShoeConverter.toDTO(shoeRepo.getShoeById(id));
    }


    //TODO remove references in Offer documents
//    @DeleteMapping
//    public void deleteShoeById(@RequestParam(value = "id") String id){
//        shoeService.deleteShoe(id);
//    }

}
