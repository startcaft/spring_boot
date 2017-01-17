package test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.startcaft.mvc.config.SpringMvcConfig;
import com.startcaft.mvc.config.service.DemoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={SpringMvcConfig.class})
//用来声明加载 ApplicationContext 是一个 WebApplicationContext
//它的属性指定的是 web 资源的位置，默认为 src/main/webapp，本列为 src/main/resources
@WebAppConfiguration("src/main/resources")
public class TestController {
	
	private MockMvc mockMvc;
	
	@Autowired
	private DemoService demoService;
	
	@Autowired
	private WebApplicationContext webContext;
	
	@Autowired
	private MockHttpSession session;
	
	@Autowired
	private MockHttpServletRequest request;
	
	@Before
	public void setup(){
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webContext).build();
	}
	
	@Test
	public void testNormalController(){
		
	}
}
