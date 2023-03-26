package com.joybuy.salesservice.repository;

import com.joybuy.salesservice.entities.InventDim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IInventDimRepository extends JpaRepository<InventDim, Long>
{

}

