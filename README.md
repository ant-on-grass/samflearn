# 👑Samflearn👑
### 📗열심히 공부하는 우리를 위하여
프로젝트 기간 : 2025/01/31 ~ 2025/02/06
<br>
<br>
프로젝트 설명 : 전문 지식 분야를 무료 혹은 경제적으로 학습할 수 있는 인프런을 모티브로 한 **삼프런** 프로젝트
<br>
프로젝트 목표 : 
<br>
1. 대량의 강의 데이터에서 검색으로 원하는 강의를 빠른 시간 내에 찾을 수 있게 하기
2. 대량의 강의 데이터에서 **인기순**으로 검색하기
3. 대량의 트래픽이 발생할 것으로 예상되는 강의 100% 할인 쿠폰(가정)으로 동시성 문제파악 및 해결
<br>

TMI ) 왜 삼프런 이지? >>>>>...3명이라서...😂 
<br>
<p align="center">
<img src="https://github.com/user-attachments/assets/75d80fa9-4b97-4fd5-a07c-65f8c544d4e6" height=230px>
<img src="https://github.com/user-attachments/assets/a112c7af-a158-4d9d-a1e8-c22c3d94c650" height=230px>
</p>

<br>

---

## 💬 ERD

<details>
<summary> ERD 보기 </summary>
  
<br>
<br>
<p align="center">
<img src="https://github.com/user-attachments/assets/8d4a91bf-5605-4f8c-8c93-3f333dc61b9f" height=350px>
</p>
<br>
<br>
</details>


---
## 📃 와이어 프레임

<details>
<summary> 와이어프레임 보기 </summary>
  
<br>
<br>
<p align="center">
<img src="https://github.com/user-attachments/assets/b7151e81-3278-44d3-86e1-5c1785e42619" height=600px>

</p>
<br>
<br>
</details>


---

## 🛠️ 기술 스택


<img src="https://img.shields.io/badge/Java-007396?style=flat-square&logo=OpenJDK&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat-square&logo=springboot&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/Gradle-02303A?style=flat-square&logo=gradle&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/Redis-DC382D?style=flat-square&logo=redis&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/k6-7D64FF?style=flat-square&logo=k6&logoColor=white">&nbsp;
<br>
<img src="https://img.shields.io/badge/IntelliJ IDEA-000000?style=flat-square&logo=IntelliJ IDEA&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/Github-181717?style=flat-square&logo=github&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/git-F05032?style=flat-square&logo=git&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/Slack-4A154B?style=flat-square&logo=Slack&logoColor=white">

---
## 👍프로젝트 최종 정리👍
### 🖥 **API**
- 사용자 C, D
- 강의 C, R(인기순 조회), U, D
- 좋아요 C, D
- 쿠폰 C

### 💣대용량 트래픽 테스트💣
- 쿠폰 100개 한정 1,000개의 트래픽에 대한 동시성 문제 해결
  - 비관적 락, 낙관적 락, Redisson 테스트

### 🔎검색 속도🔎 
- 강의 데이터 10만 건 누적 후
  - 페이지 네이션 강의 검색 속도 : ** 0.08초 **
 
### 📚인기강의 검색📚
- 강의 데이터 10만 건 누적 후
  - 인기가 많은 TOP 10위 까지의 데이터 검색

<br>

## 👏성능 개선👏
<details>
  <summary> 1. ✨레디스 캐싱 </summary>
  
> 데이터에 보다 빠른 접근을 하여 검색 속도를 향상시키기 위함.
> 
> 검색 기능에 Redis 캐싱 적용
>
> `V1` : 캐싱 전 , `V2` : 캐싱 후

      
|캐싱 적용 전|캐싱 적용 후|
|---|---|
|![image](https://github.com/user-attachments/assets/98767e11-8938-4194-b3e3-9099d1e70d1a)|![image](https://github.com/user-attachments/assets/5b3ab6de-7fb6-4cc8-b575-25ebc3babafa)|

> 개선 결과 : `cour`라는 단어가 들어간 강의 검색 결과
> 
> `기존` : 34ms
> 
> `변경` : 8ms
> 
> 약 `76%`감소
  
</details>

<details>
<summary> 2. ✨동시성 제어 </summary>
  
> 문제 발생 이유 : 여러개의 스레드가 동시에 동일한 데이터에 접근하여 수정을 시도했기 때문
> 
> -> 현재 프로젝트 상황에서는 동시에 여러 고객이 수량이 한정된 쿠폰을 얻으려고 했기때문
>
테스트 조건 : 1,000명이 동시에 쿠폰 수령 요청, 쿠폰 수량 : 100개
> ![image](https://github.com/user-attachments/assets/9942de84-1d60-489a-8f48-e3c79f562656)

기대값은 쿠폰의 수량이 0이 되는 것이었으나, 실제로는 83개의 쿠폰이 남음.

> 해결 할 수 있는 방법 : 비관적 락, 낙관적 락, Redisson 등
> 
> 각 각의 방법으로 테스트를 해보고 상황에 맞는 방법을 채택하기로 함.
>
## 1. 비관적 락
   > 충돌은 발생할 수 밖에 없을거야...😥라고 비관적인 관점을 가진 락
   >
   > 다른 트랜잭션이 데이터에 접근하지 못하게 방어적으로 처리하는 방식
   >
✅1차 시도
1,000명이 동시 구매, 쿠폰수량 : 100개, 7s 370ms

![image](https://github.com/user-attachments/assets/f9284455-578a-42d4-b66c-7713924a6eca)

✅2차 시도
5,000명이 동시 구매, 쿠폰수량 : 100개, 10s 989ms

![image](https://github.com/user-attachments/assets/02c2df19-7282-469c-9487-a3a716d01faa)

**동시 주문 요청에 대해 데이터 정확성 확보**
<br>

## 2. 낙관적 락
   > 충돌은 발생하지 않을거야😚 라고 낙관적인 관점을 가진 락
   >
   > 충돌은 발생하지 않을 것이라는 관점이기때문에 실제로 락을 사용하지 않음.
   >
   > 충돌이 발생한 후에 대한 후속처리를 수동적으로 해줘야 함.
   > 
✅1차 시도
1,000명이 동시 구매, 쿠폰수량 : 100개, 18s 333ms

![image](https://github.com/user-attachments/assets/58d63c06-bb6f-4556-94f0-a9dc09286f16)


**동시 주문 요청에 대한 데이터 정확성은 확보되나,**
<br>
**충돌이 일어나지 않을 것이라고 생각 하고 있다가 충돌이 발견되면 충돌을 해결하는 방식으로**
<br>
**비관적 락보다 시간이 오래걸리는 것을 알 수 있음**

`**비관적 락 채택**`

`하지만 무조건적으로 비관적 락이 옳은 방법이다 라고 말 할 수 없음`
<br>
`비즈니스 요구사항에 적절한 방법을 선택하는 것이 중요`


  
</details>

## 😲부하 테스트😲
<details> 
  <summary> 1. ✨상세 내용 </summary>
  
> Redis Chche에 대한 부하테스트
>
> `V1` : 캐싱 전 , `V2` : 캐싱 후
>
> 테스트 케이스
>
> - 인원 : 100명(가정)
> - 시간 : 30s
> - 테스트 목적 : 동일한 인원이 동일한 시간동안 보내는 트래픽에 대해 얼마나 처리하는가(데이터 조회)
>
|캐싱 적용 전|캐싱 적용 후|
|---|---|
|![image](https://github.com/user-attachments/assets/162f8443-cf2e-400d-bebb-1b3d7c85ce3e)|![image](https://github.com/user-attachments/assets/3a06207b-5af5-42e4-855d-c65e162d211c)|
|<p align="center"><img src="https://github.com/user-attachments/assets/186daf96-550b-460b-8449-895f6a4d8885" height=300px></p>|<p align="center"><img src="https://github.com/user-attachments/assets/d4faa7df-2636-431b-baec-1ab042cd067d" height=300px>|

> 이로써 DB가 아닌 In-Memory-Cache를 사용하면 외부에 위치한 DB에서 데이터를 가지고 오는 속도보다 훨씬 빠른 것을 알 수 있다.
</details>








## 💡느낀 점💡


## 😊추가로 도전해 보고싶은 것😊



| 이름 | 깃허브 |
| --- | --- |
| 민진홍 | [wls313](https://github.com/wls313) |
| 송진솔 | [jinsolsong (Jinsol)](https://github.com/jinsolsong) |
| 김태은 | [ant-on-grass](https://github.com/ant-on-grass) |

<br> <br>

**참고 링크**
[히카리 참고 링크](https://velog.io/@dongvelop/Spring-Boot-Hikari-CP-%EC%BB%A4%EC%8A%A4%ED%85%80%EC%9C%BC%EB%A1%9C-%EC%84%B1%EB%8A%A5-%EC%B5%9C%EC%A0%81%ED%99%94%ED%95%98%EA%B8%B0) <br>
[데드락 참고 링크](https://developerlee79.github.io/blog/hikari-deadlock)

