package com.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DBRepository {
	private static Logger logger = LoggerFactory.getLogger(DBRepository.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void displayDBInfo() throws Exception {
		Connection conn = jdbcTemplate.getDataSource().getConnection();
		logger.info("Connection valid: " + conn.isValid(1000));
		logger.info("Server name: " + jdbcTemplate.queryForObject("select @@hostname;", String.class));
		logger.info("User name: " + jdbcTemplate.queryForObject("select USER();", String.class));
		logger.info("Db Name: " + jdbcTemplate.queryForObject("select DATABASE();", String.class));
		logger.info("TX Isolation level: " + jdbcTemplate.queryForList(
				"SELECT * FROM information_schema.session_variables WHERE variable_name = 'tx_isolation';"));

		BasicDataSource bds = (BasicDataSource) jdbcTemplate.getDataSource();
		getPoolStats(bds);
		logger.info("Close a connection and check status");
		conn.close();
		getPoolStats(bds);
		logger.info("obtain max connections and check status");
		List<Connection> connections = new ArrayList<>();
		for (int i = 0; i < bds.getMaxTotal(); i++) {
			connections.add(bds.getConnection());
		}
		getPoolStats(bds);
		logger.info("Try to obtain one additional connection and verify error.");
		try {
			bds.setMaxWaitMillis(1000);
			bds.getConnection();
			getPoolStats(bds);
		} catch (Exception ex) {
			logger.info("Successfully got an exception", ex);
		}

	}

	private static void getPoolStats(BasicDataSource bds) {
		logger.info("Max Idle: " + bds.getMaxIdle());
		logger.info("Max Active: " + bds.getMaxIdle());
		logger.info("Active: " + bds.getNumActive());
		logger.info("Idle: " + bds.getNumIdle());
	}
}