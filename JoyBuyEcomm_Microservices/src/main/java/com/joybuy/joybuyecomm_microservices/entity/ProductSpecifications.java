package com.joybuy.joybuyecomm_microservices.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductSpecifications
{
    public String defaultSpec;
    public String technicalSpec;
    public String shippingSpec;
    public String handlingSpec;
    public String otherSpec;
}
