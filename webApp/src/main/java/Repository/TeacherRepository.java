package Repository;

import Entity.Teacher;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TeacherRepository extends Repository<Teacher> {

    public List<Teacher> getList() {
        try (Connection connection = connect()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from freedom.teacher");
            List<Teacher> list = new ArrayList<Teacher>();
            while (rs.next()) {
                list.add(new Teacher(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getInt("university_id"),
                        rs.getBigDecimal("salary")));
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<Teacher> getList(String name, String surname, BigDecimal salary) {
        try (Connection connection = connect()) {
            String sql = "select * from teacher where ";
            int index = 0;

            if (name != null && name.trim().length() > 0) {
                sql += "name like ? and ";//select * from teacher where name like ? and 
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

            List<Teacher> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Teacher(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getInt("university_id"),
                        rs.getBigDecimal("salary")
                ));
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    public Teacher getByID(Integer id) {
        try (Connection connection = connect()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from teacher where id = " + id);
            Teacher teacher = null;
            if (rs.next()) {
                teacher = new Teacher(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getInt("university_id"),
                        rs.getBigDecimal("salary"));
                return teacher;
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Teacher insert(Teacher teacher) {
        try (Connection connection = connect()) {
            PreparedStatement prStatement = connection.prepareStatement(
                    "insert into teacher(name,surname,salary,university_id) values(?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            prStatement.setString(1, teacher.getName());
            prStatement.setString(2, teacher.getSurname());
            prStatement.setBigDecimal(3, teacher.getSalary());
            prStatement.setInt(4, teacher.getUniversity_id());
            prStatement.execute();
            ResultSet rs = prStatement.getGeneratedKeys();
            if (rs.next()) {
                teacher.setId(rs.getInt(1));
            }
            return teacher;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(Teacher teacher) {
        try (Connection connection = connect()) {
            String sql = "update teacher set ";
            int index = 1;
            if (teacher.getName() != null) {
                sql += "name=?,";
                index++;
            }
            if (teacher.getSurname() != null) {
                sql += "surname=?,";
                index++;
            }
            if (teacher.getSalary() != null) {
                sql += "salary=?,";
                index++;
            }
            if (teacher.getUniversity_id() != null) {
                sql += "university_id=?,";
                index++;
            }
            sql = sql.substring(0, sql.length() - 1);
            sql += " where id=?";
            System.out.println(sql);
            PreparedStatement prStatement = connection.prepareStatement(sql);

            prStatement.setInt(index, teacher.getId());
            index--;
            if (teacher.getUniversity_id() != null) {
                prStatement.setInt(index, teacher.getUniversity_id());
                index--;
            }
            if (teacher.getSalary() != null) {
                prStatement.setBigDecimal(index, teacher.getSalary());
                index--;
            }
            if (teacher.getSurname() != null) {
                prStatement.setString(index, teacher.getSurname());
                index--;
            }
            if (teacher.getName() != null) {
                prStatement.setString(index, teacher.getName());
                index--;
            }
            return prStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Integer id) {
        try (Connection connection = connect()) {
            PreparedStatement prStatement = connection.prepareStatement("delete from teacher where id = ?");
            prStatement.setInt(1, id);
            return prStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
