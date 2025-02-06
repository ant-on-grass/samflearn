package com.samflearn.service.coupon;

import com.samflearn.common.entity.coupon.Coupon;
import com.samflearn.repository.coupon.CouponRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
class CouponServiceTest {

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private CouponService couponService;

    @Autowired
    private CouponRedissonLockService couponRedissonLockService;

    private Coupon coupon;

    @BeforeEach
    void setUpCoupon() {
        coupon = new Coupon(100L);
        couponRepository.save(coupon);
    }

    @Test
    @DisplayName("동시에 요청 100개_락 사용 안함_동시성 문제 발생")
    public void request_100_atTheSameTime () throws InterruptedException {
        //given
        //테스트에서 동시에 사용할 스레드 수 100개
        int threadCount = 100;
        //스레드 풀 생성(한번에 최대 20개의 스레드를 사용)
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        //여러 스레드가 완료 될 때까지 기다리는 역할(100개)
        CountDownLatch latch = new CountDownLatch(threadCount);

        //when
        for (int i = 0; i < 100; i++) {
            executorService.submit(()-> {
                try {
                    couponService.getCouponService(coupon.getId());
                } finally {
                    latch.countDown();
                }
            });

        }
        latch.await();
        executorService.shutdown();
        //then

        Coupon persistedCoupon = couponRepository.findById(coupon.getId())
                .orElseThrow(IllegalArgumentException::new); // 쿠폰 조회
        assertThat(persistedCoupon.getQuantity(), greaterThan(0L)); // 잔여 쿠폰 수량은 0보다 커야 한다
        System.out.println("잔여 쿠폰 수량: " + persistedCoupon.getQuantity());
    }

    @Test
    @DisplayName("동시에 요청 100개_비관적 락 사용")
    public void request_100_atTheSameTime_With_PessimisticLock () throws InterruptedException {
        //given
        //테스트에서 동시에 사용할 스레드 수 100개
        int threadCount = 100;
        //스레드 풀 생성(한번에 최대 20개의 스레드를 사용)
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        //여러 스레드가 완료 될 때까지 기다리는 역할(100개)
        CountDownLatch latch = new CountDownLatch(threadCount);

        //when
        for (int i = 0; i < 100; i++) {
            executorService.submit(()-> {
                try {
                    couponService.getCouponServiceWithPessimisticLock(coupon.getId());
                } finally {
                    latch.countDown();
                }
            });

        }
        latch.await();
        executorService.shutdown();
        //then

        Coupon persistedCoupon = couponRepository.findById(coupon.getId())
                .orElseThrow(IllegalArgumentException::new); // 쿠폰 조회
        assertThat(persistedCoupon.getQuantity(), equalTo(0L)); // 잔여 쿠폰 수량은 0이어야 한다
        System.out.println("잔여 쿠폰 수량: " + persistedCoupon.getQuantity());
    }

    @Test
    @DisplayName("동시에 요청 100개_낙관적 락 사용")
    public void request_100_atTheSameTime_With_OptimisticLock () throws InterruptedException {
        //given
        //테스트에서 동시에 사용할 스레드 수 100개
        int threadCount = 100;
        //스레드 풀 생성(한번에 최대 20개의 스레드를 사용)
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        //여러 스레드가 완료 될 때까지 기다리는 역할(100개)
        CountDownLatch latch = new CountDownLatch(threadCount);

        //when
        for (int i = 0; i < 100; i++) {
            executorService.submit(()-> {
                try {
                    couponService.callOptimisticLock(coupon.getId());
                } finally {
                    latch.countDown();
                }
            });

        }
        latch.await();
        executorService.shutdown();
        //then

        Coupon persistedCoupon = couponRepository.findById(coupon.getId())
                .orElseThrow(IllegalArgumentException::new); // 쿠폰 조회
        assertThat(persistedCoupon.getQuantity(), equalTo(0L)); // 잔여 쿠폰 수량은 0이어야 한다
        System.out.println("잔여 쿠폰 수량: " + persistedCoupon.getQuantity());
    }

    @Test
    @DisplayName("동시에 요청 100개_Redis_Redisson 사용")
    public void request_100_atTheSameTime_With_Redisson () throws InterruptedException {
        //given
        //테스트에서 동시에 사용할 스레드 수 100개
        int threadCount = 100;
        //스레드 풀 생성(한번에 최대 20개의 스레드를 사용)
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        //여러 스레드가 완료 될 때까지 기다리는 역할(100개)
        CountDownLatch latch = new CountDownLatch(threadCount);

        //when
        for (int i = 0; i < 100; i++) {
            executorService.submit(()-> {
                try {
                    couponRedissonLockService.getCouponWithRedisson(coupon.getId());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }finally {
                    latch.countDown();
                }
            });

        }
        latch.await();
        executorService.shutdown();
        //then

        Coupon persistedCoupon = couponRepository.findById(coupon.getId())
                .orElseThrow(IllegalArgumentException::new); // 쿠폰 조회
        assertThat(persistedCoupon.getQuantity(), equalTo(0L)); // 잔여 쿠폰 수량은 0이어야 한다
        System.out.println("잔여 쿠폰 수량: " + persistedCoupon.getQuantity());
    }

}