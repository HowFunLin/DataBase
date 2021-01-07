package manager;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class Connections
{
	public static Connection getConnection() throws Exception
	{
		InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
		
		Properties properties = new Properties();
		properties.load(is);
		
		DataSource source = DruidDataSourceFactory.createDataSource(properties);
		
		return source.getConnection();
	}
}
