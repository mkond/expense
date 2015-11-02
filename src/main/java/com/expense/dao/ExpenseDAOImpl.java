package com.expense.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.expense.interfaces.ExpenseDAO;
import com.expense.objects.ExpCategory;
import com.expense.objects.Expense;
import com.expense.objects.ExpenseUser;

@Component("expenseDAOImpl")
public class ExpenseDAOImpl implements ExpenseDAO {
	private String ssss;
	private NamedParameterJdbcTemplate jdbcTempl;
	private DataSource dataSource;
	
	@Autowired
	public void setDataSource(DataSource dataSource){//датасорс бази даних
		this.jdbcTempl = new NamedParameterJdbcTemplate(dataSource);
		this.dataSource = dataSource;
	}
	
	public String show(){
		return "hello!";
	}
	
	@Override
	public List<Expense> getExpenseList() {
		String sql = "SELECT 	 E.id,"+
							"EC.name as category,"+
							"EU.name as username,"+
							"E.Amount,"+
							"E.DateSpent,"+
							"E.Title"+
					" FROM expenselist as E"+
					" left join ExpUser as EU on E.WhoSpentId=EU.id"+
					" left join ExpCategory as EC on E.expCategoryId = EC.id";
		return jdbcTempl.query(sql, new MP3RowMaper());
	}
	
	private static final class MP3RowMaper implements RowMapper<Expense>{

		@Override
		public Expense mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Expense expense = new Expense();
			expense.setId(rs.getInt("id"));
			expense.setAmount(rs.getInt("Amount"));
			expense.setDate(rs.getDate("DateSpent"));
			expense.setTitle(rs.getString("Title"));
			
			ExpenseUser expenseUser = new ExpenseUser();
			expenseUser.setName(rs.getString("username"));
			expense.setExpenseUser(expenseUser);
			
			ExpCategory expCategory = new ExpCategory();
			expCategory.setName(rs.getString("category"));
			expense.setExpCategory(expCategory);
			return expense;
		}
		
	}

}
