package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDB {
	
	Connection connection;
	Statement statement;
	ResultSet resultSet;
	
	String driverName = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://13.114.123.192/rhythmTyping?characterEncoding=euckr";

	String user = "root";
	String password = "0228";
	
	
	public boolean checkInfo(String inputId,String inputPw,String inputName) {
		try
		{	

//			// �� �ε�
			Class.forName(driverName);
//			// �� ����
//			connection = DriverManager.getConnection(url, user, password);
//			
//			System.out.println("����");
			
			String queryString = "SELECT * FROM userInfo where id = '"+inputId+"'";

			// �� ���� [Connection]
			connection = DriverManager.getConnection(url, user, password);

			// �� ���� [Statement]
			statement = connection.createStatement();
			
			// �� ���� [CRUD]
			resultSet = statement.executeQuery(queryString);
			
			// �÷� ���� ��������
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

			// �÷� ���
			//System.out.println(resultSetMetaData.getColumnName(1));
			while (resultSet.next())
			{
				String dbId=resultSet.getString("id");
				String dbName=resultSet.getString("name");
				String dbPw=resultSet.getString("pw");
				
				if(inputId.equals(dbId) && inputPw.equals(dbPw) && inputName.equals(dbName)) {
					return true;
				}
			}
			//System.out.println(resultSetMetaData.getColumnName(2));
			
			
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
		return false;
	}
	
	public boolean checkJoinInfo(String inputId,String inputPw,String inputName) {
		try
		{	

//			// �� �ε�
			Class.forName(driverName);
//			// �� ����
//			connection = DriverManager.getConnection(url, user, password);
//			
//			System.out.println("����");
			
			String queryString = "SELECT * FROM userInfo where id = '"+inputId+"'";

			// �� ���� [Connection]
			connection = DriverManager.getConnection(url, user, password);

			// �� ���� [Statement]
			statement = connection.createStatement();
			
			// �� ���� [CRUD]
			resultSet = statement.executeQuery(queryString);
			
			// �÷� ���� ��������
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

			// �÷� ���
			//System.out.println(resultSetMetaData.getColumnName(1));
			while (resultSet.next())
			{
				String dbId=resultSet.getString("id");
				String dbName=resultSet.getString("name");
				String dbPw=resultSet.getString("pw");
				
				if(inputId.equals(dbId)) {
					return false;
				}
			}
			
			queryString = "INSERT INTO userInfo VALUES ('" + inputId + "', '" + inputName + "', '" + inputPw + "')";
			// �� ���� [Connection]
			
			int resultSet = statement.executeUpdate(queryString);
			//System.out.println(resultSetMetaData.getColumnName(2));
			
			
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
		return true;
		
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
	

}
