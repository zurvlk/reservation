package reservation;

import java.awt.Dialog;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class ReservationDialog extends Dialog implements ActionListener, WindowListener,ItemListener{

	boolean canceled;		//キャンセルされたら true 予約実行ボタンのときは false
	// パネル
	Panel panelNorth;
	Panel panelMid;
	Panel panelSouth;
	// 入力用コンポーネント
	ChoiceFacility choiceFacility;	// 施設選択用ボックス
	TextField tfYear,tfMonth,tfDay;	// 年月日のテキストフィールド
	ChoiceHour startHour;			// 予約開始時刻　時の選択用ボックス
	ChoiceMinute startMinute;		// 予約開始時刻　分の選択用ボックス
	ChoiceHour endHour;				// 予約終了時刻 　時の選択用ボックス
	ChoiceMinute endMinute;			// 予約終了時刻　分の選択用ボックス
	// ボタン
	Button buttonOK;
	Button buttonCancel;
	public ReservationDialog(Frame owner) {

		//基底クラス(Dialog)のコンストラクタを呼び出す
		super(owner, "新規予約", true);

		//キャンセルは初期値ではtrueとしておく
		canceled = true;

		//施設選択ボックスの生成
		choiceFacility = new ChoiceFacility();
		//テキストフィールドの生成　年月日
		tfYear = new TextField("",4);
		tfMonth = new TextField("",2);
		tfDay = new TextField("",2);
		//開始時刻　時分選択ボックスの生成
		startHour = new ChoiceHour();
		startMinute = new ChoiceMinute();
		//終了時刻　自分選択ボックスの生成
		endHour = new ChoiceHour();
		endMinute = new ChoiceMinute();

		//ボタンの生成
		buttonOK = new Button("予約実行");
		buttonCancel = new Button("キャンセル");

		//パネルの生成
		panelNorth = new Panel();
		panelMid = new Panel();
		panelSouth = new Panel();

		//上部パネルに施設選択ボックス，年月日入力欄を追加
		panelNorth.add( new Label("施設　"));
		panelNorth.add(choiceFacility);
		panelNorth.add( new Label("予約日 "));
		panelNorth.add(tfYear);
		panelNorth.add(new Label("年"));
		panelNorth.add(tfMonth);
		panelNorth.add(new Label("月"));
		panelNorth.add(tfDay);
		panelNorth.add(new Label("日　"));

		//中央パネルに予約　開始時刻，終了時刻入力用選択ボックスを追加
		panelMid.add( new Label("予約時間　"));
		panelMid.add( startHour);
		panelMid.add( new Label("時"));
		panelMid.add( startMinute);
		panelMid.add( new Label("分　〜　"));
		panelMid.add( endHour);
		panelMid.add( new Label("時"));
		panelMid.add( endMinute);
		panelMid.add( new Label("分"));

		//下部パネルに2つのボタンを追加
		panelSouth.add(buttonCancel);
		panelSouth.add( new Label("　　　　"));
		panelSouth.add(buttonOK);

		// ReservationDialogをBorderLayoutに設定し，3つのパネルを追加
		setLayout(new BorderLayout());
		add(panelNorth, BorderLayout.NORTH);
		add(panelMid,BorderLayout.CENTER);
		add(panelSouth, BorderLayout.SOUTH);

		// ウィンドウリスナを追加
		addWindowListener(this);
		// ボタンにアクションリスナを追加
		buttonOK.addActionListener(this);
		buttonCancel.addActionListener(this);
		//施設選択ボックス，時・分選択ボックスそれぞれに項目リスナを追加
		choiceFacility.addItemListener(this);
		startHour.addItemListener(this);
		startMinute.addItemListener(this);
		endHour.addItemListener(this);
		endMinute.addItemListener(this);

		// 選択されている施設によって，時刻の範囲を設定する．
		resetTimeRange();

		// 大きさの設定，ウィンドウのサイズ変更不可の設定
		this.setBounds( 100, 100, 500, 120);
		setResizable(false);
	}



	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		if ( e.getSource()==choiceFacility){
			// 施設が変更されたら，施設に応じた範囲を設定
			resetTimeRange();
		} else if ( e.getSource()==startHour){
			//開始時刻が変更されたら，終了時刻入力欄の時を開始時刻に合わせる
			int start = Integer.parseInt( startHour.getSelectedItem());
			endHour.resetRange(start, Integer.parseInt( endHour.getLast()));
		} else if ( e.getSource()==endHour){
			//終了時刻が変更され，最後の時刻の場合，分は 00分に設定
			if ( endHour.getSelectedIndex()==endHour.getItemCount()-1){
				endMinute.select(0);
			}
		} if( e.getSource()==endMinute){
			//終了時刻（分）が変更され，時が最後の場合，分は 00分に設定
			if ( endHour.getSelectedIndex()==endHour.getItemCount()-1){
				endMinute.select(0);
			}
		}
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		setVisible(false);
		dispose();
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		if ( e.getSource() == buttonCancel){
			setVisible(false);
			dispose();
		} else if ( e.getSource() == buttonOK){
			canceled = false;
			setVisible(false);
			dispose();
		}
		
	}
	private void resetTimeRange() {
		// 選択されている施設によって，時刻の範囲を設定する．
		if ( choiceFacility.getSelectedIndex()==0){
			// 最初の施設(小ホールのとき)の設定
			startHour.resetRange(10, 20);
			endHour.resetRange(10, 21);
		} else {
			// 小ホール以外の設定
			startHour.resetRange(9, 19);
			endHour.resetRange(9, 20);
		}
	}
	
	private boolean checkReservationDate( int y, int m, int d){
		// 予約日
		Calendar dateR = Calendar.getInstance();
		dateR.set( y, m-1, d);	// 月から1引かなければならないことに注意！

		// 今日の１日後
		Calendar date1 = Calendar.getInstance();
		date1.add(Calendar.DATE, 1);

		// 今日の３ヶ月後（90日後)
		Calendar date2 = Calendar.getInstance();
		date2.add(Calendar.DATE, 90);

		if ( dateR.after(date1) && dateR.before(date2)){
			return true;
		}
		return false;
	}
	

}
