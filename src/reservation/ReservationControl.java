package reservation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReservationControl {
	static Connection sqlCon;
	static Statement sqlStmt;
	static String userid = "学籍番号"; //ユーザID
	static String password = "学籍番号"; //パスワード
	public static void main(String args[]){
		ReservationControl reservationControl = new ReservationControl();
		MainFrame mainFrame = new MainFrame(reservationControl);
			
		mainFrame.setBounds(5, 5, 655, 490);
		mainFrame.setVisible(true);	
	}
	
	private static void connectDB(){
		  try{
		      // ドライバクラスをロード
		      Class.forName("org.gjt.mm.mysql.Driver"); // MySQLの指定

		      // データベースへのURLを作成
		      String url = "jdbc:mysql://localhost?useUnicode=true&characterEncoding=utf8";
		      // ユーザIDとパスワードを設定して接続
		      sqlCon = DriverManager.getConnection(url,userid, password);

		      // ステートメントオブジェクトを生成
		      sqlStmt = sqlCon.createStatement();
		    } catch (Exception e) {
			      e.printStackTrace();
		    }
		}
	
	private static void closeDB(){
		try{
			sqlStmt.close();
			sqlCon.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String selectReservation(){

		String res = "";

		//(1) MySQLを使用する準備
		connectDB();

		//(2) MySQLの操作(SELECT文の実行)
		try {
		      // 予約情報を取得するクエリ
		      String sql = "SELECT * FROM practice.reservation;";
		      // クエリーを実行して結果セットを取得
		      ResultSet rs = sqlStmt.executeQuery(sql);
		      // 検索結果からレコードを１つずつ取り出し，3つのカラムの値を表示
		      while(rs.next()){
		    	  String student = rs.getString("student_id");
		    	  String facility_name = rs.getString("facility_name");
		    	  String date = rs.getString("date");
		    	  res += date + "  " + student + "  " + facility_name +"\n" ;
		      }
		} catch (Exception e) {
			res = "データ検索においてエラーが生じました";
		}

		//(3) MySQへの接続切断
		closeDB();

		return res;
	}
	
	//指定した日,施設の 空き状況(というか予約状況)
	public String getReservationOn( String facility, String ryear_str, String rmonth_str, String rday_str){
		String res = "";
		// 年月日が数字かどうかををチェックする処理
		try {
			int ryear = Integer.parseInt( ryear_str);
			int rmonth = Integer.parseInt( rmonth_str);
			int rday = Integer.parseInt( rday_str);
		} catch(NumberFormatException e){
			res ="年月日には数字を指定してください";
			return res;
		}
		res = facility + " 予約状況\n\n";

		// 月と日が一桁だったら,前に0をつける処理
		if (rmonth_str.length()==1) {
			rmonth_str = "0" + rmonth_str;
		}
		if ( rday_str.length()==1){
			rday_str = "0" + rday_str;
		}
		//SQL で検索するための年月日のフォーマットの文字列を作成する処理
		String rdate = ryear_str + "-" + rmonth_str + "-" + rday_str;

		//(1) MySQL を使用する準備
		connectDB();

		//(2) MySQLの操作(SELECT文の実行)
		try {
			// 予約情報を取得するクエリ
			String sql = "SELECT * FROM db_reservation.reservation WHERE date ='" + rdate + "' AND facility_name = '"+ facility +"' ORDER BY start_time;";
			// クエリーを実行して結果セットを取得
			ResultSet rs = sqlStmt.executeQuery(sql); // 検索結果から予約状況を作成
			boolean exist = false;
			while(rs.next()){
				String start = rs.getString("start_time");
				String end = rs.getString("end_time");
				res += " " + start + " -- " + end + "\n";
				exist = true;
			}

			if ( !exist){ //予約が1つも存在しない場合の処理
				res = "予約はありません";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		//(3) MySQ への接続切断
		closeDB();
		return res;
	}


	
	
}
