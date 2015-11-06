package com.expense.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.expense.interfaces.TransactionDAO;

import com.expense.objects.UsersTransaction;
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
	public void insertTransaction(int userIDWhoPay, int userIDForExpense, int amount) {
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

//	@Override
//	public List<UsersTransaction> getUserListWhoNeedPayToMe(String user) {
//		String sql="select c.FromId, c.ToId, sum(c.Summ) from ((SELECT U1.name as FromId, U2.name as ToId, sum(T.Amount) as Summ "
//					+" FROM Transaction as T "
//					+" left join expense.ExpUser as U1 on T.FromId = U1.id "
//					+" left join ExpUser as U2 on T.ToId = U2.id "
//					+" group by U1.name, U2.name)  "
//					
//					+" union all  "
//					+" (SELECT U2.name as FromId, U1.name as ToId, -sum(T.Amount) as Summ "
//					+" FROM Transaction as T "
//					+" left join expense.ExpUser as U1 on T.FromId = U1.id "
//					+" left join ExpUser as U2 on T.ToId = U2.id "
//					+" group by U2.name, U1.name )) as c "
//					+" where FromId=:user "
//					+" group by c.FromId, c.ToId "
//					
//					+" having sum(c.Summ) > 0";
//		
//		MapSqlParameterSource param = new MapSqlParameterSource();
//		param.addValue("user", user);
//		return null;
////	}
//	
//	private static final class TransactionRomMapper implements RowMapper<UsersTransaction>{
//
//		@Override
//		public UsersTransaction mapRow(ResultSet rs, int arg1) throws SQLException {
//			UsersTransaction userTR = new UsersTransaction();
//			String expenseUser = new String();
//			
//			return null;
//		}
		
//	}



}
