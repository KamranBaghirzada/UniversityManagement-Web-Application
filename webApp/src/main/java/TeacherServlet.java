import Entity.Student;
import Entity.Teacher;
import Repository.StudentRepository;
import Repository.TeacherRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet(urlPatterns = "", name = "TeachersServlet")
public class TeacherServlet extends HttpServlet {
    public final TeacherRepository teacherRepository = new TeacherRepository();
    public final StudentRepository studentRepository = new StudentRepository();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("jspname").contains("teacher")) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            String action = request.getParameter("action");
            if ("delete".equalsIgnoreCase(action)) {
                teacherRepository.delete(id);
            } else if ("update".equalsIgnoreCase(action)) {
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String salary = request.getParameter("salary");
                teacherRepository.update(new Teacher()
                        .setId(id)
                        .setName(name)
                        .setSurname(surname)
                        .setSalary(new BigDecimal(salary)));
            } else if ("insert".equalsIgnoreCase(action)) {
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String salary = request.getParameter("salary");
                Integer universityId = Integer.parseInt(request.getParameter("university_id"));
                Teacher teacher = new Teacher();
                teacher.setName(name).setSurname(surname).setSalary(new BigDecimal(salary)).setUniversity_id(universityId);
                teacherRepository.insert(teacher);
            }
            response.sendRedirect("/webApp_war_exploded/mainteacher.jsp");
        } else if (request.getParameter("jspname").contains("student")) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            String action = request.getParameter("action");
            if ("delete".equalsIgnoreCase(action)) {
                studentRepository.delete(id);
            } else if ("update".equalsIgnoreCase(action)) {
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String salary = request.getParameter("salary");
                studentRepository.update(new Student()
                        .setId(id)
                        .setName(name)
                        .setSurname(surname)
                        .setSalary(new BigDecimal(salary)));
            } else if ("insert".equalsIgnoreCase(action)) {
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String salary = request.getParameter("salary");
                Integer universityId = Integer.parseInt(request.getParameter("university_id"));
                Student student = new Student();
                student.setName(name).setSurname(surname).setSalary(new BigDecimal(salary)).setUniversity_id(universityId);
                studentRepository.insert(student);
            }
            response.sendRedirect("/webApp_war_exploded/mainstudent.jsp");

        } else {
            response.getOutputStream().print("<h1> WTF are you doing? </h1>");
        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("main.jsp").forward(request, response);
    }
}
