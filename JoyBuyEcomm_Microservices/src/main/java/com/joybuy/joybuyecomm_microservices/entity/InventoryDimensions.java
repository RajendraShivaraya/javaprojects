package com.joybuy.joybuyecomm_microservices.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InventoryDimensions
{
    public String color;
    public String size;
    public float  weight;
    public String dimension;
    public String serialNumber;
    public String batchNumber;
}
