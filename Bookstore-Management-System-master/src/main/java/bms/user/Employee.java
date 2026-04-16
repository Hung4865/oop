package bms.user;

public class Employee extends Person {

    private String position;
    private String employmentType;

    public Employee(String position, String employmentType, String id, String name, String birth, String address, String phoneNumber, String email) {
        super(id, name, birth, address, phoneNumber, email);
        this.position = position;
        this.employmentType = employmentType;
    }

    // --- GETTERS & SETTERS ---
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }
}