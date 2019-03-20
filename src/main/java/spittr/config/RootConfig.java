package spittr.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages={"spittr"}, excludeFilters={@Filter(type=FilterType.ANNOTATION, value=EnableWebMvc.class)})
public class RootConfig {
	
	@Bean(destroyMethod="close")
	public DataSource dataSource(){
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:orcl");
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		dataSource.setUsername("scott");
		dataSource.setPassword("123456");
		dataSource.setInitialSize(20);
		dataSource.setMaxActive(30);
		return dataSource;
	}
}
