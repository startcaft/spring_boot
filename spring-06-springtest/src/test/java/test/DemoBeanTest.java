package test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.startcaft.test.TestBean;
import com.startcaft.test.TestConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfig.class})
@ActiveProfiles("prod")
public class DemoBeanTest {
	
	@Autowired
	private TestBean testBean;
	
	@Test
	public void prodBeanShouldInject(){
		
		String expected = "from development profile";
		String actual = testBean.getContent();
		Assert.assertEquals(expected, actual);
	}
}
