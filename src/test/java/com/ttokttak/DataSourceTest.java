package com.ttokttak;

import com.ttokttak.config.RootConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringJUnitConfig(classes = RootConfig.class)
public class DataSourceTest {
    @Autowired
    private DataSource dataSource;

    @Test
    public void testConnection() throws Exception {
        Connection conn = dataSource.getConnection();
        System.out.println("연결 성공: " + conn);
        conn.close();
    }
}
