package com.gravity.ecommerce.offer;


import com.gravity.ecommerce.offer.OfferResource.OfferResponse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService{

  @Override
  public OfferResponse getOfferResponse1(List<Integer> priceList) {
    if(priceList.size()==0){
      return null;
    }
    return getOfferResponse(priceList);
  }

  private OfferResponse getOfferResponse(List<Integer> productList) {
    List<Integer> discountList = new ArrayList<>();
    List<Integer> payableList = new ArrayList<>();
    System.out.println("Actual Product List"+ productList);
    List<Integer> sortedPriceList1=productList.stream().sorted(Comparator.reverseOrder())
        .collect(Collectors.toList());
    System.out.println("Sorted List:"+sortedPriceList1);
    List<Integer> updatedSortedList = new LinkedList<>(sortedPriceList1);
    sortedPriceList1.forEach(price->{
      if(sortedPriceList1.indexOf(price)+1 < sortedPriceList1.size() && updatedSortedList.contains(price)){
        int nextPrice=getNextDiscountPrice(price,updatedSortedList);
        if(nextPrice==0){
          payableList.add(price);
          updatedSortedList.remove(price);
        }
        if(price>nextPrice && updatedSortedList.contains(price)){
          payableList.add(price);
          updatedSortedList.remove(price);
        }
        if(nextPrice<price && updatedSortedList.contains(nextPrice)){
          discountList.add(nextPrice);
          updatedSortedList.remove(updatedSortedList.get(updatedSortedList.indexOf(nextPrice)));
        }
      }
    });
    if(updatedSortedList.size()==1){
      payableList.add(updatedSortedList.get(0));
      updatedSortedList.remove(0);
    }
    System.out.println("payablelist="+payableList +", discountList="+discountList);
    System.out.println("updatedSortedList List:"+updatedSortedList);

    return OfferResponse.builder()
        .discountPriceList(discountList)
        .payablePriceList(payableList)
        .build();
  }

  private static int getNextDiscountPrice(int currentPrice,List<Integer> updatedSortedList){
    return updatedSortedList.stream().filter(number ->currentPrice>number)
        .findFirst()
        .orElse(0);
  }
}
