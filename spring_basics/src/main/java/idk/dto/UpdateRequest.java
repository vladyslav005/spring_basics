package idk.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UpdateRequest {
    private Long id;
    private String title;
    private String content;
}
