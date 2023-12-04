# PreScent
![썸네일 최종](https://github.com/PreScent-sc23/PreScent/assets/118275773/9dea5ed9-c31e-4053-8ee6-0a57b68c2c65)


## 프로젝트 개요
  많은 사람이 꽃 선물을 주고받습니다. 하지만 자체 설문 조사에 따르면, 꽃 구매 시에 매장에 방문하기 전에는 꽃가게 정보, 꽃말, 가격 등 꽃 구매에 필요한 정보들을 얻기 힘든 점이 불편했고, 주고받은 꽃 선물의 의미를 모르는 경우가 많았습니다.

  이를 해결하기 위해 꽃가게와 구매자의 상품 거래를 연결하는 플랫폼을 구축하고자 합니다. 플랫폼은 판매자가 등록한 정보들과 주문서를 통해 꽃 구매자들이 간편하게 꽃을 주문할 수 있게 하고, 사용자가 꽃 사진을 업로드하면 꽃 이름과 꽃말, 의미를 분석해주는 기능을 제공합니다. 
이를 통해 꽃가게에게는 주문 관리의 편의성을 제공하고, 구매자에게는 더 만족스러운 꽃 주문 경험을 제공합니다.
<br/><br/>

## 팀원 소개
#### 백엔드
|**이름**|**역할**|**email**|**github**|
|--|-------|-----|-----|
|서상원|yyy0304@ajou.ac.kr|https://github.com/SnagwonSeo|
|김수현|담당 구현 : Detection & Classification AI 모델 학습, 꽃 분석 기능 (PS:Len), GPT 기반 꽃 이미지 분위기 추출 기능, 판매자/고객 회원가입 기능, 판매자/고객 로그인 기능 <br/>  기타 담당 : Detection 데이터 수집, Swagger 설정, Jenkins 배포 자동화, 메인 로고 디자인|sooh@ajou.ac.kr|https://github.com/susooo|
|김도윤|rlaehdbs20@ajou.ac.kr|https://github.com/Ajou-sipdo|

<br/>

## 사용 라이브러리 및 개발 환경
<img src="https://img.shields.io/badge/IntelliJ IDEA-000000?style=for-the-badge&logo=intellijidea&logoColor=white"/>
<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=Spring Boot&logoColor=white"/>
<img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white"/>
<img src="https://img.shields.io/badge/Amazon EC2-FF9900?style=for-the-badge&logo=Amazon EC2&logoColor=white"/>
<img src="https://img.shields.io/badge/Amazon S3-569A31?style=for-the-badge&logo=Amazon S3&logoColor=white"/>
<img src="https://img.shields.io/badge/NAVER Cloud-03C75A?style=for-the-badge&logo=NAVER Cloud&logoColor=white"/>
<img src="https://img.shields.io/badge/Jenkins-D24939?style=for-the-badge&logo=Jenkins&logoColor=white"/>
<img src="https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger&logoColor=white"/>

<br/>

## 협업 툴
<img src="https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=Notion&logoColor=white">
<img src="https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=Slack&logoColor=white">
<img src="https://img.shields.io/badge/Discord-5865F2?style=for-the-badge&logo=Discord&logoColor=white">

<br/>

## 패키지 구조
#### 기능별로 Controller, Service, Repository, Entity, Dto를 생성하여 사용
|**패키지**|**역할**|
|--|-----|
|controller|MVC의 컨트롤러 역할|
|service|비즈니스 로직 구현|
|repository|데이터베이스 접근|
|entity|데이터베이스에 저장할 객체|
|dto|정보 전달 객체|

- User : 회원 관리 기능
- FlowerShop : 매장 관리 기능
- FinishedProduct : 완제품 관리 기능
- Search : 검색 관련 기능
- Cart : 장바구니 관련 기능
- FPORder : 주문 관련 기능
- AIAnalysis : AI분석 관련 기능
<br/> <br/>
## 서비스 요약

![1_11zon](https://github.com/PreScent-sc23/backend/assets/92291198/729fbf60-b01c-4f39-b2c4-78bf4bfff739)
