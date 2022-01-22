package com.droppynapi.controller;

import com.droppynapi.dao.OfferDao;
import com.droppynapi.dto.OfferDTO;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;



class OfferControllerTest {

    @Mock
    private OfferDao offerDao;

    @Test
    void getAllOffers() {
        OfferController offerController = Mockito.mock(OfferController.class);

//        Mockito.when(offerController.getAllOffers()).thenReturn(prepareMockData());

        MatcherAssert.assertThat(offerController.getAllOffers(),Matchers.hasSize(2));
    }

    @Test
    void addOffer(){
        OfferController offerController = Mockito.mock(OfferController.class);

//        Mockito.when(offerController.addOffer()).thenReturn(prepareMockData());


    }


}