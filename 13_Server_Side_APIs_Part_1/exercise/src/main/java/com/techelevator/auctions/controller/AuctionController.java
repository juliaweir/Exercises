package com.techelevator.auctions.controller;

import com.techelevator.auctions.dao.AuctionDao;
import com.techelevator.auctions.dao.MemoryAuctionDao;
import com.techelevator.auctions.model.Auction;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
//@RequestMapping("/auctions")
public class AuctionController {

    private AuctionDao dao;

    public AuctionController() {
        this.dao = new MemoryAuctionDao();
    }
    @RequestMapping(path = "/auctions", method = RequestMethod.GET)
    public List<Auction> auctions(@RequestParam(name="title_like", defaultValue="") String title,
                        @RequestParam(name="currentBid_lte", defaultValue="0") double bid) {
        if(!title.equals("") && bid > 0) {
            return dao.searchByTitleAndPrice(title, bid);
         } else if (bid > 0) {
            return dao.searchByPrice(bid);
        }  else if (!title.equals("")) {
            return dao.searchByTitle(title);
        }
        return dao.list();
    }
   
// Step Three: Implement the `get()` action

    @RequestMapping(path = "/auctions/{id}", method = RequestMethod.GET)
    public Auction get(@PathVariable int id) {
        Auction auction = dao.get(id);
            if  (auction == null) {
                throw new ResponseStatusException(null);
                } else {
                    return auction;
            }
    }
  
    // Step Four:    Implement the `create()` action
    @RequestMapping(path = "/auctions", method = RequestMethod.POST)
    public Auction create(@RequestBody Auction auction) {
        return dao.create(auction);
        }
}