package com.expense.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.expense.interfaces.ExpenseUserDAO;
import com.expense.objects.ExpCategory;
import com.expense.objects.Expense;
import com.expense.objects.ExpenseUser;

@Component("expenseUserDAOImpl")
public class ExpenseUserDAOImpl implements ExpenseUserDAO {
	
	private NamedParameterJdbcTemplate jdbcTempl;
	private DataSource dataSource;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTempl = new NamedParameterJdbcTemplate(dataSource);
		this.dataSource = dataSource;
	}

	@Override
	public List<ExpenseUser> getExpenseUserList() {
		
		return null;
	}

	@Override
	public List<ExpenseUser> getExpenseUserListWithoutThisUser(UserDetails user) {
		String sql = "Select * from ExpUser where name not like :name";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("name", "%"+user.getUsername().toString().toUpperCase()+"%");
		return jdbcTempl.query(sql, param, new ExpenseUserRowMaper());
	}
	
	@Override
	public int getUserId(String username) {
		String sql = "Select id from ExpUser where name =:name";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("name", username);
		
		
		return jdbcTempl.queryForObject(sql, param, Integer.class);
	}
	
	
	
	private static final class ExpenseUserRowMaper implements RowMapper<ExpenseUser>{

		@Override
		public ExpenseUser mapRow(ResultSet rs, int arg1) throws SQLException {
			ExpenseUser user = new ExpenseUser();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setFirstname(rs.getString("firstName"));
			user.setLastname(rs.getString("lastName"));
			return user;
		}


		
	}



	

}
