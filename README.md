# RECL
#### 전남대학교 공과대학 7호관 강의실 대여 시스템 

</br></br></br>

### 순서
[1. 개요](#개요) <br/>
[2. 사용된 기술](#사용된-기술) <br/>
[3. 요구사항 분석](#요구사항-분석) <br/>
[4. DB 설계](#db-설계) <br/>
[5. 흐름도](#흐름도) <br/>
[6. 실행결과](#실행결과) <br/>
[7. 회고](#회고)

</br>

***

</br>

## 개요
✔ 프로젝트 명 : RECL 
</br>
✔ 개발 인원 : 3명
</br>
✔ 프로젝트 기간 : 19년 09월 16일 ~ 12월 15일 (총 12주) 
</br>
✔ 개발 목적 : 학교 포털에서 제공하는 강의실별 시간표는 접근 경로가 복잡하고 <br/>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; 강의실 이용 예약 시 학과 사무실로 직접 가야하기 때문에 <br/>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; 시간표 조회 및 강의실 이용에 대한 접근성을 높이기 위한 캡스톤 프로젝트
</br>
✔ 주요 기능 <br/>
&emsp;&emsp;-사용자 : Firebase authentication 로그인, 회원가입, 회원탈퇴, 유효성 검사 및 중복 검사<br/>
&emsp;&emsp;-강의실 : 시간표 보기, 현재 빈 강의실 확인, 강의실 예약, 예약 취소, 예약 내역 확인
</br></br></br>


## 사용된 기술
✔ 개발 환경 : Window 10, Android Studio
</br>
✔ 개발 언어 : Java 
</br>
✔ 데이터베이스 : Firebase
</br></br></br>

## 요구사항 분석
✔ 로그인 
+ 첫 화면
+ 가입되지 않은 아이디로 로그인 시도할 때 "존재하지 않는 아이디입니다.
+ 아이디와 비밀번호 일치하지 않을 때 "ID와 비밀번호를 다시 확인해주세요"
+ 로그인 성공 시 "로그인 성공" -> 메뉴 화면으로 이동
</br>

✔ 회원가입
+ 비어있거나 형식에 맞지 않을 때 "다시 입력해주세요"
+ 이미 존재하는 이메일일 때 "이미 존재하는 이메일입니다."
+ 회원가입 성공 시 "회원가입 성공" -> 로그인 화면으로 이동
</br>

✔ 빈 강의실 보기
+ 현재 날짜, 요일, 시간 확인 후 수업 중이 아닌 빈 강의실 출력
+ 시간 새로고침 기능
</br>

✔ 강의실 대여 신청
+ 날짜 선택 -> 강의실 선택 -> 비어있는 시간에 강의실 대여 할 수 있음
+ 날짜 선택 후 자신이 선택한 날짜 보여주기
+ 신청 내역 (사용자 이름, 이메일, 신청 날짜, 강의실 번호, 신청 시간) 출력 - DB에 신청내역 추가
+ 홈 버튼과 마이페이지 버튼으로 이동 가능
</br>

✔ 강의실 대여 신청 확인 및 취소
+ 마이페이지로 이동 -> 신청 내역 확인 가능
+ 신청 내역에서 신청취소 버튼을 누르면 팝업으로 한번 더 알림
+ 신청취소 내역 (신청 날짜, 강의실, 신청시간) 출력 - DB에서 신청내역 삭제
</br></br></br>




## DB 설계
<img width="80%" src="https://github-production-user-asset-6210df.s3.amazonaws.com/126046238/281644476-9f405095-0c92-45bd-8628-36eb4e858d91.png"/>

</br></br></br>
## 흐름도
<img width="80%" src="https://github.com/recordhyo/RECL/assets/126046238/402b18da-9eb7-4e25-8de3-799fc7b63cae"/>
</br></br></br>



## 실행결과
### ✔ 실행화면
**로그인 및 회원가입**
<p>
  <img width="250px" src="https://github.com/recordhyo/RECL/assets/126046238/a56075ac-7a9c-4534-8634-fc5ff472fafd"/>&emsp;
  <img width="250px" src="https://github.com/recordhyo/RECL/assets/126046238/666e02ad-caa9-4af5-a6a1-a8076a3f7270"/>
</p>
</br>

**메뉴** 
<p>
 <img width="250px" src="https://github.com/recordhyo/RECL/assets/126046238/3949d6d1-a4e5-4afe-beb0-aa05c5ea826e"/></p>
</br>

**강의실 별 시간표**
</br>
메뉴에서 ```강의실 별 시간표``` 버튼 클릭 -> 강의실 번호 버튼 클릭
<p>
  <img width="250px" src="https://github.com/recordhyo/RECL/assets/126046238/5486cee6-2f7e-453c-ba33-e6ab68cd9ce8"/>
 <img width="250px" src="https://github.com/recordhyo/RECL/assets/126046238/9e57123c-7ff7-4780-85a5-f5f6d0e1c0ad"/></p>
</br>

**빈 강의실 보기**
</br>
메뉴에서 ```빈 강의실 보기``` 버튼 클릭 -> 현재 날짜 시간에 비어있는 강의실 출력 
</br>

**강의실 대여 신청**
</br>
메뉴에서 ```강의실 대여 신청``` 버튼 클릭 -> 날짜, 강의실 번호, 대여 가능 강의실 선택 후 대여 신청 -> 신청확인
<p>
  <img width="200px" src="https://github.com/recordhyo/RECL/assets/126046238/91ca5a5c-d4b3-4dba-b0c6-7e9e22b42ecc"/>
  <img width="200px" src="https://github.com/recordhyo/RECL/assets/126046238/0f340211-b12d-41da-ae5a-f4db6d02f95f"/>
  <img width="200px" src="https://github.com/recordhyo/RECL/assets/126046238/ce70bae4-17e0-4d6e-a756-63e463e2d35b"/>
  <img width="200px" src="https://github.com/recordhyo/RECL/assets/126046238/25a6ed13-1061-438e-b915-b2b90645f3c4"/>
</p>
</br>

**마이페이지 및 대여 신청취소**
</br>
마이페이지 ```신청내역``` -> 신청 내역 확인 가능, ```신청취소``` 버튼 -> 신청취소 확인 팝업 -> 신청취소
<p>
  <img width="200px" src="https://github.com/recordhyo/RECL/assets/126046238/1f830b8a-2c8f-4be3-9976-69c8f8a4fb8e"/>
  <img width="200px" src="https://github.com/recordhyo/RECL/assets/126046238/d81c00e3-9046-4b4f-967e-51722171a674"/>
  <img width="200px" src="https://github.com/recordhyo/RECL/assets/126046238/8ba2192c-96c9-4614-bbd2-f0473c558dd2"/>

</p>
</br>

### ✔ 실행영상 
</br>

**강의실 예약 신청 및 신청 취소**
<img width="80%" src="https://github.com/recordhyo/RECL/assets/126046238/055e7c90-f2a8-49ad-8b21-000a34cf201c"/>

</br></br></br>

## 회고
+ 웹과 다르게 프론트엔드(UI)를 함께 개발해서 어려운 점이 있었다.
+ Java 말고 코틀린이나 플러터를 배워서 개발해보고 싶다는 생각을 했다.




