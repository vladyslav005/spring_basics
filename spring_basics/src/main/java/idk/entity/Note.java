package idk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Note {
    private Long id;
    private String title;
    private String content;
}
