package com.samflearn.common.entity.coupon;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Getter
@AllArgsConstructor
@Table(name = "coupon")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long quantity;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;

//     낙관락 활용시 주석 해제 필요
//    @Version
//    private Long version;

    public Coupon () {

    }
    public Coupon (Long quantity) {
        this.quantity = quantity;
    }

    public void decrease(Long quantity) {
        if(this.quantity - quantity < 0) {
            throw new RuntimeException("쿠폰 전량 소진");
        }
        this.quantity -= quantity;
    }

}
