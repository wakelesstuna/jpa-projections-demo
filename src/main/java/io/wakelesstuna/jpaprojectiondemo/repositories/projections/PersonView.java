package io.wakelesstuna.jpaprojectiondemo.repositories.projections;

import org.springframework.beans.factory.annotation.Value;

public interface PersonView {

    @Value("#{target.firstName + ' ' + target.lastName}")
    String getFullName();
    AddressView getAddress();
}
