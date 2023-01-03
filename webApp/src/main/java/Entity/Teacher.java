package Entity;

import java.math.BigDecimal;

public class Teacher {

    private Integer id;
    private String name;
    private String surname;
    private BigDecimal salary;
    private Integer university_id;

    public Teacher() {

    }

    public Teacher(Integer id, String name, String surname, Integer university_id, BigDecimal salary) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.university_id = university_id;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public Teacher setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Teacher setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public Teacher setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public Integer getUniversity_id() {
        return university_id;
    }

    public Teacher setUniversity_id(Integer university_id) {
        this.university_id = university_id;
        return this;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public Teacher setSalary(BigDecimal salary) {
        this.salary = salary;
        return this;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", salary=" + salary +
                ", university_id=" + university_id +
                '}';
    }
}
