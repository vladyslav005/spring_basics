package idk.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "note")
public class Note {

    @Id
    private Long id;

    @NotBlank
    @Size(min = 1, max = 50)
    @NotNull
    private String title;

    @NotBlank
    @Size(min = 1, max = 255)
    @NotNull
    private String content;
}
