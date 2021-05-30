package com.liveshop.liveservice.entrypoint.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class LiveRequest {

  @NotNull
  @PastOrPresent
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime liveAt;
  @NotEmpty
  private List<String> influencers;
  private List<String> brands;
  private List<String> topics;
  @NotNull
  private boolean isPrivate;

}
