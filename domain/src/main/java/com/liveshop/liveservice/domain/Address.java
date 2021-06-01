package com.liveshop.liveservice.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Builder
@Value
public class Address{
  private String street;
  private String number;
  private String complement;
  private String neighborhood;
  private String zipCode;
  private String city;
  private String state;
  private String country;
}