package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExam
{
	Connection connection;
	Statement statement;
	ResultSet resultSet;
	
	String driverName = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://13.114.123.192/test";
	String user = "root";
	String password = "0228";
	
	public JDBCExam()
	{
		try
		{	

//			// ① 로드
			Class.forName(driverName);
//			// ② 연결
//			connection = DriverManager.getConnection(url, user, password);
//			
//			System.out.println("성공");
			
			String queryString = "SELECT * FROM table1";

			// ② 연결 [Connection]
			connection = DriverManager.getConnection(url, user, password);

			// ② 연결 [Statement]
			statement = connection.createStatement();
			
			// ③ 실행 [CRUD]
			resultSet = statement.executeQuery(queryString);
			
			// 컬럼 정보 가져오기
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

			// 컬럼 출력
			System.out.println(resultSetMetaData.getColumnName(1));

			while (resultSet.next())
			{
				System.out.println(resultSet.getString("name"));
			}
			
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("[로드 오류]\n" + e.getStackTrace());
			System.out.println(e.toString());
		}
		catch (SQLException e)
		{
			System.out.println("[연결 오류]\n" +  e.getStackTrace());
			System.out.println(e.toString());

		}
		
	}
	
	public void closeDatabase()
	{
		try
		{
			if( connection != null )
			{
				connection.close();
			}
			
			if( statement != null )
			{
				statement.close();
			}
			
			if( resultSet != null )
			{
				resultSet.close();
			}
		}
		catch (SQLException e)
		{
			System.out.println("[닫기 오류]\n" +  e.getStackTrace());
		}
	}
	

	public static void main(String[] args)
	{
		new JDBCExam();
	}
}