package OrderManagement;

public class Product {

        private int pId;
        private String pName;
        private String pType;
        private double pPrice;
        private int pQty;

        public Product(int pId, String pName, String pType, double pPrice, int pQty) {
            this.pId = pId;
            this.pName = pName;
            this.pType = pType;
            this.pPrice = pPrice;
            this.pQty = pQty;
        }


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


