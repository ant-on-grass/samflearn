package com.samflearn.service.coupon;

import com.samflearn.common.entity.coupon.Coupon;
import com.samflearn.common.exception.user.NotFoundException;
import com.samflearn.repository.coupon.CouponRepository;
import com.samflearn.service.redislock.RedisLockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CouponService {

    private final CouponRepository couponRepository;
    private final RedisLockService redisLockService;

    public void getCouponService(Long couponId) {

        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(()-> new NotFoundException("존재하지 않는 쿠폰입니다"));

        if (coupon == null || coupon.getQuantity() <= 0) {
            throw new RuntimeException("쿠폰이 모두 소진되었습니다.");
        }
        coupon.decrease(1L);

        couponRepository.save(coupon);

    }

    //비관적 락 테스트용
    @Transactional
    public void getCouponServiceWithPessimisticLock(Long couponId) {


        Coupon coupon = couponRepository.findIdWithPessimisticLock(couponId);
        if (coupon == null || coupon.getQuantity() <= 0) {
            throw new RuntimeException("쿠폰이 모두 소진되었습니다.");
        }
        coupon.decrease(1L);

        couponRepository.save(coupon);

    }

    //낙관적 락 테스트용
    @Transactional
    public boolean getCouponServiceWithOptimisticLock(Long couponId) {

        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(()-> new NotFoundException("없음"));
        if (coupon == null || coupon.getQuantity() <= 0) {
            throw new RuntimeException("쿠폰이 모두 소진되었습니다.");
        }
        coupon.decrease(1L);

        couponRepository.save(coupon);

        return true;
    }

    public int callOptimisticLock(Long couponId) {
        boolean success = false;

        int failureCount = 0;

        while (!success) {
            try {
                success = getCouponServiceWithOptimisticLock(couponId);
            } catch (RuntimeException e) {
                failureCount++;
            }
        }
        return failureCount;
    }

    public void getCouponServiceWithRedis(Long couponId) {

        if (redisLockService.tryLock() == true) {
            log.info("락 키를 받았다");
            Coupon coupon = couponRepository.findById(couponId)
                    .orElseThrow(()-> new NotFoundException("존재하지 않는 쿠폰입니다"));

            if (coupon == null || coupon.getQuantity() <= 0) {
                throw new RuntimeException("쿠폰이 모두 소진되었습니다.");
            }
            coupon.decrease(1L);

            couponRepository.save(coupon);

            redisLockService.unlock();
            log.info("락 키를 반납했다");
            return;
        }
        log.info("락 키를 받지 못해서 종료되었다");
    }

}
