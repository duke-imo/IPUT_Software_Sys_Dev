package dbInit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class initReservationDB {
	static String userid = "puser";
	static String password = "1234";
	static Connection sqlCon;
	static Statement sqlStmt;
	
	private static void connectDB() {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			String url = "jdbc:mysql://localhost?useUnicode=true&characterEncoding=SJIS";
			sqlCon = DriverManager.getConnection(url, userid, password);
			sqlStmt = sqlCon.createStatement();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void closeDB() {
		try {
			sqlStmt.close();
			sqlCon.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		String[][] userData = new String[][] {
			{"TK190000", "国際太郎", "IA13A", "株式会社ABC", "pass0000"},
			{"TK190001", "国際花子", "IS13A", "国際市役所", "pass0001"},
			{"TK190002", "世界一郎", "IR13A", "国際ロボティクス", "pass0002"}
		};
		connectDB();
		
		try {
			String sql = "DELETE FROM db_reservation.user;";
			int rsUpdate = sqlStmt.executeUpdate(sql);
			System.out.println(rsUpdate + "件のデータを削除しました。");
			for (int i=0; i<userData.length; i++) {
				sql = "INSERT INTO db_reservation.user VALUES('" + userData[i][0] + "'";
				for(int j=1; j<5; j++) {
					sql = sql + ",'" + userData[i][j] + "'";
				}
				sql = sql + ");";
				System.out.println(sql);
				rsUpdate = sqlStmt.executeUpdate(sql);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		String[][] reservationData = new String[][] {
			{"371", "TK190000","2024-04-08 12:00:00", "2024-07-31", "10:00:00", "12:00:00"},
			{"251", "TK190001","2024-04-09 20:00:00", "2024-04-16", "09:30:00", "21:15:00"},
			{"354", "TK190002","2024-04-10 13:00:00", "2024-05-20", "13:00:00", "15:00:00"}
		};
		try {
			String sql = "DELETE FROM db_reservation.reservation;";
			int rsUpdate = sqlStmt.executeUpdate(sql);
			System.out.println(rsUpdate + "件のデータを削除しました。");
			for (int i=0; i<reservationData.length; i++) {
				sql = "INSERT INTO db_reservation.reservation( facility_id, user_id, date, day, start_time, end_time)";
				sql = sql + "VALUES('" + reservationData[i][0] + "'";
				for(int j=1; j<6; j++) {
					sql = sql + ",'" + reservationData[i][j] + "'";
				}
				sql = sql + ");";
				System.out.println(sql);
				rsUpdate = sqlStmt.executeUpdate(sql);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		String[][] facilityData = new String[][] {
			{ "371", "371 Traning room",          "08:00:00", "21:30:00", "371実習室 座席数： 56"},
			{ "373", "373 Traning room",          "08:00:00", "21:30:00", "373実習室 座席数： 52"},
			{ "374", "374 Traning room",          "08:00:00", "21:30:00", "374実習室 座席数： 56"},
			{ "376", "376 Traning room",          "08:00:00", "21:30:00", "376実習室 座席数： 52"},
			{ "361", "361 Traning room",          "08:00:00", "21:30:00", "361実習室 座席数： 56"},
			{ "363", "363 Traning room",          "08:00:00", "21:30:00", "363実習室 座席数： 56"},
			{ "364", "364 Workshop",              "09:00:00", "21:15:00", "364工作室 座席数： 50"},
			{ "351", "351 Lecture room",          "08:00:00", "21:30:00", "351講義室 座席数:102"},
			{ "353", "353 Lecture room",          "08:00:00", "21:30:00", "353講義室 座席数： 46"},
			{ "354", "354 IoT Lab",               "09:00:00", "21:15:00", "354IoTLab 座席数： 53"},
			{ "341", "341 Lecture room",          "08:00:00", "21:30:00", "341講義室 座席数： 55"},
			{ "342", "342 Lecture room",          "08:00:00", "21:30:00", "342講義室 座席数： 55"},
			{ "345", "345 Lecture room",          "08:00:00", "21:30:00", "345講義室 座席数： 55"},
			{ "311", "311 Lecture room",          "08:00:00", "21:30:00", "311講義室 座席数:183"},
			{ "313", "313 PC room",               "09:00:00", "21:15:00", "313PCRoom 座席数： 80"},
			{ "291", "291 Motion Capture Studio", "09:00:00", "21:15:00", "291 MotionCaptureStudio"},
			{ "277", "277 PC room",               "09:00:00", "21:15:00", "277PCRoom 座席数： 80"},
			{ "251", "251 Robot Lab",             "09:00:00", "21:15:00", "251RoboLab 座席数： 30"},
			{ "257", "257 PC room",               "09:00:00", "21:15:00", "257PCRoom 座席数： 80"}
		};
        
        try {
	        String sql = "DELETE FROM db_reservation.facility;";
			int rsUpdate = sqlStmt.executeUpdate(sql);
			System.out.println(rsUpdate + "件のデータを削除しました。");
			for (int i=0; i<facilityData.length; i++) {
				sql = "INSERT INTO db_reservation.facility VALUES('" + facilityData[i][0] + "'";
				for(int j=1; j<5; j++) {
					sql = sql + ",'" + facilityData[i][j] + "'";
				}
				sql = sql + ");";
				System.out.println(sql);
				rsUpdate = sqlStmt.executeUpdate(sql);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		closeDB();
		System.out.println("Tableの初期化が完了しました！");
	}
}

	
