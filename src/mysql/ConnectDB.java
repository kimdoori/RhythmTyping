package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import javax.swing.JOptionPane;

import screen.RhythmTyping;
import screen.SelectMusicPanel;

public class ConnectDB {

	Connection connection;
	Statement statement;
	ResultSet resultSet;

	String driverName = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://13.114.123.192/rhythmTyping?characterEncoding=euckr";

	String user = "root";
	String password = "0228";
	public int rowcount;
	public ConnectDB() {
		super();
		try {
			Class.forName(driverName);

			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();

		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "�������̸� �������ּ���.", "��Ʈ��ũ ���� ����", JOptionPane.ERROR_MESSAGE);

			//System.out.println("[�ε� ����]\n" + e.getStackTrace());
		//	System.out.println(e.toString());

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "�������̸� �������ּ���.", "��Ʈ��ũ ���� ����", JOptionPane.ERROR_MESSAGE);

		//	System.out.println("[���� ����]\n" + e.getStackTrace());
		//	System.out.println(e.toString());
		}
		//getSongRecord();
	}

	@SuppressWarnings("unused")
	public String checkInfo(String inputId, String inputPw, String inputName) {
		try {

			String queryString = "SELECT * FROM userInfo where id = '" + inputId + "'";

			// �� ���� [CRUD]
			resultSet = statement.executeQuery(queryString);

			// �÷� ���� ��������
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

			// �÷� ���
			// System.out.println(resultSetMetaData.getColumnName(1));
			while (resultSet.next()) {
				String dbId = resultSet.getString("id");
				String dbName = resultSet.getString("name");
				String dbPw = resultSet.getString("pw");

				
				if (!inputPw.equals(dbPw))
					return "�ùٸ� ��й�ȣ�� �Է����ּ���.";
				else if (!inputName.equals(dbName))
					return "�ùٸ� �̸��� �Է����ּ���.";
				else {
					RhythmTyping.playID=dbId;
					getSongRecord();

				}
				return "true";

			}
			// System.out.println(resultSetMetaData.getColumnName(2));

		} catch (SQLException e) {
			//JOptionPane.showMessageDialog(null, "�������̸� �������ּ���.", "��Ʈ��ũ ���� ����", JOptionPane.ERROR_MESSAGE);

		//	System.out.println("[���� ����]\n" + e.getStackTrace());
		//	System.out.println(e.toString());

		}
		return "�������� �ʴ� ���̵��Դϴ�.";
	}

	@SuppressWarnings("unused")
	public boolean checkJoinInfo(String inputId, String inputPw, String inputName) {
		try {

			String queryString = "SELECT * FROM userInfo where id = '" + inputId + "'";

			// �� ���� [CRUD]
			resultSet = statement.executeQuery(queryString);

			// �÷� ���� ��������
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

			// �÷� ���
			// System.out.println(resultSetMetaData.getColumnName(1));
			while (resultSet.next()) {
				String dbId = resultSet.getString("id");
				String dbName = resultSet.getString("name");
				String dbPw = resultSet.getString("pw");

				if (inputId.equals(dbId)) {
					return false;
				}
			}

			queryString = "INSERT INTO userInfo VALUES ('" + inputId + "', '" + inputName + "', '" + inputPw + "')";
			int resultSet = statement.executeUpdate(queryString);

			String[] songTable= {"gangnamstyle","D","redflavor","cracked"};
			
			for(int i=0;i<songTable.length;i++) {
				queryString = "INSERT INTO "+songTable[i]+" VALUES ('" + inputId + "', 0)";
				resultSet = statement.executeUpdate(queryString);
			}
			

			
		} catch (SQLException e) {
		//	JOptionPane.showMessageDialog(null, "�������̸� �������ּ���.", "��Ʈ��ũ ���� ����", JOptionPane.ERROR_MESSAGE);

		//	System.out.println("[���� ����]\n" + e.getStackTrace());
		//	System.out.println(e.toString());

		}
		return true;

	}

	// TODO:�뷡���ڵ� ��������
	public void getSongRecord() {
		try {

			String[] queryString = { "SELECT * FROM gangnamstyle ORDER BY score DESC", "SELECT * FROM D ORDER BY score DESC",
					"SELECT * FROM redflavor ORDER BY score DESC", "SELECT * FROM cracked ORDER BY score DESC" };
//			gangnamScore;
//			public static String[] DScore;
//			public static String[] redflavorScore;
//			public static String[] crackedScore;
			
			resultSet = statement.executeQuery(queryString[0]);
			resultSet.last();      
	        rowcount = resultSet.getRow();
			RhythmTyping.score= new int[4][rowcount];
			RhythmTyping.user= new String[4][rowcount];
			for(int i=0;i<queryString.length;i++) {
				resultSet = statement.executeQuery(queryString[i]);

		        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();		
				int j=0;
				while (resultSet.next()) {
					RhythmTyping.score[i][j]=resultSet.getInt("score");

					RhythmTyping.user[i][j]=resultSet.getString("id");	
					if(RhythmTyping.playID!=null && RhythmTyping.playID.equals(RhythmTyping.user[i][j])) {
						RhythmTyping.playScore[i]=RhythmTyping.score[i][j];
					}
					j++;

				}
			   // Arrays.sort(RhythmTyping.score[i]);
			    

			}
			
		} catch (SQLException e) {
		//	JOptionPane.showMessageDialog(null, "�������̸� �������ּ���.", "��Ʈ��ũ ���� ����", JOptionPane.ERROR_MESSAGE);

			//System.out.println("[���� ����]\n" + e.getStackTrace());
			//System.out.println(e.toString());

		}

	}

	
	// TODO: RESULT������Ʈ
	public boolean updateScore(String userScore) {
		try {
			String songName="";

			switch(SelectMusicPanel.songIndex) {
			case 0:
				songName="gangnamstyle";
				break;
			case 1:
				songName="D";
				break;
			case 2:
				songName="redflavor";
				break;
			case 3:
				songName="cracked";
				break;				
			}
			String queryString = "SELECT score FROM "+songName+" where id = '" + RhythmTyping.playID + "'";

			resultSet = statement.executeQuery(queryString);

			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

			while (resultSet.next()) {
				String score = resultSet.getString("score");

				if(Integer.parseInt(score)<Integer.parseInt(userScore)) {
					queryString = "UPDATE "+songName+" SET score ='" + userScore + "' WHERE id = '" + RhythmTyping.playID + "'";

					int resultSet = statement.executeUpdate(queryString);
					// System.out.println(resultSetMetaData.getColumnName(2));
					
					RhythmTyping.playScore[SelectMusicPanel.songIndex]=Integer.parseInt(userScore);
				}
		
			}

			

		} catch (SQLException e) {
		//	JOptionPane.showMessageDialog(null, "�������̸� �������ּ���.", "��Ʈ��ũ ���� ����", JOptionPane.ERROR_MESSAGE);

		//	System.out.println("[���� ����]\n" + e.getStackTrace());
		//	System.out.println(e.toString());

		}
		return true;

	}

	public void closeDatabase() {
		try {
			if (connection != null) {
				connection.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			System.out.println("[�ݱ� ����]\n" + e.getStackTrace());
		}
	}

}
