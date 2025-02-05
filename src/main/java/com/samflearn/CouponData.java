//package com.samflearn;
//
//import com.samflearn.common.entity.coupon.Coupon;
//import com.samflearn.repository.coupon.CouponRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//public class CouponData implements CommandLineRunner{
//
//    private final CouponRepository couponRepository;
//
//
//    @Override
//    @Transactional
//    public void run(String... args) throws Exception {
//
//        Coupon coupon = new Coupon(100L);
//
//        couponRepository.save(coupon);
//
//    }
//}
//
