package com.liveshop.liveservice.gateway.database.model;

import com.liveshop.liveservice.domain.Address;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class AddressData{
  private String street;
  private String number;
  private String complement;
  private String neighborhood;
  private String zipCode;
  private String city;
  private String state;
  private String country;

  public static AddressData toAddressData(final Address address){
    return AddressData.builder()
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

  public static Address toAddress(final AddressData address){
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
