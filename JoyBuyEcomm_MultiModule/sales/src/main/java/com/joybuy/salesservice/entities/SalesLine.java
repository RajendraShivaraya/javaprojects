package com.joybuy.salesservice.entities;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Getter@Setter@AllArgsConstructor@NoArgsConstructor
@Table(name = "salesline")
public class SalesLine
{
    @Id
    @Column(name = "recid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long recId;

    @Column(name = "transid")
    public String transId;
    @Nonnull
    @Column(name = "linenum")
    public int    lineNum;
    @Column(name = "itemid")
    public String itemId;
    @Column(name = "qty")
    public int     qty;

    @Column(name = "salesprice")
    public float    salesPrice;
    @Column(name = "lineamount")
    public float    lineAmount;
    @Column(name = "linedisc")
    public float    lineDisc;
    @Column(name = "totalprice")
    public float    totalPrice;

    @Nonnull
    @Column(name = "inventdimid")
    public Long inventDimId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY, targetEntity = SalesTable.class)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="salesid", referencedColumnName = "salesid", nullable = false, foreignKey = @ForeignKey(name = "fk_salestable"))
    private SalesTable salesTable;

}
