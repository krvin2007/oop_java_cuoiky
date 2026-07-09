package com.mycompany.quanlygara.model;

public abstract class Employee extends Person {
    // Tên đăng nhập (Username)
    private String username;
    // Mật khẩu (Password)
    private String password;
    // Vai trò (Role)
    private String role;
    // Lương (Salary)
    private double salary;
    // Trạng thái làm việc (Status)
    private String status;

    // Khởi tạo đối tượng Employee mới
    public Employee() {
        super();
    }

    // Khởi tạo đối tượng Employee mới
    public Employee(int id, String name, String phone, String address, String username, String password, String role, double salary, String status) {
        super(id, name, phone, address);
        this.username = username;
        this.password = password;
        this.role = role;
        this.salary = salary;
        this.status = status;
    }

    // Lấy giá trị của thuộc tính Username
    public String getUsername() { return username; }
    // Cập nhật giá trị cho thuộc tính Username
    public void setUsername(String username) { this.username = username; }

    // Lấy giá trị của thuộc tính Password
    public String getPassword() { return password; }
    // Cập nhật giá trị cho thuộc tính Password
    public void setPassword(String password) { this.password = password; }

    // Lấy giá trị của thuộc tính Role
    public String getRole() { return role; }
    // Cập nhật giá trị cho thuộc tính Role
    public void setRole(String role) { this.role = role; }

    // Lấy giá trị của thuộc tính Salary
    public double getSalary() { return salary; }
    // Cập nhật giá trị cho thuộc tính Salary
    public void setSalary(double salary) { this.salary = salary; }

    // Lấy giá trị của thuộc tính Status
    public String getStatus() { return status; }
    // Cập nhật giá trị cho thuộc tính Status
    public void setStatus(String status) { this.status = status; }
}
