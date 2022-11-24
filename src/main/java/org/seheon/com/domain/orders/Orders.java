package org.seheon.com.domain.orders;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(length = 500, nullable = false)
    private String productName;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String orderDetail;

    private String buyer;

    @Builder
    public Orders(String productName, String orderDetail, String buyer){
        this.productName = productName;
        this.orderDetail = orderDetail;
        this.buyer = buyer;
    }
}
