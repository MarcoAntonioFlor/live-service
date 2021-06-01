package com.liveshop.liveservice.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Builder
@Value
public class Address{
  private String city;
  private String street;
  private String district;
  private String complement;
  private String number;
  private String zipCode;
}