package com.droppynapi.controller;

import com.droppynapi.converter.OfferConverter;
import com.droppynapi.dto.OfferDTO;
import com.droppynapi.model.Offer;
import com.droppynapi.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/offer")
@RequiredArgsConstructor
public class OfferController {

    @Autowired
    private OfferService offerService;

    @GetMapping("/all")
    public List<OfferDTO> getAllOffers(){
        return offerService.getAllOffers().stream()
                .map(OfferConverter::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping
    public OfferDTO getOfferById(@RequestParam(value = "id") String id){
        return OfferConverter.toDTO(offerService.getOfferById(id));
    }

    @GetMapping("/shoe")
    public List<OfferDTO> getShoeOffers(@RequestParam(value = "shoeId") String shoeID){
        return offerService.getAllShoeOffers(shoeID).stream()
                .map(OfferConverter::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/myoffer/all")
    public List<OfferDTO> getUserOffers(@RequestParam(value = "userId") String userId){
        return offerService.getUserOffers(userId).stream()
                .map(OfferConverter::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/favoriteoffer/all")
    public List<OfferDTO> getUserFavoriteOffers(@RequestParam(value = "userId") String userId){
        return offerService.getUserFavoriteOffers(userId).stream()
                .map(OfferConverter::toDTO)
                .collect(Collectors.toList());
    }


    @PostMapping("/myoffer")
    public OfferDTO addOffer(@RequestBody Offer offer,
                             @RequestParam(value = "shoeId") String shoeId,
                             @RequestParam(value = "userId") String userId,
                             @RequestParam(value = "sizeId") String sizeId){
        return OfferConverter.toDTO(offerService.addOffer(offer, shoeId, userId, sizeId));
    }

    @PostMapping("/favoriteoffer")
    public OfferDTO addFavoriteOffer(@RequestParam(value = "id") String id,
                                     @RequestParam(value = "userId") String userId){
        return OfferConverter.toDTO(offerService.addFavoriteOffer(id, userId));
    }

    @DeleteMapping("/myoffer")
    public void deleteMyOfferById(@RequestParam(value = "id") String id,
                                  @RequestParam(value = "userId") String userId){
        offerService.removeMyOfferById(id, userId);
    }


    @DeleteMapping("/favoriteoffer")
    public void deleteFavoriteOfferById(@RequestParam(value = "id") String id,
                                  @RequestParam(value = "userId") String userId){
        offerService.removeFavoriteOfferById(id, userId);
    }

    @PutMapping("/myoffer")
    public OfferDTO updateOffer(@RequestBody Offer offer,
                             @RequestParam(value = "shoeId") String shoeId,
                             @RequestParam(value = "userId") String userId,
                             @RequestParam(value = "sizeId") String sizeId){
        return OfferConverter.toDTO(offerService.updateMyOffer(offer, shoeId, userId, sizeId));
    }

}
