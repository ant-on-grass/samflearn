package com.samflearn.common.entity.coupon;

import com.samflearn.common.entity.course.Course;
import com.samflearn.common.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Entity
@Getter
@AllArgsConstructor
@Table(name = "coupon")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 낙관락 활용시 주석 해제 필요
    @Version
    private Long version;

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
