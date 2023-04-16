package products_mongodb.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProductRatings
{
    public double averageRating;
    public Integer fiveStar;
    public Integer fourStar;
    public Integer threeStar;
    public Integer twoStar;
    public Integer oneStar;
}
