package sales.domain.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class JwtRequest {
    private String login;
    private String password;
}
