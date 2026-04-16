package bms.product;

public class Notebook extends Product {

    private int pageCount;
    private String paperType;
    private String size;
    private String manufacturer;

    public Notebook(int pageCount, String paperType, String size, String manufacturer,
                    String id, String name, double costPrice, double salePrice, int quantity, String unit, String origin) {
        super(id, name, costPrice, salePrice, quantity, unit, origin);
        this.pageCount = pageCount;
        this.paperType = paperType;
        this.size = size;
        this.manufacturer = manufacturer;
    }

    // --- GETTERS & SETTERS ---
    public int getPageCount() { return pageCount; }
    public void setPageCount(int pageCount) { this.pageCount = pageCount; }

    public String getPaperType() { return paperType; }
    public void setPaperType(String paperType) { this.paperType = paperType; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public String getManufacturer() { return manufacturer; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }
}