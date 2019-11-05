package pw.react.backend.reactbackend;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pw.react.backend.reactbackend.Controllers.UserController;
import pw.react.backend.reactbackend.Model.User;
import pw.react.backend.reactbackend.Repositories.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = {"it"})
public class MainIT {

    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext wac;

    @Autowired
    private UserRepository repository;
    @Autowired
    private UserController Controller;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.Controller).build();
    }

    @Test
    public void givenCreateWithRequestBody_whenMockMVC_thenResponseOK() throws Exception {

        String content="[{"+
            "\"login\":"+"\"marti3\","+
                    "\"firstName\":"+"\"marti3\","+
                    "\"lastName\":"+"\"marti3\","+
                    "\"dateOfBirth\":"+"\"1998-01-01\","+
                    "\"active\":"+"false"+
        "},{"+
                "\"login\":"+"\"marti4\","+
                "\"firstName\":"+"\"marti3\","+
                "\"lastName\":"+"\"marti3\","+
                "\"dateOfBirth\":"+"\"1998-01-01\","+
                "\"active\":"+"false"+
        "}]";

        MockHttpServletRequestBuilder postUsers=post("/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        mockMvc.perform(postUsers)
                .andExpect(status().isOk());
        User actualUser=repository.findByLogin("marti3");
        assertThat(actualUser).isNotNull();
        assertThat(actualUser.getLogin()).isEqualTo("marti3");
    }

    @Test
    public void givenCheckWithPathVariable_whenMockMVC_thenResponseOK() throws Exception
    {
        MockHttpServletRequestBuilder checkUser=get("/users/Login1");
        mockMvc.perform(checkUser)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login",equalTo("Login1")));
    }



}