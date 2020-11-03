PatientApplication 클래스는 GUI 클래스입니다.
PatientService에서 주요 기능을 구현한 후
PatientApplication에서 GUI를 연동할거예요.
Patient은 로그인 후 객체 생성하면 됩니다.
정보는 로그인 후, 병원리스트 보기 / 접수 목록 보기 등의 요청에 따라
서버로부터 데이터를 넘겨받아 출력할 겁니다.

데이터는 임의로 넣고나서 메소드 구현하시면 돼요.
나중에 데이터 형식을 정형화하겠습니다.

환자측 기능으로는
로그인, 로그아웃
개인정보보기, 개인정보수정
병원리스트 보기, 병원 검색, 병원 정보 보기
접수 하기, 접수 내역 보기, 대기번호 확인

PatientService 메소드 구현시
데이터 요청부분은 아래의 예시처럼 주석해놓고
나머지 작성하시면 됩니다.

public void searchHospital(String keyword) {
    // 병원리스트 요청 후 데이터 저장
    ArrayList<Hospital> hospitalList = new ArrayList<>();
    hospitalList.add(hospitalExample1);
    hospitalList.add(hospitalExample2);
    hospitalList.add(hospitalExample3);

    // 메소드 구현
    ...
    ...

}    