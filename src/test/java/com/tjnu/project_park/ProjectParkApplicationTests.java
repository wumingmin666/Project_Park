package com.tjnu.project_park;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class ProjectParkApplicationTests {
    @Autowired
    DataSource dataSource;
    @Test
    void contextLoads() {
    }
    @Test
    void getConnect() throws SQLException {
        System.out.println(dataSource.getConnection());
    }

}
