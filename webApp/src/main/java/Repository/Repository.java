package Repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public abstract class Repository<T> {
    protected Connection connect() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/freedom?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "Kamran145145145!");
    }

    public abstract List<T> getList() throws Exception;
    public abstract List<T> getList(String name, String surname, BigDecimal salary) throws Exception;
    public abstract T getByID(Integer id);

    public abstract T insert(T t);

    public abstract boolean update(T t);

    public abstract boolean delete(Integer id);
}
