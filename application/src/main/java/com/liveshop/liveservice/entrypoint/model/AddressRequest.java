package com.liveshop.liveservice.entrypoint.model;

import com.liveshop.liveservice.domain.Address;
import lombok.Data;

@Data
class AddressRequest{
  private String city;
  private String street;
  private String district;
  private String complement;
  private String number;
  private String zipCode;

  public static Address toAddress(final AddressRequest address){
    return Address.builder()
        .city(address.getCity())
        .street(address.getStreet())
        .district(address.getDistrict())
        .complement(address.getComplement())
        .number(address.getNumber())
        .zipCode(address.getZipCode())
        .build();
  }
}