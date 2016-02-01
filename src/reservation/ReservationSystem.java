package reservation;

public class ReservationSystem {

	public static void main(String[] args) {
		//めも
		//できる：ログイン、予約
		//できない：キャンセル、予約確認

		ReservationControl reservationControl = new ReservationControl();
		MainFrame mainFrame = new MainFrame(reservationControl);
		 mainFrame.setBounds( 5, 5, 660, 500 );
		 mainFrame.setVisible(true);

	}

}
