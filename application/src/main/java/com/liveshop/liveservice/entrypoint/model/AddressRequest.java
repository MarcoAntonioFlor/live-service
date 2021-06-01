package com.liveshop.liveservice.entrypoint.model;

import com.liveshop.liveservice.domain.Address;
import lombok.Data;

@Data
class AddressRequest{
  private String street;
  private String number;
  private String complement;
  private String neighborhood;
  private String zipCode;
  private String city;
  private String state;
  private String country;

  public static Address toAddress(final AddressRequest address){
    return Address.builder()
        .street(address.getStreet())
        .number(address.getNumber())
        .complement(address.getComplement())
        .neighborhood(address.getNeighborhood())
        .zipCode(address.getZipCode())
        .city(address.getCity())
        .state(address.getState())
        .country(address.getCountry())
        .build();
  }
}