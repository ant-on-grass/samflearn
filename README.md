# samflearn
<br>
<br>

---

## 프로젝트 난이도 (중)
<br> 

### Cache을 이용한 성능개선 프로젝트
<br> 
<br>

---

<details><summary>프로젝트 목표 
</summary>
  <br>
  
  1. 현 데이터 베이스의 문제 <br> <br>
  2. cache 를 이용하여 조회 기능 개선 <br> <br>
  3. cache 의 적절한 활용 
</details>

<br>

---

**ERD**
<br>
<br>
![삼프런 (1)](https://github.com/user-attachments/assets/3a149e0d-1a8b-4a18-8dd6-dd8e2763e754)
<br>
<br>

---

**기술 스택**
<br>
<br>
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/redis-FF4438?style=for-the-badge&logo=redis&logoColor=white"> <img src="https://img.shields.io/badge/k6-7D64FF?style=for-the-badge&logo=k6&logoColor=white"> 
<br>
<br>

---
**기능 API**
<br>
<br>

user - CD
<br>

course - CRUD
<br>

like - CD

<br>

---

**성능 테스트 및 개선**
<br>
<br>

v1 : DSL 조회 // v2 : CACHE 조회

<br>
<br>

vus : 100 // duration 30 초
<details><summary> 더미 생성 
</summary>
  <br>
  
  ![더미 데이터 생성](https://github.com/user-attachments/assets/7ed28528-0929-4373-b547-0b65b43cd51a)

</details>

<br>

\*\*\***결과의 지표인 tps 를 보면서 성능 차이를 볼 예정**
<details><summary> tps 란 
</summary>
  <br>
  
TPS는 Transaction Per Second의 약자로, 1 초당 처리할 수 있는 트랜잭션을 의미한다

<br>
  
TPS는 시스템의 성능 측정의 지표가 된다
<br>

간단히 말하자면, '**1초 동안 요청을 얼마나 많이 받고 처리하는 가**' 를 의미하는 수치로 생각하면 된다.
<br>
</details>

<br>
<br>

**Post\_man course 데이터 10만 건**
<details><summary> v1 
</summary>
  <br>
  
  ![포스트맨_v1](https://github.com/user-attachments/assets/bea82f81-ad25-4fa7-a818-0cbcfc553ec7) <br>
  course 데이터에 특정 검색을 했을 시 34ms 가 나오는 것을 확인할 수 있다. 

</details>
<details><summary> v2 
</summary>
  <br>
  
 ![포스트맨_v2](https://github.com/user-attachments/assets/370fe068-5633-4c25-9270-07e5cc45c765) <br>
  course 데이터에 특정 검색을 했을 시 8ms 가 나오는 것을 확인할 수 있다. <br> <br>
  결과를 볼 때, 캐싱 조회가 단면적으로 db 에서 데이터를 응답받는 것보다 빠른 것을 알 수 있다.
<br>
</details>
<br>

**course 데이터 10만 건  (히카리 변경 x )**
<details><summary> v1 
</summary>
  <br>
  
  ![v1_10만 더미_히카리x](https://github.com/user-attachments/assets/dfa4b1d9-fc18-41ac-844f-d2a5ad6a802f) <br>
  30초 동안 10010 개의 요청을 받아 처리하였고 , <br>
  **tps 는 330** 임을 확인하였다.

</details>
<details><summary> v2 
</summary>
  <br>
  
  ![v2_10만 더미_히카리x](https://github.com/user-attachments/assets/e7ecaeb3-1565-4aef-a7a2-088a7567bb60) <br>
  30초 동안 약 300000 개의 요청을 받아 처리하였고 , <br>
  **tps 는 10190** 임을 확인할 수 있다. <br>
  포스트 맨에서 요청에 따른 응답 속도의 결과와 마찬가지로 <br> 
  응답 빠른 만큼 사용자에 요청 처리 수 또한 확연히 차이를 보인다.

</details>
<br>

다음 히카리를 변경해보면 어떤 차이가 있는 지 확인해보려한다. <br> <br>
다만, 히카리 설정은 한번만 실행하였고, 테스트에 영향을 줄 수 있는 쓰레드를 변경하지 않았음을 말하겠다. <br> <br>
**course 데이터 10만 건  (히카리 변경 o )**

<br>

<details><summary> 히카리 란? 
</summary>
  <br>
  데이터베이스 연결(Connection)을 관리해 주는 도구(라이브러리)이다. <br>
커넥션 풀(Connection Pool)이 설정된 커넥션의 사이즈만큼의 연결을 허용하며 <br> 
 
  **HTTP 요청에 대해 순차적으로 DB 커넥션을 처리해 주는 기능을 수행** 한다. <br> <br>

ps) Database Connection Pool( : DBCP) <br>
최초 풀에서 생긴 연결들로 요청을 처리하고, 이를 **재 사용**하는 것을 의미한다. <br> <br>
데이터베이스 커넥션 풀의 과정
1. WAS가 실행 되면서 Pool 내에 Connection들을 생성하고, 
2. HTTP의 요청이 올 때, Pool 내에서 Connection 객체를 가져다가 사용한다.
3. 사용이 완료된 Connection 객체는 Pool 내에 반환한다.
<br> <br>

데이터베이스 커넥션 풀의 장점 <br> <br>
1. WAS와 데이터베이스와의 연결을 정해놓은 만큼만 미리하여, 매번 생성하는 비용을 줄일 수 있다.
2. Connection에 대해 조정을 할 수 있다.
    -   **커넥션 풀을 크게 설정하면?** → 메모리 소모가 큰 대신 많은 사람의 대기시간이 줄어든다.
    -   **커넥션 풀을 작게 설정하면?** → 사용자의 대기시간이 길어진다.

<br> <br>

데드락 피하기 <br>
PoolSize = Tn × ( Cm - 1 ) + ( Tn / 2 ) <br> <br>
**Tn** : 전체 Thread 갯수<br>
**Cm** : 하나의 Task에서 동시에 필요한 Connection 수 <br>
<br>

ps ) 데드락 : 하나의 작업에서 필요한 커넥션의 양보다 HikariPool Size가 적으면 발생하게 되는 교착 상태이다.

</details>
<details><summary> 히카리 환경 설정
</summary>
  <br>
  
 ![히카리 설정](https://github.com/user-attachments/assets/4801463f-6036-4a7b-9c06-1764364b6b6a) <br>
<br>

**maximum-pool-size: 최대 pool size (defailt 10)** <br>
connection-timeout: 커넥션의 타임아웃 <br>
validation-timeout: 유효한 타임아웃 <br>
minimum-idle: 연결 풀에서 HikariCP가 유지 관리하는 최소 유휴 연결 수 <br>
idle-timeout: 연결을 위한 최대 유휴 시간 <br>
max-lifetime: 닫힌 후 pool 에있는 connection의 최대 수명 (ms)이다. <br>
auto-commit: auto commit 여부 (default true) <br>
<br>

해당 옵션의 설명을 참고하길 바랜다.

</details>

<details><summary> v1 
</summary>
  <br>
  
  ![더미10만 히카리 0 v1](https://github.com/user-attachments/assets/05f28107-c8e3-43ae-bf35-ecd1635b8794) <br>
 30초 동안 약 10000 개의 요청을 받아 처리하였고 , <br>
  **tps 는 344** 임을 확인할 수 있다. <br> <br>
</details>
<details><summary> v2 
</summary>
  <br>
  
  ![더미10만 히카리 0 v2](https://github.com/user-attachments/assets/1c0be763-f0d7-47e3-9ee4-66139c28ce40)  <br>
 30초 동안 약 240000 개의 요청을 받아 처리하였고 , <br>
  **tps 는 약 8100** 임을 확인할 수 있다. <br> <br>
  히카리를 변경했지만, 유의미하게 변화점을 찾을 수 없었고, 오히려 히카리 변경 전보다 tps 가 떨어짐을 확인했다.

</details>
<br>
<br>

---
**트러블 슈팅**
1. db 를 통해 응답에 의존하던 상황을 벗어나 캐싱을 씀으로 써, 성능을 개선할 수 있었다.
2. 그 외 히카리를 사용하여 더 나은 성능 개선을 시도했지만, 설정 변경 수가 적었고, 이에 대한 공부가 부족하여 더 나은 개선을 하지못했다.
    -  추가적으로 성능 개선을 위해, 쓰레드와 히카리 학습을 진행하겠다.
    -  후에 쓰레드와 히카리를 변경하여 성능 개선을 해 볼 생각이다.


<br>
<br>
<br>

---

<br>
<br>

---

| 이름 | 깃허브 |
| --- | --- |
| 민진홍 | [wls313](https://github.com/wls313) |
| 송진솔 | [jinsolsong (Jinsol)](https://github.com/jinsolsong) |
| 김태은 | [ant-on-grass](https://github.com/ant-on-grass) |

<br> <br>

**참고 링크**
[히카리 참고 링크](https://velog.io/@dongvelop/Spring-Boot-Hikari-CP-%EC%BB%A4%EC%8A%A4%ED%85%80%EC%9C%BC%EB%A1%9C-%EC%84%B1%EB%8A%A5-%EC%B5%9C%EC%A0%81%ED%99%94%ED%95%98%EA%B8%B0) <br>
[데드락 참고 링크](https://developerlee79.github.io/blog/hikari-deadlock)

