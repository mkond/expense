package com.expense.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.expense.interfaces.ExpCategoryDAO;
import com.expense.objects.ExpCategory;
@Component("expCategoryDAOImpl")
public class ExpCategoryDAOImpl implements ExpCategoryDAO {
	
	private NamedParameterJdbcTemplate jdbcTempl;
	private DataSource dataSource;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTempl = new NamedParameterJdbcTemplate(dataSource);
		this.dataSource = dataSource;
	}

	@Override
	public List<ExpCategory> getExpCategoryList() {
		String sql = "SELECT * FROM ExpCategory";
		return jdbcTempl.query(sql, new ExpCategoryRowMaper());
	}
	
	private static final class ExpCategoryRowMaper implements RowMapper<ExpCategory>{

		@Override
		public ExpCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
			ExpCategory expCategory = new ExpCategory();
			expCategory.setId(rs.getInt("id"));
			expCategory.setName(rs.getString("name"));
			return expCategory;
		}
		
	}

}
