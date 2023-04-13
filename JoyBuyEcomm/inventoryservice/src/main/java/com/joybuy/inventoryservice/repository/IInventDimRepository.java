package com.joybuy.inventoryservice.repository;


import com.joybuy.inventoryservice.entities.InventDim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IInventDimRepository extends JpaRepository<InventDim, Long>
{
    List<InventDim> findByProductIdAndInventColorIdAndInventSizeId(String ProductId, String inventColorId, String inventSizeId);
}
