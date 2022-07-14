package q.bit.qcommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LiteCartDTO {

    private LiteUserDTO user;

    private List<String> productsName;

    private double total;

    private boolean completed;

}
