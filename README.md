# tripleCMS
> 트리플 클럽 마일리지 서비스의 서버 애플리케이션 구현 프로젝트 입니다.

<br/>

## 1. 서비스 개요
트리플 사용자들이 장소에 리뷰를 작성할 때 포인트를 부여하고, 전체/개인에 대한 포인트 부여 히스토리와 개인별 누적 포인트를 관리하고자 합니다.

<br/>   

## 2. 서비스 주요기능
- 리뷰 작성 시 포인트 적립 및 회수
    1. 포인트 지급
        - 글만 있는 리뷰 작성하면 <b>1점</b> 적립
            - 1자 이상 텍스트 작성 : 1점
        - 글과 사진이 있는 리뷰 작성 <b>2점</b> 적립 
            - 1자 이상 텍스트 + 1장 이상 사진 : 2점
        - 글만 있는 리뷰를 수정하여 사진을 추가할 시, 1점 추가 적립
        - 리뷰가 없는 여행장소에 최초 리뷰 작성 시 <b>보너스 점수 1점</b> 추가 적립
            - 여행장소에 사용자 A가 최초리뷰 작성 후 삭제하고, 삭제된 이후 사용자 B가 리뷰를 작성하면 B에게 보너스 점수 부여
            - [trouble case] 사용자 A가 최초리뷰를 삭제하는데 <b>삭제되기 이전에 사용자 B가 리뷰를 작성하면</b> 최초리뷰로 인정되지 않고 보너스 점수가 부여 안됨
    2. 포인트 회수
        - 작성한 리뷰를 삭제하면, 해당 리뷰에 지급된 <b>모든 포인트</b> 회수
        - 글과 사진이 있는 리뷰에서 <b>사진을 모두 삭제</b>하면 <b>1점</b> 회수
    
<br/>

- 포인트 증감 이력 확인
    - 사용자는 현재 시점의 본인의 포인트 이력 및 총점을 조회할 수 있음

<br/>

- 포인트 별 레벨 구현
    - 레벨 승급 포인트 달성 시, 등급 업그레이드 및 포인트 회수로 인한 다운그레이드 기능 구현
    - 0 LEVEL : 5 포인트 미만
    - 1 LEVEL : 5 포인트
    - 2 LEVEL : 40 포인트
    - 3 LEVEL : 100 포인트

<br/>

## 3. 개발 요구사항
- SQL( MySQL >= 5.7) 스키마 설계
    - 테이블과 인덱스에 대한 DDL 필요
- REST API를 제공하는 서버 애플리케이션 구현

<br/>

## 4. 기술 스택
- Spring Boot (API Server)
- Spring Security (Security)
- MySQL(8.0) (RDB)
- JPA (ORM)
- AWS EC2 (Infra)
- AWS S3 (Store)
- Postman(Documentation)
- DBeaver (Database tool)
- IntelliJ (Development tools)

<br/>

## 5. 가용 서버
- 13.125.166.209:8080
    - 위 서버로 테스트 진행해주시면 됩니다..!

<br/>

## 기능 및 사용방법

<br/>
🔍 회원가입 및 로그인

> * 13.125.166.209:8080/api/sign/** <- 토큰 없이도 사용가능합니다.

```bash 
Post
* 회원가입 : 13.125.166.209:8080/api/sign/register
* 로그인 : 13.125.166.209:8080/api/sign/login
* 엑세스 토큰 유효시간 : 30분
* 재발행 토큰 유효기간 : 7일
{
    "userName" : "testda",
    "userPw" : "qwer1234"
}
```
<p align="center">
<img src = "./img/join.png" width=45%>
<img src = "./img/join.png" width=45%>
</p>

> * JWT 토큰을 통한 회원가입과 로그인을 구현하였습니다.
> * Response 값에 나와있는 access 토큰을 url에서 사용해주시면됩니다.

<br/>

🔍 토큰 재발행

```bash 
Post
* 토큰 재발행 : 13.125.166.209:8080/api/sign/register
{
    "accessToken" : "엑세스 토큰",
    "refreshToken" : "리프레시 토큰"
}
```

<p align="center">
<img src = "./img/refreshToken.png">
</p>


> * 토큰 재발행 시, 이전 로그인할때 나온 Response 값(accessToken, refreshToken)으로 사용하시면 됩니다.

<br/>

🔍 엑세스 토큰 인증방법

```bash 
* 각 기능의 Headers 에서 Key값과 Value값을 넣어주세요
* key : X-AUTH-TOKEN
* value : access 토큰
```

<p align="center">
<img src = "./img/authToken.png" >
</p>



<br/>

🔍 내 회원정보 조회

```bash 
Get
* 로그인 Response 값에서 userID 값을 주소에 붙여주세요 
* 13.125.166.209:8080/api/sign/user/{userID}
```

<p align="center">
<img src = "./img/userView.png" >
</p>

> * userId / userName / point(보유포인트) / userLevel (유저레벨) 확인 할 수 있습니다.

<br/>

🔍 장소 등록 및 조회

```bash 
Post 
* 장소등록
* 로그인 Response 값에서 userID 값을 주소에 붙여주세요 
* 13.125.166.209:8080/api/place/add

{
    "placeName" : "장소명"
}

-------------------------------

Get
* 장소 전체 조회
* 13.125.166.209:8080/api/place

```
<p align="center">
<img src = "./img/addPlace.png" width=45%>
<img src = "./img/getPlace.png" width=45%>
</p>








<br/>
