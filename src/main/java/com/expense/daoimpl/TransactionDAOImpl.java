package com.expense.daoimpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.expense.interfaces.TransactionDAO;
@Component("transactionDAOImpl")
public class TransactionDAOImpl implements TransactionDAO {
	
	private NamedParameterJdbcTemplate jdbcTempl;
	private DataSource dataSource;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTempl = new NamedParameterJdbcTemplate(dataSource);
		this.dataSource = dataSource;
	}

	@Override
	public void inserTransaction(int userIDWhoPay, int userIDForExpense, int amount) {
		String sql = "INSERT INTO Transaction (	FromId, ToId, Amount, DateGiven) "
				+ "		VALUES(	:userIDWhoPay,:userIDForExpense, :amount,:date)";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("userIDWhoPay", userIDWhoPay);
		params.addValue("userIDForExpense", userIDForExpense);
		params.addValue("amount", amount);
		params.addValue("date", dateFormat.format(date));
		
		jdbcTempl.update(sql, params);

	}

}
