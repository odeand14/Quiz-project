import com.j256.ormlite.jdbc.JdbcConnectionSource;
import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

// Created by Andreas Ødegaard on 08.12.2016.
public class DBloaderTest {
    @Test
    public void getConnectionSource() throws Exception {
        JdbcConnectionSource con = DBloader.getConnectionSource();
        assertNotNull(con);
        assertTrue(con.getUrl().equals("jdbc:mysql://localhost:3306/database-here"));
    }

    @Test
    public void loadPropertiesFile() throws Exception {
        Properties p = DBloader.loadPropertiesFile();
        assertNotNull(p);
        String url = p.getProperty("JDBC.user");
        assertTrue(url.equals("root"));
    }

}