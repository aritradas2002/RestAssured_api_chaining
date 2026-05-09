package Pojo;

public class Bookingdates_pojo {


        private String checkin;
        private String checkout;



        // Constructor
        public Bookingdates_pojo(String checkin, String checkout) {

            this.checkin = checkin;
            this.checkout = checkout;
        }



        // Getter
        public String getCheckin() {
            return checkin;
        }



        // Getter
        public String getCheckout() {
            return checkout;
        }

}
