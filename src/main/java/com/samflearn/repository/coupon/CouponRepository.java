package com.samflearn.repository.coupon;

import com.samflearn.common.entity.coupon.Coupon;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CouponRepository extends JpaRepository<Coupon, Long> {

    //비관적 락
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM Coupon c WHERE c.id = :id")
    Coupon findIdWithPessimisticLock(@Param("id")Long couponId);

}