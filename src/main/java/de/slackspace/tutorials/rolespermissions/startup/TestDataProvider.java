package de.slackspace.tutorials.rolespermissions.startup;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * A class just to create the database tables and provide some test users.
 */
@Component
public class TestDataProvider implements InitializingBean {

	@Autowired
	private DataSource dataSource;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		deleteExistingData(jdbcTemplate);
		createTables(jdbcTemplate);
		createData(jdbcTemplate);
	}

	private void deleteExistingData(JdbcTemplate jdbcTemplate) {
		jdbcTemplate.execute("drop table role_members IF EXISTS");
		jdbcTemplate.execute("drop table role_permissions IF EXISTS");
		jdbcTemplate.execute("drop table permissions IF EXISTS");
		jdbcTemplate.execute("drop table roles IF EXISTS");
		jdbcTemplate.execute("drop table users IF EXISTS");
	}

	private void createData(JdbcTemplate jdbcTemplate) {
		jdbcTemplate.execute("insert into users(username, password, enabled) VALUES('rod', 'koala', true)");;
		jdbcTemplate.execute("insert into permissions(name) VALUES('Show Account Page')");
		jdbcTemplate.execute("insert into roles(name) VALUES('Admin')");
		jdbcTemplate.execute("insert into role_members(roles_id, members_id) VALUES(1,1)");
		jdbcTemplate.execute("insert into role_permissions(roles_id,permissions_id) VALUES(1,1)");
		
		jdbcTemplate.execute("insert into users(username, password, enabled) VALUES('peter', 'opal', true)");;
		jdbcTemplate.execute("insert into permissions(name) VALUES('Show Protected Page')");
		jdbcTemplate.execute("insert into roles(name) VALUES('Users')");
		jdbcTemplate.execute("insert into role_members(roles_id, members_id) VALUES(2,2)");
		jdbcTemplate.execute("insert into role_permissions(roles_id,permissions_id) VALUES(2,2)");
	}
	
	private void createTables(JdbcTemplate jdbcTemplate) {
		jdbcTemplate.execute("create table if not exists users("+
				  "id bigint AUTO_INCREMENT not null primary key,"+
				  "username varchar(100) not null,"+
				  "password varchar(100) not null,"+
				  "enabled boolean not null);");
							      
		jdbcTemplate.execute("create table if not exists roles("+
				  "id bigint AUTO_INCREMENT not null primary key,"+
				  "name varchar(100) not null);");
				  
		jdbcTemplate.execute("create table if not exists permissions("+
				  "id bigint AUTO_INCREMENT not null primary key,"+
				  "name varchar(100) not null);");
				  
		jdbcTemplate.execute("create table if not exists role_members("+
				  "roles_id bigint not null,"+
				  "members_id bigint not null);");

		jdbcTemplate.execute("create table if not exists role_permissions("+
				  "roles_id bigint not null,"+
				  "permissions_id bigint not null);");
	}

}
