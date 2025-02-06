package com.samflearn.service.redislock;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class RedisLockService {

    private static final String LOCK_KEY = "coupon:lock";
    private static final long LOCK_EXPIRE_TIME = 5; // 락 만료 시간 (초)
    private static final long TIMEOUT = 500; // 락을 기다릴 최대 시간 (ms)
    private static final long INITIAL_BACKOFF = 10; // 초기 백오프 시간 (ms)

    @Autowired
    public RedisTemplate<String, String> redisTemplate;

    private String lockValue;

    @Transactional
    public boolean tryLock() {
        long startTime = System.currentTimeMillis();
        long backoff = INITIAL_BACKOFF;
        this.lockValue = UUID.randomUUID().toString(); // 고유한 락 값 생성

        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();

        while (System.currentTimeMillis() - startTime < TIMEOUT) {
            // setIfAbsent는 "SETNX"와 같은 역할을 합니다.
            Boolean success = valueOps.setIfAbsent(LOCK_KEY, lockValue, LOCK_EXPIRE_TIME, TimeUnit.SECONDS);
            if (Boolean.TRUE.equals(success)) {
                // 락 획득 성공
                return true;
            }

            try {
                // 백오프 적용 (점진적으로 대기 시간 증가)
                TimeUnit.MILLISECONDS.sleep(backoff);
                backoff = Math.min(backoff * 2, 200); // 최대 1초까지 증가
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        return false; // TIMEOUT 초과 시 락 획득 실패
    }
    @Transactional
    public void unlock() {
        if (lockValue == null) return;

        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        String currentValue = valueOps.get(LOCK_KEY);

        if (lockValue.equals(currentValue)) {
            redisTemplate.delete(LOCK_KEY); // 락 해제
        }
    }

}
