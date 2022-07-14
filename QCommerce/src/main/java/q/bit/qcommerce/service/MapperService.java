package q.bit.qcommerce.service;

import lombok.Getter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import q.bit.qcommerce.dto.LiteCartDTO;
import q.bit.qcommerce.dto.LiteUserDTO;
import q.bit.qcommerce.model.Cart;
import q.bit.qcommerce.model.Product;
import q.bit.qcommerce.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
public class MapperService {

    private final ModelMapper mapper = new ModelMapper();

    @Bean
    private ModelMapper setConverters() {

        Converter <User, LiteUserDTO> userToLiteUserDTO = new Converter<User, LiteUserDTO>() {
            @Override
            public LiteUserDTO convert(MappingContext<User, LiteUserDTO> context) {
                User user = context.getSource();
                return new LiteUserDTO(user.getName() + " " + user.getSurname(), user.getEmail());
            }
        };

        Converter <Cart, LiteCartDTO> cartToLiterCartDTO = new Converter<Cart, LiteCartDTO>() {
            @Override
            public LiteCartDTO convert(MappingContext<Cart, LiteCartDTO> context) {
                Cart cart = context.getSource();
                LiteUserDTO liteUser = mapper.map(cart.getUser(), LiteUserDTO.class);
                List<String> productsName = cart.getProducts()
                        .stream()
                        .map(Product::getName)
                        .collect(Collectors.toList());
                return new LiteCartDTO(liteUser, productsName, cart.getTotal(), cart.isCompleted());
            }
        };

        mapper.addConverter(userToLiteUserDTO);
        mapper.addConverter(cartToLiterCartDTO);
        return this.mapper;
    }
}
