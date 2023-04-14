package com.joybuy.joybuyecomm_microservices.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductRatings
{
    public Integer averageRating;
    public Integer fiveStar;
    public Integer fourStar;
    public Integer threeStar;
    public Integer twoStar;
    public Integer oneStar;
}
