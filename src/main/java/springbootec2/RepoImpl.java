package springbootec2;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by haugom on 04.10.17.
 */
@Component
public class RepoImpl implements Repo {

    public static final String SELECT_FIRST_NAME_FROM_PERSONS_LIMIT_1 = "select first_name from persons limit 1";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void init() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.migrate();
    }

    @Override
    public String currentTimeInDatabase() {
        return getSingleString("select now()");
    }

    private String getSingleString(String sqlToExecute) {
        final String[] result = {""};
        jdbcTemplate.query(sqlToExecute, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                result[0] = rs.getString(1);
            }
        });
        return result[0];
    }

    @Override
    public String author() {
        return getSingleString(SELECT_FIRST_NAME_FROM_PERSONS_LIMIT_1);
    }
}
