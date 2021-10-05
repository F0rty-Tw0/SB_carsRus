package cars.rus.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class HomeController {
  @GetMapping("/")
  public RedirectView  index() {
    return new RedirectView("swagger-ui/index.html");
  }
}
