package com.liveshop.liveservice.gateway.database.model;

import com.liveshop.liveservice.domain.Address;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class AddressData{
  private String city;
  private String street;
  private String district;
  private String complement;
  private String number;
  private String zipCode;

  public static AddressData toAddressData(final Address address){
    return AddressData.builder()
        .city(address.getCity())
        .street(address.getStreet())
        .district(address.getDistrict())
        .complement(address.getComplement())
        .number(address.getNumber())
        .zipCode(address.getZipCode())
        .build();
  }

  public static Address toAddress(final AddressData address){
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
