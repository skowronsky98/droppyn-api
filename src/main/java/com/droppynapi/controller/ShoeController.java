package com.droppynapi.controller;

import com.droppynapi.converter.ShoeConverter;
import com.droppynapi.dto.ShoeDTO;
import com.droppynapi.model.Shoe;
import com.droppynapi.repo.ShoeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ShoeDTO getShoeById(@RequestParam String id){
        return ShoeConverter.toDTO(shoeRepo.getShoeById(id));
    }

    @GetMapping("/page")
    public Page<ShoeDTO> getPageShoes(@RequestParam(value = "page") int page,
                                      @RequestParam(value = "size") int size){
        Pageable requestPage = PageRequest.of(page, size);
        return shoeRepo.getShoesWithPaging(requestPage).map(ShoeConverter::toDTO);
    }

    //TODO remove references in Offer documents
//    @DeleteMapping
//    public void deleteShoeById(@RequestParam(value = "id") String id){
//        shoeService.deleteShoe(id);
//    }

}
