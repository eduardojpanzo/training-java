package ao.eduardojpanzo.storebook.model;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResultData(
   List<Book> items,
   int totalItems
) {
}
