package Repository;

import Entity.Student;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentRepository extends Repository<Student> {
    @Override
    public List<Student> getList() {
        try (Connection connection = connect()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from freedom.student");
            List<Student> list = new ArrayList<Student>();
            while (rs.next()) {
                list.add(new Student(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getBigDecimal("salary"),
                        rs.getInt("university_id")));
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<Student> getList(String name, String surname, BigDecimal salary) throws Exception {
        try (Connection connection = connect()) {
            String sql = "select * from student where ";
            int index = 0;

            if (name != null && name.trim().length() > 0) {
                sql += "name like ? and ";//select * from student where name like ? and
                index++;
            }
            if (surname != null && surname.trim().length() > 0) {
                sql += "surname like ? and ";
                index++;
            }
            if (salary != null) {
                sql += "salary=? and ";
                index++;
            }

            sql = sql.substring(0, sql.length() - 5);

            System.out.println("SQL=" + sql);

            PreparedStatement prStatement = connection.prepareStatement(sql);

            if (salary != null) {
                prStatement.setBigDecimal(index, salary);
                index--;
            }
            if (surname != null && surname.trim().length() > 0) {
                prStatement.setString(index, "%" + surname + "%");
                index--;
            }
            if (name != null && name.trim().length() > 0) {
                prStatement.setString(index, "%" + name + "%");
                index--;
            }

            ResultSet rs = prStatement.executeQuery();

            List<Student> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getBigDecimal("salary"),
                        rs.getInt("university_id")
                ));
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public Student getByID(Integer id) {
        try (Connection connection = connect()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from student where id = " + id);
            Student student = null;
            if (rs.next()) {
                student = new Student(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getBigDecimal("salary"),
                        rs.getInt("university_id"));
                return student;
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Student insert(Student student) {
        try (Connection connection = connect()) {
            PreparedStatement prStatement = connection.prepareStatement(
                    "insert into student(name,surname,salary,university_id) values(?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            prStatement.setString(1, student.getName());
            prStatement.setString(2, student.getSurname());
            prStatement.setBigDecimal(3, student.getSalary());
            prStatement.setInt(4, student.getUniversity_id());
            prStatement.execute();
            ResultSet rs = prStatement.getGeneratedKeys();
            if (rs.next()) {
                student.setId(rs.getInt(1));
            }
            return student;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(Student student) {
        try (Connection connection = connect()) {
            String sql = "update student set ";
            int index = 1;
            if (student.getName() != null) {
                sql += "name=?,";
                index++;
            }
            if (student.getSurname() != null) {
                sql += "surname=?,";
                index++;
            }
            if (student.getSalary() != null) {
                sql += "salary=?,";
                index++;
            }
            if (student.getUniversity_id() != null) {
                sql += "university_id=?,";
                index++;
            }
            sql = sql.substring(0, sql.length() - 1);
            sql += " where id=?";
            System.out.println(sql);
            PreparedStatement prStatement = connection.prepareStatement(sql);

            prStatement.setInt(index, student.getId());
            index--;
            if (student.getUniversity_id() != null) {
                prStatement.setInt(index, student.getUniversity_id());
                index--;
            }
            if (student.getSalary() != null) {
                prStatement.setBigDecimal(index, student.getSalary());
                index--;
            }
            if (student.getSurname() != null) {
                prStatement.setString(index, student.getSurname());
                index--;
            }
            if (student.getName() != null) {
                prStatement.setString(index, student.getName());
                index--;
            }
            return prStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        try (Connection connection = connect()) {
            PreparedStatement prStatement = connection.prepareStatement("delete from student where id = ?");
            prStatement.setInt(1, id);
            return prStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
