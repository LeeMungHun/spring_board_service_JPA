# 게시판 서비스

### 개발환경
- Language: [Java 11]()
- Frame work: [Spring boot 2.6.x]()
- Data Base: [MySQL 8.x]()
- Library: [lombok,thymeleaf]()

### 기능
- 전체보기(실행화면)
- 저장
- 수정,삭제
- 검색


### 전체보기(실행화면)

![image](https://github.com/MungHun/spring_board_service_JPA/assets/46837684/21f63247-c8eb-4a76-b636-a89c248b1a88)


1. 실행했을때 기본적으로 10개의 게시판 데이터를 넣습니다.
![image](https://github.com/MungHun/spring_board_service_JPA/assets/46837684/d822e871-4e67-4d2a-aa60-333e15218371)

--------------------
### 저장(글쓰기)

![image](https://github.com/MungHun/spring_board_service_JPA/assets/46837684/7fbe58d4-2dd0-4183-bf64-1fc39364712a)

1. 글쓰기 버튼 클릭시 Get요청으로 게시글 작성폼으로 넘겨주고 작성완료를 누르면 
BoardDto형태로 모델에 넣어주고 저장한다.
![image](https://github.com/MungHun/spring_board_service_JPA/assets/46837684/d42df9ac-a0b1-4b86-9d7d-e20911ef6658)

2. 작성완료 후 저장완료 문구를 표시하고 작성한게시글을 확인할수 있도록 게시글 조회를 리다이렉트시킨다.(새로고침을 하더라고 중복요청을 하지않는다.)

![image](https://github.com/MungHun/spring_board_service_JPA/assets/46837684/a8997579-d62c-46a8-b420-32f53f7e4204)



3. 게시글 목록화면 변화

![image](https://github.com/MungHun/spring_board_service_JPA/assets/46837684/bdf537ac-0bf0-4c79-b3d8-c0c1f964fdc0)

--------------------
### 수정,삭제 (title9 제거)

![image](https://github.com/MungHun/spring_board_service_JPA/assets/46837684/79101932-e85f-4cf7-8497-9f6efe35fe07)

### 검색(제목검색)

1. title1검색시 아래와 같이 표시

![image](https://github.com/MungHun/spring_board_service_JPA/assets/46837684/138b93ad-e60e-4d5a-ba60-4b56e6f1b02d)


2. 폼에 아무것도 작성안하고 검색하면 게시판리스트 그대로 보여줍니다.

![image](https://github.com/MungHun/spring_board_service_JPA/assets/46837684/16bfd037-cea2-43a7-a606-2d7dd33f2caa)



--------------------

### Board의 Entity와 Jpa 인터페이스 코드

## Board
```
@Entity
@Table(name = "board")
@Getter
@Setter
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(length = 10, nullable = false)
    private String writer;

    private Board() {
    }

    @Builder
    public Board(Long id, String title, String content, String writer) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
}
```
--------------

## JpaBoardRepository
```
public interface JpaBoardRepository extends JpaRepository<Board,Long> {
    List<Board> findByTitleContaining(String searchTitle);
}
```


### 프로젝트 추가,수정이 필요하거나 아쉬운점
1. 검색된 게시글이 하나일때 페이징기능에서 오류발생 
2. 게시글 수정할 때 수정된 부분만 업데이트 해야하는데 모두 수정하는 부분
3. 로그인기능(네이버,구글 연동)
4. 파일,이미지 업로드(형식에 맞도록 비즈니스로직단에서 필터링)
5. 게시글 임시저장기능
6. 회원정보 암호화
7. 서버 to 서버 통신
8. 부트스트랩과 페이징기능 인터넷에서 그대로 가져온부분

### etc
프로젝트를 진행할때 처음에는 PC 메모리를 저장소로 만들어보고 그 후 Mybatis와 JPA를 이용해 DB에 저장했는데
깊게 이해하지못해서 JPA는 기본적인 부분에서 사용하고 좀더 세세한 쿼리문이 필요할때는 Mybatis 를 사용할거같다.
마지막으로 spring boot 를 사용해 프로젝트를 진행함으로서 더 빠른 프로젝트 진행을 할 수 있어서 좋았다.
(기본 싱글톤지원,라이브러리 버전 자동호환 등..)

[Mybatis 프로젝트](https://github.com/MungHun/spring_board_service-master_mybatis)
