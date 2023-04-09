package com.joybuy.inventoryservice.repository;

import com.joybuy.inventoryservice.entities.InventDim;
import com.joybuy.inventoryservice.entities.SalesPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISalesPriceRepository extends JpaRepository<SalesPrice, Long>
{
    List<SalesPrice> findByInventDim(String inventDimId);
}
