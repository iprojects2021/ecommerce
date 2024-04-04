package com.gravity.ecommerce.offer;

import com.gravity.ecommerce.offer.OfferResource.OfferRequest;
import com.gravity.ecommerce.offer.OfferResource.OfferResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class OfferController {

  @Autowired
  OfferService offerService;

  @PostMapping("/offer")
  ResponseEntity<?> getOfferResponse(@RequestBody OfferRequest priceList)throws Exception{
    OfferResponse response=offerService.getOfferResponse1(priceList);
    if(response==null){
      return ResponseEntity.badRequest().body("Input is Empty");
    }
    return ResponseEntity.ok(offerService.getOfferResponse1(priceList));
  }
}
