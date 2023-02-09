package com.techelevator.auctions.controller;

import com.techelevator.auctions.dao.AuctionDao;
import com.techelevator.auctions.dao.MemoryAuctionDao;
import com.techelevator.auctions.model.Auction;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/auctions")
public class AuctionController {

    private AuctionDao dao;

    public AuctionController() {
        this.dao = new MemoryAuctionDao();
    }

    //Step Two: Implement the `list()` method
    @RequestMapping(path = "/auctions", method = RequestMethod.GET)
    public List<Auction> auctions() {
        return dao.list();
    }
// Step Three: Implement the `get()` action

    @RequestMapping(path = "/auctions/{id}", method = RequestMethod.GET)
    public Auction get(@PathVariable int id) {
        return dao.get(id);
    }

    // Step Four:    Implement the `create()` action
    @RequestMapping(path = "/auctions", method = RequestMethod.POST)
    public Auction create(@RequestBody Auction auction) {
        return dao.create(auction);
        }


    //Step Five: Add searching by title
    @RequestMapping(path = "/auctions?title_like=", method = RequestMethod.GET)
    public List<Auction> listOfAuctions(@RequestParam(value = "title_like", defaultValue = "")String title){
        return dao.searchByTitle(title);

    }

   // }
    //Step Six: Add searching by price

    //Step Seven: Search by title and price
}