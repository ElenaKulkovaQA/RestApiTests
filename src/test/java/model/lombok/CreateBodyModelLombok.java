package model.lombok;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateBodyModelLombok {
    String name, job;
}

