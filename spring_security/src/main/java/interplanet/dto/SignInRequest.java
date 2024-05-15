package interplanet.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SignInRequest {
    private String name;
    private String password;
}
