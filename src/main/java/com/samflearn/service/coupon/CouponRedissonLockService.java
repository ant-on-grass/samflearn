package com.samflearn.service.coupon;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@RequiredArgsConstructor
@EnableRetry
public class CouponRedissonLockService {

    private final RedissonClient redissonClient;
    private final CouponService couponService;



    @Retryable(
            backoff = @Backoff(
                delay = 1000,
                maxDelay = 3000,
                multiplier = 1.5
            )
    )
    public void getCouponWithRedisson(Long couponId) throws InterruptedException {
        RLock lock = redissonClient.getLock(couponId.toString());

        try {
            boolean available = lock.tryLock(10, 5, TimeUnit.SECONDS);

            if(!available) {
                log.info("lock 획득 실패");
            }
            //lock 획득 실패후 그냥 바로 서비스로 진입.
            couponService.getCouponService(couponId);

        }catch (InterruptedException e) {
            throw e;
        }finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

}
