package idk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Note {
    private Long id;
    private String title;
    private String content;
}
