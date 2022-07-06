package io.wakelesstuna.jpaprojectiondemo.repositories;

import io.wakelesstuna.jpaprojectiondemo.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, String> {
}
