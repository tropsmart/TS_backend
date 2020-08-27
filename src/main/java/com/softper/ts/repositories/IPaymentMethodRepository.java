package com.softper.ts.repositories;

import com.softper.ts.models.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {

}
