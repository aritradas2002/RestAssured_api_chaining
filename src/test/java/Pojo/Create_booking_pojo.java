package Pojo;

public class Create_booking_pojo {
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;

    private Bookingdates_pojo bookingdates;

    private String additionalneeds;



    // Constructor
    public Create_booking_pojo(String firstname,
                          String lastname,
                          int totalprice,
                          boolean depositpaid,
                          Bookingdates_pojo bookingdates,
                          String additionalneeds) {

        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates;
        this.additionalneeds = additionalneeds;
    }



    // Getters

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public Bookingdates_pojo getBookingdates() {
        return bookingdates;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

}
