package com.mycompany.quanlygara.model;

public abstract class Employee extends Person {
    private String username;
    private String password;
    private String role;
    private double salary;
    private String status;

    public Employee() {
        super();
    }

    public Employee(int id, String name, String phone, String address, String username, String password, String role, double salary, String status) {
        super(id, name, phone, address);
        this.username = username;
        this.password = password;
        this.role = role;
        this.salary = salary;
        this.status = status;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
