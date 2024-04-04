package com.gravity.ecommerce.offer;

import com.gravity.ecommerce.offer.OfferResource.OfferRequest;
import com.gravity.ecommerce.offer.OfferResource.OfferResponse;
import java.util.List;

public interface OfferService {

  OfferResponse getOfferResponse1(OfferRequest priceList);

}
