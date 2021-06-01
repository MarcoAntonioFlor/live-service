package com.liveshop.liveservice.domain;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Builder
@Value
public class Company {

  private String cnpj;
  private String companyName;
  private String businessName;
  private String brand;
  private List<Image> images;
  private Address address;
}
