package reservation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReservationControl {
	String reservation_userid;
	private boolean flagLogin;

	//ログインしていればtrue
	ReservationControl(){
		flagLogin = false;
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
		//connectDB();
		MySQL mysql = new MySQL();

		//(2) MySQLの操作(SELECT文の実行)
		try {
			// 予約情報を取得するクエリ
			ResultSet rs = mysql.getReservation(rdate, facility);
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
		
		return res;
	}
	
	//////ログイン・ログアウトボタンの処理
	public String loginLogout( MainFrame frame){
		String res=""; //結果を入れる変数
		if ( flagLogin){ //ログアウトを行う処理
			flagLogin = false;
			frame.buttonLog.setLabel(" ログイン "); //ログインを行う処理
		} else {
			//ログインダイアログの生成と表示
			LoginDialog ld = new LoginDialog(frame);
			ld.setVisible(true);
			ld.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
			//IDとパスワードの入力がキャンセルされたら,空文字列を結果として終了
			if ( ld.canceled){
				return "";
			}

			//ユーザIDとパスワードが入力された場合の処理
			//ユーザIDは他の機能のときに使用するのでメンバー変数に代入
			reservation_userid = ld.tfUserID.getText();
			//パスワードはここでしか使わないので,ローカル変数に代入
			String password = ld.tfPassword.getText();
		}

		return res;
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







}


