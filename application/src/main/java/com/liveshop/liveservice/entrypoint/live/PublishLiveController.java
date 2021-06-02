package com.liveshop.liveservice.entrypoint.live;


import com.liveshop.liveservice.domain.Live;
import com.liveshop.liveservice.usecase.PublishLivesUseCase;
import com.liveshop.liveservice.usecase.SearchLivesUseCase.Input;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class PublishLiveController {

  private final PublishLivesUseCase publishLivesUseCase;

  @PostMapping("/publish")
  @ResponseStatus(HttpStatus.OK)
  public Flux<Live> publish(@RequestBody @Valid final PublishRequest request) {
    return publishLivesUseCase.execute(Input.builder().ids(request.getIds()).build());
  }

}

@Data
@NoArgsConstructor
class PublishRequest {
  @NotNull
  private List<String> ids;
}
