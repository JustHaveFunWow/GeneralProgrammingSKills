package nimdanoob.knight;

import nimdanoob.knight.web.KnightApplication;
import nimdanoob.knight.web.controller.UserController;
import nimdanoob.knight.web.domain.mapper.CityMapper;
import nimdanoob.knight.web.domain.model.CityExample;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {KnightApplication.class})
public class KnightApplicationTests {
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

    @Test
    public void testUserController() throws Exception {
        RequestBuilder request = null;
        request = get("users");
        //get 查一下user列表，结果应该为空
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));

        //post 提交一个User
        request = post("/users/")
                .param("id","1")
                .param("name","test master")
                .param("age","20");
        mvc.perform(request)
                .andExpect(content().string("success"));

        //get 获取 user列表

    }

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void test() throws Exception{
        stringRedisTemplate.opsForValue().set("aaa","111");
        Assert.assertEquals("111",stringRedisTemplate.opsForValue().get("aaa")   );
    }

    @Test
    public void testRedis() throws Exception{

    }


    @Autowired
    private CityMapper cityMapper;
    @Test
    public void testMybatis(){
        long count = cityMapper.countByExample(new CityExample());

    }



    @Test
    public void contextLoads() {
    }


}
