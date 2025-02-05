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
  1. 현 데이터 베이스의 문제
  2. cache 를 이용하여 조회 기능 개선
  3. cache 의 적절한 활용
</details>
<br>
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


<br>

**course 데이터 1,000 건 (히카리 변경 x )**
<details><summary> v1 
</summary>
  
</details>
<details><summary> v2 
</summary>
  
</details>

<br>

**course 데이터 1,000 건 (히카리 변경 o )**
<details><summary> v1 
</summary>
  
</details>
<details><summary> v2 
</summary>
  
</details>

<br>

**course 데이터 10만 건  (히카리 변경 x )**
<details><summary> v1 
</summary>
  
</details>
<details><summary> v2 
</summary>
  
</details>
<br>

**course 데이터 10만 건  (히카리 변경 o )**
<details><summary> v1 
</summary>
  
</details>
<details><summary> v2 
</summary>
  
</details>
<br>

**course 데이터 10만 건  (쓰레드 변경 x )**
<details><summary> v1 
</summary>
  
</details>
<details><summary> v2 
</summary>
  
</details>
<br>

**course 데이터 10만 건  (쓰레드 변경 o )**
<details><summary> v1 
</summary>
  
</details>
<details><summary> v2 
</summary>
  
</details>
<br>
<br>

---
<details><summary> 트러블 슈팅
</summary>

</details>
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


