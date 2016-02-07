package reservation;

public class ReservationSystem {

	public static void main(String[] args) {
		//めも
		//とりあえず完成。
		ReservationControl reservationControl = new ReservationControl();
		MainFrame mainFrame = new MainFrame(reservationControl);
		 mainFrame.setBounds( 5, 5, 660, 500);
		 mainFrame.setVisible(true);

	}

}
