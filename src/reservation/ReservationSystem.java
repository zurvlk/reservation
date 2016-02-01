package reservation;

public class ReservationSystem {

	public static void main(String[] args) {
		//めも
		//できる：ログイン、予約、予約確認
		//できない：キャンセル

		ReservationControl reservationControl = new ReservationControl();
		MainFrame mainFrame = new MainFrame(reservationControl);
		 mainFrame.setBounds( 5, 5, 660, 500 );
		 mainFrame.setVisible(true);

	}

}
