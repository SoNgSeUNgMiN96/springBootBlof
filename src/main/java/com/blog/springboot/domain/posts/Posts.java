package com.blog.springboot.domain.posts;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter     //롬복 어노테이션  모든 Getter 자동 생성
@NoArgsConstructor  //롬복 어노테이션  기본 생성자 자동 추가
@Entity     //JPA 어노테이션 테이블과 링크될 클래스임을 나타냄
public class Posts {        //주요 어노테이션을 클래스에 가깝게 둔다.

    @Id //해당 테이블의 PK 필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK 생성 규칙. GenerationType.IDENTITY 옵션이 있어야만 auto_increment가 된다
    private Long id;

    @Column(length = 500, nullable = false)     //칼럼임을 나타내며 굳이 나타내지 않아도 칼럼이 됨. 사용하는 이유는 옵션변경때문
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

}
