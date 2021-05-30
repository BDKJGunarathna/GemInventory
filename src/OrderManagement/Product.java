package OrderManagement;

public class Product {
        //declare variables
        private int pId;
        private String pName;
        private String pType;
        private double pPrice;
        private int pQty;

        //constructor
        public Product(int pId, String pName, String pType, double pPrice, int pQty) {
            this.pId = pId;
            this.pName = pName;
            this.pType = pType;
            this.pPrice = pPrice;
            this.pQty = pQty;
        }

        //getters and setters

        public int getPId() {

            return pId;
        }

        public String getPName() {

            return pName;
        }

        public String getPType() {

            return pType;
        }

        public double getPPrice() {

            return pPrice;
        }

        public int getPQty() {

            return pQty;
        }
    }


