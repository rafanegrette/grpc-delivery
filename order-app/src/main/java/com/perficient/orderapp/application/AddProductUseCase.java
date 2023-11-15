package com.perficient.orderapp.application;

import com.perficient.orderapp.domain.Cart;
import com.perficient.orderapp.domain.port.RetrieveCustomer;
import com.perficient.orderapp.domain.port.RetrieveProductItem;
import com.perficient.orderapp.domain.port.SaveCustomerCart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddProductUseCase {


  private final RetrieveProductItem retrieveProductItem;
  private final RetrieveCustomer retrieveCustomer;
  private final SaveCustomerCart saveCustomerCart;

  public void addProductToCart(UUID customerId, UUID productItemId, int quantity) {
    var productItem = retrieveProductItem.retrieve(productItemId);
    Cart cart = retrieveCart(customerId);
    cart.addProduct(productItem, quantity);
    saveCustomerCart.saveCart(cart);
  }

  public Cart retrieveCart(UUID customerId) {
    return retrieveCustomer.retrieveById(customerId).getCart();
  }

}
