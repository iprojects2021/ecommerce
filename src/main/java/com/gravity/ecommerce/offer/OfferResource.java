package com.gravity.ecommerce.offer;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class OfferResource {
  @Setter
  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class OfferRequest{
    List<Integer> priceList;
  }

  @Setter
  @Getter
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class OfferResponse{
    List<Integer> discountPriceList;
    List<Integer> payablePriceList;
  }
}
