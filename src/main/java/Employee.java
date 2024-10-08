import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class Employee {

    private Connection con;


    private int EMPNO;
    private String Ename;
    private String JOB;
    private String MGR;
    private Date HIREDATE;
    private int SAL;
    private int COMM;
    private int DEPTNO;

    public Employee(int EMPNO, String Ename, int sal){
        this.EMPNO = EMPNO;
        this.Ename = Ename;
        this.SAL = sal;
        this.con = dataBaseConnection();
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

    public ArrayList<Employee> populateEmployee() throws SQLException{
        ArrayList<Employee> list = new ArrayList<>();

        String sqlInput = "select ename, empno, sal from emp";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sqlInput);

        while(rs.next()){
            Employee obj = new Employee(rs.getInt("empno"),rs.getString("ename"),rs.getInt("sal"));
            list.add(obj);
        }

        return list;
    }

    @Override
    public String toString(){
        return this.getEname() + this.getEMPNO() + this.getSAL();
    }

    public int getEMPNO() {
        return EMPNO;
    }

    public void setEMPNO(int EMPNO) {
        this.EMPNO = EMPNO;
    }

    public String getEname() {
        return Ename;
    }

    public void setEname(String ename) {
        Ename = ename;
    }

    public String getJOB() {
        return JOB;
    }

    public void setJOB(String JOB) {
        this.JOB = JOB;
    }

    public String getMGR() {
        return MGR;
    }

    public void setMGR(String MGR) {
        this.MGR = MGR;
    }

    public Date getHIREDATE() {
        return HIREDATE;
    }

    public void setHIREDATE(Date HIREDATE) {
        this.HIREDATE = HIREDATE;
    }

    public int getSAL() {
        return SAL;
    }

    public void setSAL(int SAL) {
        this.SAL = SAL;
    }

    public int getCOMM() {
        return COMM;
    }

    public void setCOMM(int COMM) {
        this.COMM = COMM;
    }

    public int getDEPTNO() {
        return DEPTNO;
    }

    public void setDEPTNO(int DEPTNO) {
        this.DEPTNO = DEPTNO;
    }

    public Employee(){
        this.con = dataBaseConnection();
    }


}
