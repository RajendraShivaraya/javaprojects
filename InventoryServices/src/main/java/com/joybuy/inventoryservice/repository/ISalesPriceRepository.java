package com.joybuy.inventoryservice.repository;

import com.joybuy.inventoryservice.entities.SalesPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISalesPriceRepository extends JpaRepository<SalesPrice, String>
{
}
