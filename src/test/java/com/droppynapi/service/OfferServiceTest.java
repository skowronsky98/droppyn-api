package com.droppynapi.service;

import com.droppynapi.dao.OfferDao;
import com.droppynapi.model.Offer;
import com.droppynapi.model.Shoe;
import com.droppynapi.model.SizeChart;
import com.droppynapi.model.User;
import com.droppynapi.repo.ShoeRepo;
import com.droppynapi.repo.SizeChartRepo;
import com.droppynapi.repo.UserRepo;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class OfferServiceTest {

    @Mock
    private OfferDao offerDao;

    @Mock
    private UserRepo userRepo;

    @Mock
    private ShoeRepo shoeRepo;

    @Mock
    private SizeChartRepo sizeChartRepo;

    @InjectMocks
    private OfferService offerService;


    private List<Offer> prepareMockDataWithNotActiveOffer() {
        List<Offer> offers = new ArrayList<>();
        offers.add(new Offer());
        offers.add(new Offer());
        offers.add(new Offer(
                "1",
                120.0,
                false,
                false,
                "meet wwa",
                null,
                null,
                null
                ));
        return offers;
    }

    private List<Offer> prepareMockDataWithDeletedOffer() {
        List<Offer> offers = new ArrayList<>();
        offers.add(new Offer());
        offers.add(new Offer());
        offers.add(new Offer(
                "1",
                120.0,
                true,
                true,
                "meet wwa",
                null,
                null,
                null
        ));
        return offers;
    }

    private final Shoe shoeMock = new Shoe();
    private final User userMock = new User();
    private final SizeChart sizeChartMock = new SizeChart();
    private final Offer newOffer = new Offer();

    @Before
    public void init(){
        given(userRepo.getUserById(Mockito.anyString())).willReturn(userMock);
        given(shoeRepo.getShoeById(Mockito.anyString())).willReturn(shoeMock);
        given(sizeChartRepo.getSizeChartById(Mockito.anyString())).willReturn(sizeChartMock);
        given(offerDao.save(newOffer)).willReturn(newOffer);
    }

    @Test
    public void shoudAssignShoeToNewOfferObject() {
        Offer createdOffer = offerService.addOffer(newOffer,"1","1","1");
        Assert.assertNotNull(createdOffer.getShoe());
    }

    @Test
    public void shoudAssignUserToNewOfferObject() {
        Offer createdOffer = offerService.addOffer(newOffer,"1","1","1");
        Assert.assertNotNull(createdOffer.getUser());
    }

    @Test
    public void shoudAssignSizeToNewOfferObject() {
        Offer createdOffer = offerService.addOffer(newOffer,"1","1","1");
        Assert.assertNotNull(createdOffer.getSizeChart());
    }

    @Test
    public void shouldReturnOnlyActiveOffers() {
        given(offerDao.findAll()).willReturn(prepareMockDataWithNotActiveOffer());

        List<Offer> offers = offerService.getAllOffers();

        Assert.assertNotEquals(offers, Matchers.hasSize(prepareMockDataWithNotActiveOffer().size()));
    }

    @Test
    public void shouldReturnOnlyNotDeletedOffers() {
        given(offerDao.findAll()).willReturn(prepareMockDataWithDeletedOffer());

        List<Offer> offers = offerService.getAllOffers();

        Assert.assertNotEquals(offers, Matchers.hasSize(prepareMockDataWithDeletedOffer().size()));
    }

}