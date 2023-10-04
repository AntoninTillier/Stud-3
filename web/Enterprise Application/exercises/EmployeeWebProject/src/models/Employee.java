package models;

public class Employee {
    private String idE;
    private String firstNameE;
    private String lastNameE;
    private String roleE;

    public Employee() { }

    public String getIdE() {
        return this.idE;
    }

    public void setIdE(String idE) {
        this.idE = idE;
    }

    public String getFirstNameE() {
        return this.firstNameE;
    }

    public void setFirstNameE(String firstNameE) {
        this.firstNameE = firstNameE;
    }

    public String getLastNameE() {
        return this.lastNameE;
    }

    public void setLastNameE(String lastNameE) {
        this.lastNameE = lastNameE;
    }

    public String getRoleE() {
        return this.roleE;
    }

    public void setRoleE(String roleE) {
        this.roleE = roleE;
    }
}