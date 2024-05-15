package idk.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

@Data
@ToString
@Validated
public class UpdateRequest {

    @NotBlank
    @Size(min = 1, max = 50)
    @NotNull
    private String title;
    @NotBlank
    @Size(min = 1, max = 255)
    @NotNull
    private String content;
}
