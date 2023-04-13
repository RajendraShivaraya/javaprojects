package com.joybuy.salesservice.repository;

import com.joybuy.salesservice.entities.SalesLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISalesLineRepository extends JpaRepository<SalesLine, Long>
{
}
