package com.techelevator.auctions.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.techelevator.auctions.model.Auction;

public class AuctionService {

    public static String API_BASE_URL = "http://localhost:3000/auctions/";
    private RestTemplate restTemplate = new RestTemplate();


    public Auction[] getAllAuctions() {
        // call api here
        ResponseEntity<Auction[]> response = restTemplate.getForEntity(API_BASE_URL, Auction[].class);
        Auction[] allAuctions = response.getBody();


    return allAuctions;
    }

    public Auction getAuction(int id) {
        // call api here
        return restTemplate.getForObject(API_BASE_URL+ id, Auction.class);

    }

    public Auction[] getAuctionsMatchingTitle(String title) {
        ResponseEntity<Auction[]> response = restTemplate.getForEntity(
                API_BASE_URL.substring(0, API_BASE_URL.length()-1) + "?title_like=" + title, Auction[].class);
        Auction[] auctions = response.getBody();
        return auctions;
    }

    public Auction[] getAuctionsAtOrBelowPrice(double price) {
        // call api here
        ResponseEntity<Auction[]> response = restTemplate.getForEntity(API_BASE_URL.substring(0, API_BASE_URL.length()-1) +
        "?currentBid_lte=" + price, Auction[].class);
        Auction[] auctions = response.getBody();
        return  auctions;
    }

}
