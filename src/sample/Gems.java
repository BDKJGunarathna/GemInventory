package sample;



public class Gems {


     String gemId;
    String gemDescription;
     String gemWeight;
     String gemShape;
   String gemDimension;
    String gemUnitsInStock;
    String gemReorderLevel;
     String gemCost;

    public Gems(String gemId,String gemDescription,String gemWeight,String gemShape,String gemDimension,  String gemUnitsInStock, String gemReorderLevel,String gemCost){
        this.gemId=gemId;
        this.gemDescription=gemDescription;
        this.gemWeight=gemWeight;
        this.gemShape=gemShape;
        this.gemDimension=gemDimension;
        this.gemUnitsInStock=gemUnitsInStock;
        this.gemReorderLevel=gemReorderLevel;
        this.gemCost=gemCost;
    }

    public String getGemId() {
        return gemId;
    }
    public void setGemId(String gemId) {
        this.gemId = gemId;
    }

    public String getGemDescription() {
        return gemDescription;
    }
    public void setGemDescription(String description ) {
        this.gemDescription =description ;
    }

    public String getGemWeight() {
        return gemWeight;
    }

    public void setWeight(String weight) {
        this.gemWeight = weight;
    }

    public String getGemShape() {
        return gemShape;
    }
    public void setGemShape(String shape) {
        this.gemShape = shape;
    }

    public String getGemDimension() {
        return gemDimension;
    }
    public void setGemDimension(String dimension) {
        this.gemDimension = dimension;
    }

    public   String getGemUnitsInStock() {
        return gemUnitsInStock;
    }
    public void setGemUnitsInStock(  String unitsInStocks) {
        this.gemUnitsInStock = unitsInStocks;
    }

    public  String getGemReorderLevel() {
        return gemReorderLevel;
    }
    public void setGemReorderLevel( String reorderLevel) {
        this.gemReorderLevel = reorderLevel;
    }

    public String getGemCost() {
        return gemCost;
    }
    public void setGemCost(String cost) {
        this.gemCost= cost;
    }



}
