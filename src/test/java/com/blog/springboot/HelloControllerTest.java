package com.blog.springboot;


import com.blog.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)        //테스트ㅡ를 진행할 때 JUnit에 내장된 실행자 외에 다른 실자를 실행시킵니다.
@WebMvcTest(controllers = HelloController.class)    //여러 테스트 어노테이션 중 Web에 집중할 수 있는 어노테이션
public class HelloControllerTest {

    @Autowired      //스프링이 관리하는 빈을 주입받는다 ?
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";
        mvc.perform(get("/hello"))  //.hello주소로 HTTP GET 요청을 함
                .andExpect(status().isOk())     //결과를 검증한다
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name",name)
                .param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(name)))     //JSON 응답값을 필드별로 검증할 수 있는 메소드임.
                .andExpect(jsonPath("$.amount",is(amount)));        //$기준 필드명을 명시
    }

}
