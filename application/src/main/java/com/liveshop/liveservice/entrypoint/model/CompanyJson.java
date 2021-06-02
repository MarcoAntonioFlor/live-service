package com.liveshop.liveservice.entrypoint.model;

import com.liveshop.liveservice.domain.Company;
import com.liveshop.liveservice.domain.Image;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

@Data
public class CompanyJson {

  @CNPJ
  private String cnpj;
  private String companyName;
  private String businessName;
  private String brand;
  private List<ImageJson> images;
  private AddressJson address;

  public static Company toCompany(final CompanyJson company){
    return Company.builder()
        .cnpj(company.getCnpj())
        .companyName(company.getCompanyName())
        .businessName(company.getBusinessName())
        .brand(company.getBrand())
        .images(buidImages(company.getImages()))
        .address(AddressJson.toAddress(company.getAddress()))
        .build();
  }

  private static List<Image> buidImages(final List<ImageJson> images){
    return images.stream()
        .map(ImageJson::toImage)
        .collect(Collectors.toList());
  }
}
