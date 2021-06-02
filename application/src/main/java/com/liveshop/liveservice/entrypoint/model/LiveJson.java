package com.liveshop.liveservice.entrypoint.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.liveshop.liveservice.entrypoint.model.CompanyJson;
import com.liveshop.liveservice.entrypoint.model.ImageJson;
import com.liveshop.liveservice.entrypoint.model.TopicJson;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import lombok.Data;

@Data
public class LiveJson {

  @NotNull
  @PastOrPresent
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime liveAt;
  
  @NotEmpty
  private List<CompanyJson> companies;

  private String description;
  private ImageJson image;
  private List<String> presenters;
  private List<TopicJson> topics;
  private String status;
  private boolean isPrivate = true;
  private boolean isHighlight = false;
}
