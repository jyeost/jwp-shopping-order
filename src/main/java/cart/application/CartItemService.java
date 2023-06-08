package cart.application;

import cart.Repository.CartItemRepository;
import cart.Repository.ProductRepository;
import cart.dao.ProductDao;
import cart.domain.Cart;
import cart.domain.CartItem;
import cart.domain.Member.Member;
import cart.dto.request.CartItemQuantityUpdateRequest;
import cart.dto.request.CartItemRequest;
import cart.dto.response.CartItemResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartItemService {
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    public CartItemService(ProductRepository productRepository, CartItemRepository cartItemRepository) {
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public List<CartItemResponse> findByMember(Member member) {
        Cart cart = cartItemRepository.findByMemberId(member.getId());

        return cart.getCartItems()
                .stream()
                .map(CartItemResponse::of)
                .collect(Collectors.toList());
    }

    public Cart findByCartItemIds(List<Long> cartItemIds) {
        if(cartItemIds.isEmpty()){
            throw new IllegalArgumentException();
        }
        return cartItemRepository.findByIds(cartItemIds);
    }

    public Long add(Member member, CartItemRequest cartItemRequest) {
        return cartItemRepository.save(new CartItem(member, productRepository.getProductById(cartItemRequest.getProductId())));
    }

    public void updateQuantity(Member member, Long id, CartItemQuantityUpdateRequest request) {
        CartItem cartItem = cartItemRepository.findById(id);
        cartItem.checkOwner(member);

        if (request.getQuantity() == 0) {
            cartItemRepository.deleteById(id);
            return;
        }

        cartItem.changeQuantity(request.getQuantity());
        cartItemRepository.updateQuantity(cartItem);
    }

    public void remove(Member member, Long id) {
        CartItem cartItem = cartItemRepository.findById(id);
        cartItem.checkOwner(member);

        cartItemRepository.deleteById(id);
    }

    public void removeByIds(List<Long> cartItemIds) {
        if(cartItemIds.isEmpty()){
            return;
        }
        cartItemRepository.deleteByIds(cartItemIds);
    }
}
