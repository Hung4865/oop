package bms.product;

public class Stationery extends Product {

    private String type;
    private String manufacturer;
    private String material;

    public Stationery(String type, String manufacturer, String material,
                      String id, String name, double costPrice, double salePrice, int quantity, String unit, String origin) {
        super(id, name, costPrice, salePrice, quantity, unit, origin);
        this.type = type;
        this.manufacturer = manufacturer;
        this.material = material;
    }

    // --- GETTERS & SETTERS ---
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getManufacturer() { return manufacturer; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }
}