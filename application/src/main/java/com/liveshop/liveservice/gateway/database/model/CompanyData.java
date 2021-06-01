package com.liveshop.liveservice.gateway.database.model;

import com.liveshop.liveservice.domain.Company;
import com.liveshop.liveservice.domain.Image;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CompanyData {

  private String cnpj;
  private String companyName;
  private String businessName;
  private String brand;
  private List<ImageData> images;
  private AddressData address;

  public static CompanyData toCompanyData(final Company company){
    return CompanyData.builder()
        .cnpj(company.getCnpj())
        .companyName(company.getCompanyName())
        .businessName(company.getBusinessName())
        .brand(company.getBrand())
        .images(buidImagesData(company.getImages()))
        .address(AddressData.toAddressData(company.getAddress()))
        .build();
  }

  public static Company toCompany(final CompanyData company){
    return Company.builder()
        .cnpj(company.getCnpj())
        .companyName(company.getCompanyName())
        .businessName(company.getBusinessName())
        .brand(company.getBrand())
        .images(buidImages(company.getImages()))
        .address(AddressData.toAddress(company.getAddress()))
        .build();
  }

  private static List<ImageData> buidImagesData(final List<Image> images){
    return images.stream()
        .map(ImageData::toImageData)
        .collect(Collectors.toList());
  }

  private static List<Image> buidImages(final List<ImageData> images){
    return images.stream()
        .map(ImageData::toImage)
        .collect(Collectors.toList());
  }
}

