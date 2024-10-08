import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class EmployeeRepository {

    private final ArrayList<Employee> list;


    public EmployeeRepository() throws SQLException{
        Employee obj = new Employee();
        this.list = obj.populateEmployee();
    };

    public ArrayList<Employee> getList(){
        return list;
    }


    public Connection dataBaseConnection(){

        try {
            String url= "jdbc:mysql://localhost:3306/emp_dept";
            String userName = "root";
            String password = "Vidar0408!";

            Connection con = DriverManager.getConnection(url,userName,password);

            return con;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void opgaveA () throws SQLException {

        Connection con = dataBaseConnection();

        Statement stmt = con.createStatement();

        ResultSet rls = stmt.executeQuery("select ENAME, EMPNO from emp");


        while (rls.next()){
            String name = rls.getString("ename");
            String EmployeeNr = rls.getString("EMPNO");
            System.out.println(name + " " + EmployeeNr);
        }

    }

    public void opgaveB () throws SQLException {

        Connection con = dataBaseConnection();

        Statement stmt = con.createStatement();

        ResultSet rls = stmt.executeQuery("select ENAME, EMPNO from emp where ENAME like 'S%'");


        while (rls.next()){
            String name = rls.getString("ename");
            String EmployeeNr = rls.getString("EMPNO");
            System.out.println(name + " " + EmployeeNr);
        }

    }

    public void opgaveC () throws SQLException {

        Connection con = dataBaseConnection();

        Statement stmt = con.createStatement();

        ResultSet rls = stmt.executeQuery("select SUM(SAL) from emp");


        while (rls.next()){
            int sum = rls.getInt("SUM(SAL)");
            System.out.println(sum);
        }

    }

    public void opgaveD () throws SQLException {

        Connection con = dataBaseConnection();

        Statement stmt = con.createStatement();

        ResultSet rls = stmt.executeQuery("select ENAME, sal from emp where sal = (select MAX(SAL) from emp)");


        while (rls.next()){
            int sal = rls.getInt("sal");
            String name = rls.getString("ENAME");
            System.out.println(name + " " + sal);
        }

    }

    public void opgaveE () throws SQLException {

        Connection con = dataBaseConnection();

        Statement stmt = con.createStatement();

        ResultSet rls = stmt.executeQuery("select ENAME, sal from emp where sal > (select avg(SAL) from emp)");


        while (rls.next()){
            int sal = rls.getInt("sal");
            String name = rls.getString("ENAME");
            System.out.println(name + " " + sal);
        }

    }




    public void opgaveF (String name) throws SQLException {

        Connection con = dataBaseConnection();

        String dbInput = "select e.ENAME as employee_name from emp e join emp m on e.MGR = m.EMPNO where m.ENAME = ?";

        PreparedStatement ps = con.prepareStatement(dbInput);
        ps.setString(1, name);

        ResultSet rls = ps.executeQuery();

        while (rls.next()){
            String nameOutput = rls.getString("employee_name");
            System.out.println(nameOutput + " manager is " + name);
        }

    }

    public void opgaveG() throws SQLException{

        Connection con = dataBaseConnection();

        Statement stmt = con.createStatement();

        ResultSet rls = stmt.executeQuery("select count(ENAME) as counter, dname from emp inner join dept on emp.DEPTNO = dept.DEPTNO group by dname having count(ENAME) >= 5");


        while (rls.next()){
            int counter = rls.getInt("counter");
            String name = rls.getString("dname");
            System.out.println(name + " " + counter);
        }


    }

}
