package supplierManagement;



public class GetGemstonesToOrder {
    private int gem_id;
    private String gem_description;
    private double reorder_level;
    private double price_of_each;

    public GetGemstonesToOrder(int gem_id, String gem_description, double reorder_level, double price_of_each) {
        this.gem_id = gem_id;
        this.gem_description = gem_description;
        this.reorder_level = reorder_level;
        this.price_of_each = price_of_each;
    }

    public int getGem_id() {
        return gem_id;
    }

    public void setGem_id(int gem_id) {
        this.gem_id = gem_id;
    }

    public String getGem_description() {
        return gem_description;
    }

    public void setGem_description(String gem_description) {
        this.gem_description = gem_description;
    }

    public double getReorder_level() {
        return reorder_level;
    }

    public void setReorder_level(double reorder_level) {
        this.reorder_level = reorder_level;
    }

    public double getPrice_of_each() {
        return price_of_each;
    }

    public void setPrice_of_each(double price_of_each) {
        this.price_of_each = price_of_each;
    }
}