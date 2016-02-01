package reservation;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainFrame extends Frame implements ActionListener,WindowListener,KeyListener{

	ReservationControl reservationControl;
	
	Panel panelNorth; //上部パネル
	Panel panelNorthSub1;
	Panel panelNorthSub2;
	Panel panelNorthSub3;
	
	Panel panelMid;	//中央パネル
	Panel panelSouth; //下部パネル
	
	Button buttonLog;
	Button buttonExplanation;
	Button buttonVacancy;
	Button buttonReservation;
	Button buttonConfirm;
	Button buttonCancel;
	
	ChoiceFacility choiceFacility;
	TextField tfYear,tfMonth,tfDay;
	TextArea textMessage;
	
	
	public MainFrame(ReservationControl reservationControl)
throws HeadlessException{	
		this.reservationControl = reservationControl;
		addWindowListener(this);
		addKeyListener(this);
		
		//ボタン生成
		buttonLog = new Button("ログイン");
		buttonExplanation = new Button("施設概要");
		buttonVacancy = new Button("空き状況照会");
		buttonReservation = new Button("新規予約");
		buttonConfirm = new Button("予約確認");
		buttonCancel = new Button("予約キャンセル");
		
		//設備チョイスボックス生成
		choiceFacility = new ChoiceFacility();
		tfYear = new TextField("",4);
		tfMonth = new TextField("",2);
		tfDay = new TextField("",2);
		
		//レイアウトマネージャーにBorderLayout設定
		setLayout( new BorderLayout());
		
		//パネル上段
		panelNorthSub1 = new Panel();
		panelNorthSub1.add(new Label("施設予約システム"));
		panelNorthSub1.add(buttonLog);
		
		panelNorthSub2 = new Panel();
		panelNorthSub2.add(new Label("施設 "));
		panelNorthSub2.add(choiceFacility);
		panelNorthSub2.add(new Label(" "));
		panelNorthSub2.add(buttonExplanation);
		
		panelNorthSub3 = new Panel();
		panelNorthSub3.add(new Label(" "));
		panelNorthSub3.add(tfYear);
		panelNorthSub3.add(new Label("年"));
		panelNorthSub3.add(tfMonth);
		panelNorthSub3.add(new Label("月"));
		panelNorthSub3.add(tfDay);
		panelNorthSub3.add(new Label("日 "));
		panelNorthSub3.add( buttonVacancy);
		
		
		textMessage = new TextArea( 20, 80);
		textMessage.setEditable(false);
		panelNorthSub3.add(textMessage);
		
		panelNorth = new Panel(new BorderLayout());
		panelNorth.add(panelNorthSub1, BorderLayout.NORTH);
		panelNorth.add(panelNorthSub2, BorderLayout.CENTER);
		panelNorth.add(panelNorthSub3, BorderLayout.SOUTH);

		add(panelNorth,BorderLayout.NORTH);
			
		panelMid = new Panel();
		
		//パネル下段
		panelSouth = new Panel();
		panelSouth.add(buttonReservation);
		panelSouth.add(buttonConfirm);
		panelSouth.add(buttonCancel);
		
		add(panelSouth, BorderLayout.CENTER);
		
		
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		System.exit(0);
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
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
		String result = new String();
		textMessage.setText("");
		if ( e.getSource() == buttonVacancy){ // 空き状況確認ボタン
			result = reservationControl.getReservationOn(choiceFacility.getSelectedItem(), tfYear.getText(), tfMonth.getText(), tfDay.getText());
		}
		textMessage.setText(result);



		
	}

	
}
