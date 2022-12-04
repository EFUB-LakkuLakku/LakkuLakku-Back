# 웹 기반 공유 다이어리 서비스, 라꾸라꾸

[![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2FEFUB-LakkuLakku%2FLakkuLakku-Back&count_bg=%23FFC400&title_bg=%23555555&icon=&icon_color=%23E7E7E7&title=hits&edge_flat=false)](https://hits.seeyoufarm.com)

## 🌕 백엔드 팀원 소개

<table border="1" cellspacing="0" cellpadding="0" width="100%">
    <tr width="100%">
        <td width="25%" align="center"><a href= "https://github.com/nitronium102">강민지</a></td>
        <td width="25%" align="center"><a href= "https://github.com/julia-ing">최예원</a></td>
        <td width="25%" align="center"><a href= "https://github.com/june0216">권지윤</a></td>
        <td width="25%" align="center"><a href= "https://github.com/SeojungH">황서정</a></td>
    </tr>
    <tr width="100%">
        <td align="center"><img src = "https://github.com/nitronium102.png"></td>
        <td align="center"><img src = "https://github.com/julia-ing.png"/></td>
        <td align="center"><img src = "https://github.com/june0216.png"/></td>
        <td align="center"><img src = "https://github.com/SeojungH.png"/></td>
    </tr>
    <tr width="100%">
        <td width="25%" align="center">
        [유저] 회원 탈퇴
[다이어리] 다이어리 생성, 수정, 삭제, 편집 기능<br>
[스티커] 스티커 조회 기능<br>
[템플릿] 템플릿 조회 기능<br>
[시큐리티] JWT 및 Redis를 통한 토큰 구현<br>
[알림] SSE 기반 실시간 알림 리팩토링<br>
[기타] 프로젝트 기본 구조 및 데이터베이스 설계
        </td>
        <td width="25%" align="center">
[홈] 홈 조회<br>
[프로필] 유저 프로필 수정<br>
[이미지] S3 이미지 관련 기능 구현<br>
[배포] EC2, Docker, Github Action 을 이용한 CI/CD 구축<br>
[배포] route53, nginx 를 이용한 https 통신 구현<br>
[기타] 데이터베이스 설계, 아키텍처 설계, AWS(EC2, RDS, S3 등) 세팅</td>
        <td width="25%" align="center">
[유저] 회원 가입, 이메일과 닉네임 중복 확인 기능, 임시 비밀번호 이메일 발송 기능<br>
[시큐리티] JWT 토큰 구현, Redis를 통한 Blacklist 구현, 비밀번호 변경 기능<br>
[친구] 친구 추가, 삭제, 조회, 찾기 기능<br>
[알림] 좋아요, 댓글, 대댓글, 친구 추가 알림 기능<br>
[기타] 데이터베이스 설계</td>
        <td width="25%" align="center">
[유저] 유저 정보 수정, 비밀번호 변경 기능<br>
[다이어리] 댓글 작성 및 삭제, 좋아요 추가 및 취소<br>
[기타] 데이터베이스 설계<br>
</td>
   </tr>
</table>


## 🌕 개요
'라꾸라꾸'는 웹 기반 공유 다이어리 서비스로, 쉽고 간단하게 다이어리를 작성하여 나만의 라이프스타일을 꾸미고 이를 공유하고자 하는 유저들을 위해 제작되었습니다.
웹 기반 서비스이기 때문에 간편하게 휴대하고 걱정없이 저장할 수 있으며, 나의 일기를 한 눈에 확인하고 친구와 공유하며 댓글로 소통할 수 있습니다.
또한 기본 제공되는 300여종의 스티커 및 템플릿를 활용하여 간단하게 다이어리를 꾸밀 수 있으며 원하는 사진을 스티커로 만들어 나만의 다이어리를 만들 수 있습니다. 

## 🌕 기술 스택
- DEVELOP &nbsp;
  ![Spring](https://img.shields.io/badge/SpringBoot-6DB33F?style=round-square&logo=Spring&logoColor=white) <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white"/>

- AWS &nbsp;
  <img src="https://img.shields.io/badge/Amazon AWS-232F3E?style=flat-square&logo=Amazon%20AWS&logoColor=white"/> <img src="https://img.shields.io/badge/Amazon S3-569A31?style=flat-square&logo=AmazonS3&logoColor=white"/> <img src="https://img.shields.io/badge/Amazon EC2-FF9900?style=flat-square&logo=Amazon EC2&logoColor=white"/> <img src="https://img.shields.io/badge/Amazon RDS-527FFF?style=flat-square&logo=Amazon RDS&logoColor=white"/> <img src="https://img.shields.io/badge/Amazon Route53-E68B49?style=flat-square&logo=Amazon Route53&logoColor=white"/>

- ETC &nbsp;
  <img src="https://img.shields.io/badge/GitHub -181717?style=flat-square&logo=GitHub&logoColor=white"/> <img src="https://img.shields.io/badge/GitHub Action-256EE0?style=flat-square&logo=GitHub Action&logoColor=white"/> <img src="https://img.shields.io/badge/Docker-23BEDE?style=flat-square&logo=Docker&logoColor=white"/> <img src="https://img.shields.io/badge/Redis-FF0000?style=flat-square&logo=Redis&logoColor=white"/></br>

## 🌕 라이브러리
1. lombok
2. spring web
3. spring data jpa
4. spring data jdbc
5. spring data rest
6. spring data validation
7. spring boot test
9. spring security test
10. spring data redis
11. spring cloud aws
12. mysql driver
![Untitled (5)](https://user-images.githubusercontent.com/68282057/182026455-482f03dc-966b-481c-8e58-6a1e9ed21a94.png)


## 🌕 프로젝트 구조
### 설명
#### Global
1. main/java/[프로젝트명]/domain/global/config     ▶️ jwt/redis security 및 S3 설정 
2. main/java/[프로젝트명]/domain/global/constant ▶️응답 message 설정 
3. main/java/[프로젝트명]/domain/global/entity ▶️BaseTimeEntity 관련 설정
4. main/java/[프로젝트명]/domain/global/exception ▶️ErrorCode 및 ErrorHandler 설정
5. main/java/[프로젝트명]/domain/global/redis ▶️redis 관련 설정

#### Domain
1. main/java/[프로젝트명]/[기능명]/controller ▶️Controller
2. main/java/[프로젝트명]/[기능명]/dto ▶️Request, Response, Mapper 등 dto
3. main/java/[프로젝트명]/[기능명]/entity ▶️Entity
4. main/java/[프로젝트명]/[기능명]/exception ▶️Custom Exception
5. main/java/[프로젝트명]/[기능명]/repository ▶️Repository
6. main/java/[프로젝트명]/[기능명]/service ▶️Service
7. main/java/[프로젝트명]/LakkuLakkuApplication.java
8. test/java/[프로젝트명]/domain ▶️기능별 테스트
9. test/java/[프로젝트명]/global ▶️security 관련 테스트
10. main/resources/application.yml ▶️ session 관련 설정

### 🌕 폴더
<pre>
<code>
└── 🗂 main
    ├── 🗂 java
    │   └── 🗂 com
    │       └── 🗂 efub
    │           └── 🗂 lakkulakku
    │               ├── 📑 Application.java
    │               ├── 🗂 domain
    │               │   ├── 🗂 comment
    │               │     ├── 🗂 controller
    │               │       ├── 📑 CommentController
    │               │     ├── 🗂 dto
    │               │       ├── 📑 CommentMapper
    │               │       ├── 📑 CommentResDto
    │               │     ├── 🗂 entity
    │               │       ├── 📑 Comment
    │               │     ├── 🗂 exception
    │               │       ├── 📑 CommentNotFoundException
    │               │       ├── 📑 ParentNotFoundException
    │               │       ├── 📑 UnauthorizedException
    │               │     ├── 🗂 repository
    │               │       ├── 📑 CommentRepository
    │               │     ├── 🗂 service
    │               │       ├── 📑 CommentService
    │               │       ├── 📑 CommentServiceImpl
    │               │   ├── 🗂 diary
    │               │     ├── 🗂 controller
    │               │       ├── 📑 DiaryController
    │               │       ├── 📑 DiaryEditController
    │               │     ├── 🗂 dto
    │               │       ├── 📑 DiaryHomeMapper
    │               │       ├── 📑 DiaryHomeResDto
    │               │       ├── 📑 DiaryLookupResDto
    │               │       ├── 📑 DiaryMapper
    │               │       ├── 📑 DiaryMessageResDto
    │               │       ├── 📑 DiaryResDto
    │               │     ├── 🗂 entity
    │               │       ├── 📑 Diary
    │               │     ├── 🗂 exception
    │               │       ├── 📑 BadDateRequestException
    │               │       ├── 📑 DiaryNotFoundException
    │               │       ├── 📑 DuplicateDiaryException
    │               │     ├── 🗂 repository
    │               │       ├── 📑 DiaryRepository
    │               │     ├── 🗂 service
    │               │       ├── 📑 DiaryService
    │               │   ├── 🗂 file
    │               │     ├── 🗂 entity
    │               │       ├── 📑 File
    │               │     ├── 🗂 exception
    │               │       ├── 📑 FileExtensionException
    │               │       ├── 📑 S3IOException
    │               │     ├── 🗂 repository
    │               │       ├── 📑 FileRepository
    │               │   ├── 🗂 friend
    │               │     ├── 🗂 controller
    │               │       ├── 📑 FriendController
    │               │     ├── 🗂 dto
    │               │       ├── 📑 FriendReqDto
    │               │       ├── 📑 FriendResDto
    │               │     ├── 🗂 entity
    │               │       ├── 📑 Friend
    │               │     ├── 🗂 exception
    │               │       ├── 📑 DuplicateFriendException
    │               │     ├── 🗂 repository
    │               │       ├── 📑 FriendRepository
    │               │     ├── 🗂 service
    │               │       ├── 📑 FriendService
    │               │   ├── 🗂 image
    │               │     ├── 🗂 dto
    │               │       ├── 📑 ImageMapper
    │               │       ├── 📑 ImageResDto
    │               │     ├── 🗂 entity
    │               │       ├── 📑 Image
    │               │     ├── 🗂 exception
    │               │     ├── 🗂 repository
    │               │       ├── 📑 ImageRepository
    │               │   ├── 🗂 likes
    │               │     ├── 🗂 controller
    │               │     ├── 🗂 dto
    │               │       ├── 📑 LikeMapper
    │               │       ├── 📑 LikeResDto
    │               │     ├── 🗂 entity
    │               │       ├── 📑 Likes
    │               │     ├── 🗂 exception
    │               │     ├── 🗂 repository
    │               │       ├── 📑 LikeRepository
    │               │     ├── 🗂 service
    │               │       ├── 📑 LikeService
    │               │   ├── 🗂 notification
    │               │     ├── 🗂 controller
    │               │     ├── 🗂 dto
    │               │       ├── 📑 NotificationHomeMapper
    │               │       ├── 📑 NofificationHomeResDto
    │               │     ├── 🗂 entity
    │               │       ├── 📑 Notification
    │               │     ├── 🗂 exception
    │               │     ├── 🗂 repository
    │               │       ├── 📑 NotificationRepository
    │               │     ├── 🗂 service
    │               │   ├── 🗂 profile
    │               │     ├── 🗂 controller
    │               │       ├── 📑 ProfileController
    │               │     ├── 🗂 dto
    │               │     ├── 🗂 entity
    │               │       ├── 📑 Profile
    │               │     ├── 🗂 exception
    │               │       ├── 📑 
    │               │     ├── 🗂 repository
    │               │       ├── 📑 ProfileRepository
    │               │     ├── 🗂 service
    │               │       ├── 📑 ProfileService
    │               │   ├── 🗂 sticker
    │               │     ├── 🗂 dto
    │               │       ├── 📑 StickerMapper
    │               │       ├── 📑 StickerResDto
    │               │     ├── 🗂 entity
    │               │       ├── 📑 Sticker
    │               │     ├── 🗂 exception
    │               │     ├── 🗂 repository
    │               │       ├── 📑 StickerRepository
    │               │   ├── 🗂 template
    │               │     ├── 🗂 dto
    │               │       ├── 📑 TemplateMapper
    │               │       ├── 📑 TemplateResDto
    │               │     ├── 🗂 entity
    │               │       ├── 📑 Template
    │               │     ├── 🗂 exception
    │               │     ├── 🗂 repository
    │               │       ├── 📑 TemplateRepository
    │               │   ├── 🗂 text
    │               │     ├── 🗂 dto
    │               │       ├── 📑 TextMapper
    │               │       ├── 📑 TextResDto
    │               │     ├── 🗂 entity
    │               │       ├── 📑 Text
    │               │     ├── 🗂 exception
    │               │     ├── 🗂 repository
    │               │       ├── 📑 TextRepository
    │               │   ├── 🗂 users
    │               │     ├── 🗂 controller
    │               │       ├── 📑 HomeController
    │               │       ├── 📑 LoginController
    │               │       ├── 📑 SettingsController
    │               │       ├── 📑 WithdrawalController
    │               │     ├── 🗂 dto
    │               │       ├── 📑 HomeMapper
    │               │       ├── 📑 HomeResDto
    │               │       ├── 📑 LoginReqDto
    │               │       ├── 📑 LoginResDto
    │               │       ├── 📑 ProfileUpdateReqDto
    │               │       ├── 📑 ProfileUpdateResDto
    │               │       ├── 📑 SettingsInfoDto
    │               │       ├── 📑 SettingsUpdateDto
    │               │       ├── 📑 SignupReqDto
    │               │       ├── 📑 UpdatePasswordDto
    │               │       ├── 📑 WithdrawReqDto
    │               │     ├── 🗂 entity
    │               │       ├── 📑 AuthUsers
    │               │       ├── 📑 Users
    │               │     ├── 🗂 exception
    │               │       ├── 📑 BadTokenRequestException
    │               │       ├── 📑 DuplicateEmailException
    │               │       ├── 📑 DuplicateNicknameException
    │               │       ├── 📑 PasswordNotMatchedException
    │               │       ├── 📑 TokenExpiredException
    │               │       ├── 📑 UserNotFoundException
    │               │     ├── 🗂 repository
    │               │       ├── 📑 UsersRepository
    │               │     ├── 🗂 service
    │               │       ├── 📑 AuthUsers
    │               │       ├── 📑 CustomUsersDetailsService
    │               │       ├── 📑 SettingsService
    │               │       ├── 📑 SettingsServiceImpl
    │               │       ├── 📑 UsersService
    │               ├── 🗂 global
    │               │   ├── 🗂 config
    │               │     ├── 🗂 jwt
    │               │       ├── 📑 JwtAuthenticationFilter
    │               │       ├── 📑 JwtProvider
    │               │     ├── 🗂 redis
    │               │       ├── 📑 RedisConfig
    │               │     ├── 📑 AppConfig
    │               │     ├── 📑 S3Config
    │               │   ├── 🗂 constant
    │               │       ├── 📑 ResponseConstant
    │               │   ├── 🗂 entity
    │               │       ├── 📑 BasicTimeEntity
    │               │   ├── 🗂 exception
    │               │     ├── 📑 ErrorCode
    │               │     ├── 📑 ErrorReponse
    │               │     ├── 📑 GlobalExceptionHandler
    │               │     ├── 🗂 jwt
    │               │       ├── 📑 BasicResponse
    │               │       ├── 📑 CustomAuthenticationEntryPoint
    │               │   ├── 🗂 redis
    │               │     ├── 📑 RedisService
    └── 🗂 resources
        ├── 📑 application.yml
</code>
</pre>


## 🌕 데이터베이스 설계도(E-R diagram)
### [🔗 Link](https://drive.google.com/file/d/1Fr5pzP-hzw_P2NPybslrYPWZtEUBHl0a/view?usp=sharing)

## 🌕 API 명세서
### [🔗 Link](https://efub.notion.site/API-0dccb2bd9eb849d893c874c34771a427)

