package com.main.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import net.minidev.json.JSONObject;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BaseTest {
	
	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	DataSourceProperties dataSourceProperties;

	@Test
	public void contextLoads() {
	}

	private MockMvc mockMvc; // MockMvcBuilders.webAppContextSetup(this.wac).build()

	@Autowired
	private WebApplicationContext wac; // WebApplicationContext

	// @Autowired
	// private MockHttpSession session;// http session
	//
	// @Autowired
	// private MockHttpServletRequest request;// http request\

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testQ1() throws Exception {
		/*Map<String, Object> map = new HashMap<>();
		map.put("name", "Sweta");*/

		MvcResult result = mockMvc.perform(get("/base").param("name","Sweta"))
				//.content(JSONObject.toJSONString(map)))
				.andDo(print())
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}



	@Test
	public void testDataSource() throws Exception {

		DataSource dataSource = applicationContext.getBean(DataSource.class);

		System.out.println(dataSource);
		System.out.println(dataSource.getClass().getName());
		System.out.println(dataSourceProperties);
	}

}
