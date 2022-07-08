package result;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SingleResult<T> extends Result {
    private T data;
}
