package com.example.demo.presentation.controller;

import com.example.demo.testcomponent.TestUtils;
import java.util.Arrays;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class BaseControllerTest {

  @Autowired
  private DataSource dataSource;

  /**
   * 引数で指定されたテーブルのtruncate処理を実施する。
   */
  protected void cleanUp() {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    //クリーンアップ時は一時的に外部キー制約を無視。
    jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS=0;");
    Arrays.stream(TestUtils.allTableNames).forEach(tableName -> {
          jdbcTemplate.execute("TRUNCATE TABLE " + tableName);
        }
    );
    jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS=1;");
  }
}
