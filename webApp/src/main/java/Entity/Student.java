package Entity;

import java.math.BigDecimal;

public class Student {
    private Integer id;
    private String name;
    private String surname;
    private BigDecimal salary;
    private Integer university_id;

    public Student() {

    }
    public Student(Integer id, String name, String surname, BigDecimal salary, Integer university_id) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.university_id = university_id;
    }

    public Integer getId() {
        return id;
    }

    public Student setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public Student setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public Student setSalary(BigDecimal salary) {
        this.salary = salary;
        return this;
    }

    public Integer getUniversity_id() {
        return university_id;
    }

    public Student setUniversity_id(Integer university_id) {
        this.university_id = university_id;
        return this;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", salary=" + salary +
                ", university_id=" + university_id +
                '}';
    }
}
