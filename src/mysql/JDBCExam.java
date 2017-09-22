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

//			// �� �ε�
			Class.forName(driverName);
//			// �� ����
//			connection = DriverManager.getConnection(url, user, password);
//			
//			System.out.println("����");
			
			String queryString = "SELECT * FROM table1";

			// �� ���� [Connection]
			connection = DriverManager.getConnection(url, user, password);

			// �� ���� [Statement]
			statement = connection.createStatement();
			
			// �� ���� [CRUD]
			resultSet = statement.executeQuery(queryString);
			
			// �÷� ���� ��������
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

			// �÷� ���
			System.out.println(resultSetMetaData.getColumnName(1));

			while (resultSet.next())
			{
				System.out.println(resultSet.getString("name"));
			}
			
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("[�ε� ����]\n" + e.getStackTrace());
			System.out.println(e.toString());
		}
		catch (SQLException e)
		{
			System.out.println("[���� ����]\n" +  e.getStackTrace());
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
			System.out.println("[�ݱ� ����]\n" +  e.getStackTrace());
		}
	}
	

	public static void main(String[] args)
	{
		new JDBCExam();
	}
}