package com.wego.web.persistance;

import static org.junit.Assert.fail;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.wego.web.cfg.RootConfig;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@ContextConfiguration(classes = {RootConfig.class})
@Log4j
public class DataSourceTest {
	@Setter(onMethod_= {@Autowired})
	private DataSource dataSource;
	@Test
	public void testConnection() {
		try (Connection con = dataSource.getConnection()){
			log.info(con);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}


}
