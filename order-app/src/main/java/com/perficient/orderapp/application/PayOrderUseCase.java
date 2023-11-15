package com.perficient.orderapp.application;

import com.perficient.orderapp.domain.*;
import com.perficient.orderapp.domain.excepton.EmptyCartException;
import com.perficient.orderapp.domain.port.PaymentApp;
import com.perficient.orderapp.domain.port.RetrieveCustomer;
import com.perficient.orderapp.domain.port.SaveCustomerCart;
import com.perficient.orderapp.domain.port.SaveOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PayOrderUseCase {

  private final PaymentApp paymentApp;
  private final RetrieveCustomer retrieveCustomer;
  private final SaveOrder saveOrder;
  private final SaveCustomerCart saveCustomerCart;

  public Order pay(UUID customerId) throws InterruptedException {
    var customer = retrieveCustomer(customerId);
    var currentCart = customer.getCart();
    validateCart(currentCart);
    Order order = new Order(customer, customer.getCart());
    PaymentDetails paymentDetails = paymentApp.executePayment(order);
    order.setPaymentDetails(paymentDetails);
    order.setOrderStatus(OrderStatus.PAID);
    saveOrder.save(order);
    currentCart.clean();
    saveCustomerCart.saveCart(currentCart);
    return order;
  }

  private Customer retrieveCustomer(UUID customerId) {
    return retrieveCustomer.retrieveById(customerId);
  }

  private void validateCart(Cart cart) {
    if (cart.getProducts() == null || cart.getProducts().isEmpty()) {
      throw new EmptyCartException();
    }
  }
}
