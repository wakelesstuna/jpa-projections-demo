package io.wakelesstuna.jpaprojectiondemo.repositories.projections;

import org.springframework.beans.factory.annotation.Value;

public interface PersonView {

    /* SpEL expression */
    @Value("#{target.firstName + ' ' + target.lastName}")
    String getFullName();
    AddressView getAddress();
}
