package springbootec2;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Springbootec2Application.class)
@WebAppConfiguration
@TestPropertySource(locations="classpath:test.properties")
public class Springbootec2ApplicationTests {

	@Autowired
	DataSource dataSource;

	@Test
	public void contextLoads() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.query(RepoImpl.SELECT_FIRST_NAME_FROM_PERSONS_LIMIT_1, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				String firstNameInDatabase = rs.getString(1);
				Assert.assertEquals("axel", firstNameInDatabase);
			}
		});

	}

}
