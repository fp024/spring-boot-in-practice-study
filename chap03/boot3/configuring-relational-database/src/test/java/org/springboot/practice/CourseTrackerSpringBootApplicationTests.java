package org.springboot.practice;

import static org.assertj.core.api.Assertions.assertThat;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CourseTrackerSpringBootApplicationTests {
  @Autowired private DataSource dataSource;

  @Test
  void contextLoads() {}

  @Test
  void givenDatasourceAvailableWhenAccessDetailsThenExpectDetails() throws SQLException {
    assertThat(dataSource.getClass()) //
        .isEqualTo(HikariDataSource.class);
    assertThat(dataSource.getConnection().getMetaData().getDatabaseProductName()) //
        .isEqualTo("H2");
  }
}
