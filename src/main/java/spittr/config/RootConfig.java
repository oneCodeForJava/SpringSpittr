package spittr.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages={"spittr"}, excludeFilters={@Filter(type=FilterType.ANNOTATION, value=EnableWebMvc.class)})
@ImportResource({"classpath:springContext.xml"})
@EnableJpaRepositories(basePackages="spittr.dao", repositoryImplementationPostfix="Helper")
public class RootConfig {
	
	@Bean(destroyMethod="close")
	public DataSource dataSource(){
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:orcl");
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		dataSource.setUsername("scott");
		dataSource.setPassword("lx123456");
		dataSource.setInitialSize(20);
		dataSource.setMaxActive(30);
		return dataSource;
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter(){
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.ORACLE);
		adapter.setShowSql(true);
		adapter.setGenerateDdl(false);
		adapter.setDatabasePlatform("org.hibernate.dialect.Oracle10gDialect");
		return adapter;
	}
	//spring Jpa 访问数据库
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory
		(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter){
		LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
		emfb.setDataSource(dataSource);
		emfb.setJpaVendorAdapter(jpaVendorAdapter);
		emfb.setPackagesToScan("spittr.entity");
		return emfb;
	}
	
	@Bean
	public JpaTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean emf) throws Exception {
	    JpaTransactionManager transactionManager = new JpaTransactionManager();
	    transactionManager.setEntityManagerFactory(emf.getObject());
	    return transactionManager;
	}
	
/*	使用jdbc模板方法
 * @Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource){
		return new JdbcTemplate(dataSource);
	}*/
	
/* 使用hibernate的sessionfactory工厂
 * @Bean
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource){
		LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();
		sfb.setDataSource(dataSource);
		sfb.setPackagesToScan(new String[]{"spittr.entity"});
		Properties pros = new Properties();
		pros.setProperty("dialect", "org.hibernate.dialect.Oracle10gDialect");
		sfb.setHibernateProperties(pros);
		return sfb;
	}*/
	
	
	@Bean()
	public BeanPostProcessor persistenceTranslation(){
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
/*	使用jndi访问数据库
 * @Bean
	public JndiObjectFactoryBean dataSource(){
		JndiObjectFactoryBean jndiObjectFB = new JndiObjectFactoryBean();
		jndiObjectFB.setJndiName("jdbc/SpittrDS");
		jndiObjectFB.setResourceRef(true);
		jndiObjectFB.setProxyInterface(javax.sql.DataSource.class);
		return jndiObjectFB;
	}*/
}
