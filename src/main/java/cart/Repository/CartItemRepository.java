package cart.Repository;

import cart.Repository.mapper.CartItemMapper;
import cart.dao.CartItemDao;
import cart.dao.MemberDao;
import cart.dao.ProductDao;
import cart.domain.CartItem;
import cart.entity.CartItemEntity;
import cart.entity.MemberEntity;
import cart.entity.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static cart.Repository.mapper.CartItemMapper.*;

@Repository
public class CartItemRepository {
    private final ProductDao productDao;
    private final MemberDao memberDao;
    private final CartItemDao cartItemDao;

    public CartItemRepository( ProductDao productDao, MemberDao memberDao, CartItemDao cartItemDao) {
        this.productDao = productDao;
        this.memberDao = memberDao;
        this.cartItemDao = cartItemDao;
    }

    public List<CartItem> findByMemberId(Long memberId) {
        List<CartItemEntity> cartItemEntities = cartItemDao.findByMemberId(memberId);

        MemberEntity memberEntity = memberDao.getMemberById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("id에 해당하는 회원이 없습니다."));

        List<ProductEntity> productsInCarts = getProductInCarts(cartItemEntities);

        return toCartItems(
                cartItemEntities,
                productsInCarts,
                memberEntity);
    }

    private List<ProductEntity> getProductInCarts(List<CartItemEntity> cartItemEntities) {
        List<Long> cartItemIds = cartItemEntities.stream().map(CartItemEntity::getProductId)
                .collect(Collectors.toUnmodifiableList());

        return productDao.getProductByIds(cartItemIds);
    }

    public List<CartItem> findByIds(List<Long> cartItemIds) {
        List<CartItemEntity> cartItemEntities = cartItemDao.findByIds(cartItemIds);
        List<ProductEntity> productsInCarts = getProductInCarts(cartItemEntities);

        //TODO: 멤버도 매핑해주기...
        MemberEntity memberEntity = memberDao.getMemberById(cartItemEntities.get(0).getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("id에 해당하는 회원이 없습니다."));

        return toCartItems(
                cartItemEntities,
                productsInCarts,
                memberEntity);
    }

    public Long save(CartItem cartItem) {
        CartItemEntity cartItemEntity = toCartItemEntity(cartItem);
        return cartItemDao.save(cartItemEntity);
    }

    public CartItem findById(Long id) {
        CartItemEntity cartItemEntity = cartItemDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id에 해당하는 장바구니가 없습니다."));

        ProductEntity productEntity = productDao.getProductById(cartItemEntity.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("id에 해당하는 상품이 없습니다."));

        MemberEntity memberEntity = memberDao.getMemberById(cartItemEntity.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("id에 해당하는 회원이 없습니다."));

        return toCartItem(cartItemEntity, productEntity, memberEntity);
    }

    public void updateQuantity(CartItem cartItem) {
        CartItemEntity cartItemEntity = toCartItemEntity(cartItem);
        cartItemDao.updateQuantity(cartItemEntity);
    }

    public void deleteById(Long id) {
        cartItemDao.deleteById(id);
    }

    public void deleteByIds(List<Long> cartItemIds) {
        cartItemDao.deleteByIds(cartItemIds);
    }
}
