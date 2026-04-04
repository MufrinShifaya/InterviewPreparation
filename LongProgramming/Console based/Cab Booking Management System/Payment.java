public class Payment {
    int bookingId;
    String method;
    int amount;
    int paymentId;

    Payment(int bookingId, String method, int amount, int paymentId) {
        this.bookingId = bookingId;
        this.method = method;
        this.amount = amount;
        this.paymentId = paymentId;

    }
        public int getbookingId() {
            return bookingId;
        }

        public void setbookingId(int bookingId){
            this.bookingId = bookingId;
        }

        public String getmethod () {
            return method;
        }

        public void setmethod(String method){
            this.method = method;
        }

        public int getamount() {
            return amount;
        }

        public void setamount(int amount){
            this.amount = amount;
        }

        public int getpaymentId() {
            return paymentId;
        }

        public void setpaymentId(int paymentId){
            this.paymentId = paymentId;
        }
    }

