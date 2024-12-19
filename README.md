# 🚪 희희 낙찰

신한DS SW 아카데미 1차 팀 프로젝트

![image](https://github.com/user-attachments/assets/e6705014-e1c7-4909-b6cf-652cea89e878)

#### 📙 발표자료 보러가기([Click](https://github.com/user-attachments/files/18176464/default.pdf))

<br/>

## 💡 프로젝트 소개

**중고 거래 및 실시간 경매 플랫폼**

- 불필요한 물건을 버리지 않고 다른 사람에게 필요한 자원으로 활용할 수 있도록 도울 수 있습니다. 이는 환경 보호와 자원 절약에도 기여하며, 이용자들이 편하게 거래를 진행할 수 있도록 해줍니다.
- 한정판 제품, 골동품 등 희소성 있는 물품에 대한 수요를 만족시킬 수 있는 효과적인 거래 방식입니다. 또한, 경매는 시장에서 물건의 가치를 실시간으로 평가받을 수 있는 공정한 방법으로, 판매자는 더 높은 가격으로 판매를, 구매자는 예산 내에서 거래를 시도할 기회를 얻을 수 있습니다.


<img src="https://github.com/user-attachments/assets/0d1c8ce4-16be-4ca7-a865-b3b3bcaa85ac" width="70%">

<img src="https://github.com/user-attachments/assets/58ae76a4-f071-4168-82c6-938ba3c4211e" width="70%">


<br/>

### ✔️ 서비스 흐름


<img src="https://github.com/user-attachments/assets/58b47802-8f63-4102-a0f8-77a0dd44e632" width="70%">

<img src="https://github.com/user-attachments/assets/49f9a914-18be-4a87-aabb-8a2ae515eb50" width="70%">

<img src="https://github.com/user-attachments/assets/d9a87a2f-6827-4c35-b499-3dc6b7f42733" width="70%">

<img src="https://github.com/user-attachments/assets/abaa9394-200b-4d26-bd05-18e457a38a05" width="70%">

<br/>

## 👪 팀원 소개 및 역할

**개발기간: 2024.05.28 ~ 2024.07.09**

### ✔️ 세부 역할 분담

- 공통: 웹 디자인 / DB 설계

- 손동희: 메인화면 / 판매 상품 상세페이지 / 판매 물품 등록 및 수정

- 신정인: 실시간 채팅 / 약속잡기 및 취소 / 채팅방 내 결제

- 신현상: 실시간 경매 / 로그인 및 회원가입 / 엘라스틱 서치 / 결제 라이브러리 연동 / 공통 JS 및 모듈 개발 / GIT 협업 관리

- 이두리: 마이페이지 / QNA 및 FAQ 게시판

- 최재명: 관리자 페이지

- 황수영: 실시간 알림 / 이용 정지된 유저 페이지

<br/>

## 📒 주요 내용

### ✔️ 물품 판매자와의 채팅 구현

중고 거래의 핵심인 에누리 과정을 위해 구매자는 판매자와 채팅을 시도할 수 있으며, 판매자는 해당 채팅방에서 즉시 가격을 수정할 수 있습니다.
<div style="display: flex;">
    <img src="https://github.com/user-attachments/assets/c7a55c92-86bd-457a-bd13-b2665ec9a139" alt="채팅1"/>
    <hr/>
    <img src="https://github.com/user-attachments/assets/30482f95-8a26-4af5-9096-c8d23a912b13" alt="채팅2"/>
    <hr/>
    <img src="https://github.com/user-attachments/assets/ddd6c82a-100b-4291-b647-99f55b545e29" alt="채팅3"/>
</div>

### ✔️ 마이페이지 구현

사용자가 본인이 판매중인 물품을 볼 수 있는 마이페이지를 구현하였습니다.
각 필터를 적용하고 순서 또한 정렬할 수 있습니다.
<div style="display: flex;">
    <img src="https://github.com/user-attachments/assets/942a0d80-b55c-446d-bbe7-fcbf24829759" alt="마이페이지1" />
    <hr/>
    본인이 등록한 물품의 정보 변경이 가능합니다.
    <img src="https://github.com/user-attachments/assets/35f859dd-2eff-472e-89f2-f5001af58893" alt="마이페이지2" />
</div>

### ✔️ 관리자 페이지 구현

사이트 운영자는 등록된 물품들, 문의 내역, 사용자들을 관리할 수 있습니다.
<div style="display: flex;">
    <img src="https://github.com/user-attachments/assets/a244ac61-eac7-4344-852c-b92d13187ad5" alt="관리자페이지1" />
</div>

### ✔️ 실시간 알림 구현

웹 사이트에서 실시간으로 알림을 보내줄 수 있습니다.
<div style="display: flex;">
    <img src="https://github.com/user-attachments/assets/e376db0b-1ee9-4650-92df-9cdd52868263" alt="실시간알림1" />
</div>

### ✔️ 실시간 경매 구현

누구나 실시간으로 진행되는 경매에 참여할 수 있습니다.
<div style="display: flex;">
    <img src="https://github.com/user-attachments/assets/85fd9872-e078-41c3-b899-f24f6b421211" alt="실시간경매1" />
    <hr/>
    <img src="https://github.com/user-attachments/assets/020b001c-9c74-4135-9ea5-63d8950f4606" alt="실시간경매2" />
</div>

### ✔️ 메인화면

조회수가 가장 높은 12개의 상품이 보여집니다.
<div style="display: flex;">
    <img src="https://github.com/user-attachments/assets/98e83310-6ee7-4639-8203-96ca18551b06" alt="메인화면1" />
</div>

### ✔️ 중고 제품 상세 페이지

중고 제품에 대한 정보를 확인할 수 있으며, 수정 또한 가능합니다.
<div style="display: flex;">
    <img src="https://github.com/user-attachments/assets/7918b5dd-ffda-443f-bd99-f7573c28dc1f" alt="상세페이지1" />
    <hr/>
    <img src="https://github.com/user-attachments/assets/785de1a4-783b-4669-8633-a5b8d742a70d" alt="상세페이지2" />
</div>

### ✔️ 물품 등록

중고 물품을 등록할 수 있습니다.
<div style="display: flex;">
    <img src="https://github.com/user-attachments/assets/d9a1dec3-2884-43a7-9e5a-1177fe7d4869" alt="상세페이지1" />
</div>

<br/>

## 🗃️ ER 다이어그램

![image](https://github.com/user-attachments/assets/55ebcd31-24f0-4632-885e-25571832008d)

<br/>

## 🛠 기술 스택

![image](https://github.com/user-attachments/assets/93e61490-53f0-45a7-89c4-c0ca69c061a5)
