package com.liveshop.liveservice.entrypoint.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.awt.Image;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import lombok.Data;

@Data
public class LiveRequest {

  @NotNull
  @PastOrPresent
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime liveAt;

  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime liveFinish;

  @NotEmpty
  private List<CompanyRequest> companies;

  private String description;
  private ImageRequest image;
  private List<String> presenters;
  private List<TopicRequest> topics;
  private String status;
  private boolean isPrivate = true;
  private boolean isHighlight = false;
}
