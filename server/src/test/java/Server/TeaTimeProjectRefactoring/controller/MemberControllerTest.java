package Server.TeaTimeProjectRefactoring.controller;

import Server.TeaTimeProjectRefactoring.dto.MemberDto;
import Server.TeaTimeProjectRefactoring.dto.MemberDto.Post;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private Gson gson;
//
//    @Test
//    void postMemberTest() throws Exception {
//        // given
//        MemberDto.Post post = new Post(
//            "mason@gmail.com",
//            "1111",
//            "masonLee",
//            "mason",
//            "911212",
//            "profile_url"
//        );
//
//        String content = gson.toJson(post);
//
//        // when
//        ResultActions actions =
//            mockMvc.perform(
//                post("/api/members")
//                    .accept(MediaType.APPLICATION_JSON)
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(content)
//            );
//
//        // then
//        actions
//            .andExpect(status().isCreated())
//            .andExpect(header().string(HttpHeaders.LOCATION, is(startsWith("/api/members"))));
//    }
}
