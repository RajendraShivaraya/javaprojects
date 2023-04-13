package com.joybuy.salesservice.repository;

import com.joybuy.salesservice.entities.SalesTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISalesTableRepository extends JpaRepository<SalesTable, String>
{
}
