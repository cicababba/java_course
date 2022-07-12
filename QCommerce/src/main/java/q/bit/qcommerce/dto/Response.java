package q.bit.qcommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response{

    private String message;

    private int status;

    private Object payload;

    private long timestamp;
}
