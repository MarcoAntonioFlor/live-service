package com.liveshop.liveservice.entrypoint.model;

import com.liveshop.liveservice.domain.Company;
import com.liveshop.liveservice.domain.Image;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class CompanyRequest {

  private String cnpj;
  private String companyName;
  private String businessName;
  private String brand;
  private List<ImageRequest> images;
  private AddressRequest address;

  public static Company toCompany(final CompanyRequest company){
    return Company.builder()
        .cnpj(company.getCnpj())
        .companyName(company.getCompanyName())
        .businessName(company.getBusinessName())
        .brand(company.getBrand())
        .images(buidImages(company.getImages()))
        .address(AddressRequest.toAddress(company.getAddress()))
        .build();
  }

  private static List<Image> buidImages(final List<ImageRequest> images){
    return images.stream()
        .map(ImageRequest::toImage)
        .collect(Collectors.toList());
  }
}
