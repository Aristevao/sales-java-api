package sales.domain.dto.response;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

public class ErrorEntity {

    @Getter
    private final List<String> errors;

    public ErrorEntity(final String message) {
        this.errors = Collections.singletonList(message);
    }
}
