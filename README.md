# :hospital: 병원 예약 관리 시스템 - Patient Application
### # 프로젝트 소개 -> ([Github: HRMS Introduction](https://github.com/sinb57/HRMS-Introduction))
## :book: 목차
* <a href="#head1">프로젝트 설계</a>
  * <a href="#head11">주요기능 요구사항</a>
  * <a href="#head12">UI 프로토타이핑</a>
</br>

***
## <p id="head1"></p>:pushpin: 프로젝트 설계
### <p id="head11"></p> :label: 주요기능 요구사항
#### 1. 로그인
- 어플리케이션 실행 시 로그인을 요구한다.
  (로그인이 인증된 사용자만이 서비스를 사용할 수 있다.)
- 계정이 없을 시 회원가입을 통해 계정 생성을 할 수 있다.

#### 2. 회원가입
- 회원가입 항목은 다음과 같다. (아이디, 비밀번호, 이름, 전화번호)
- 회원가입 시 비밀번호 입력은 두번 요구되며, 두 입력값이 일치해야 한다.

#### 3. 병원 목록
- 페이지당 4개의 병원을 보여준다.
- 표시되는 병원 정보는 다음과 같다.
(병원명, 병원주소, 진료과목, 영업상태)
- 영업상태는 현시각 기준 영업상황에 따라 다음 항목 중 하나로 표시된다.
(영업중 / 휴무 / 영업마감 / 식사중)
- 병원 조회 항목은 다음과 같다.
    - 상단에 주소를 지정하면 해당 지역의 병원 리스트를 보여준다. (주소 형식 : 도/시/구)
    - 체크박스를 이용하여 현시각 영엽중인 병원만 조회할 수 있다.
    - 병원명을 검색하여 병원을 조회할 수 있다.
    - 진료 과목을 선택하여 병원을 조회할 수 있다.

#### 4. 병원 상세 보기
- 다음과 같은 병원 정보가 표시된다.
(병원명, 연락처, 주소, 진료과목, 진료시간)
- 진료 기록이 있는 경우, 최근 진료 시각이 표시된다.
- 현재 예약 중이라면 예약 버튼이 비활성화되며, 아닐 시 예약 버튼이 활성화된다.

#### 5. 병원 예약
- 병원 예약 시각을 지정할 수 있다.
- 진료받고자 하는 과목을 선택할 수 있다.
- 진료과목에 따라 하단에 증상 리스트가 변경된다.
- 증상은 복수선택이 가능하다.
- 예약 버튼을 누르면 입력된 내용이 제출되며, 입력된 시각에 예약을 할 수 없다면  
  "해당 시간에 예약이 가득 찼습니다." 문구가 출력된다. (동일 시간 예약은 최대 3명까지 가능)

#### 6. 예약 목록
- 페이지당 4개의 예약 기록 정보를 보여준다.
- 표시되는 예약 기록 정보는 다음과 같다.
  (예약시각, 병원명, 병원주소, 예약상태)
- 예약상태는 현시각 기준 예약/진료 상황에 따라 다음 항목 중 하나로 표시된다.
  (예약중 / 예약취소 / 진료취소 / 진료완료)

#### 7. 예약 기록 상세 조회
- 다음과 같은 예약 기록 정보가 표시된다.
  (예약시각, 예약상태, 병원정보)
- 예약상태가 현재 예약중이면 "예약취소" 버튼이,
  예약중이 아니라면 "예약하기" 버튼이 활성화된다.

#### 8. 개인정보
- 회원가입 과정에서 입력한 개인정보를 확인할 수 있다.
  (아이디, 이름, 전화번호)
- 비밀번호의 경우, 새로운 비밀번호로 변경 가능하다.
- 로그아웃을 진행할 수 있다.
- 회원 탈퇴를 진행할 수 있다.
</br>

## <p id="head12"></p> :iphone: UI 프로토타이핑
https://ovenapp.io/view/6j5hShRxKGkMVjzI2Isv8J6jLlUuHE4W/
#### 1. 로그인 / 회원가입
![](https://imgur.com/nVU8Oqb.png)

#### 2. 병원 목록
![](https://imgur.com/2TxDrfb.png)

#### 3. 병원 상세 조회
![](https://imgur.com/QOFIdQF.png)

#### 4. 병원 예약
![](https://imgur.com/zRbyOJ0.png)

#### 5. 예약 목록 / 예약 기록 상세 조회
![](https://imgur.com/Ib6NsMM.png)

#### 6. 마이페이지
![](https://imgur.com/RtECc6Q.png)
</br>
</br>



</br>
</br>
