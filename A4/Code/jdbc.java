import java.sql.*;
import java.util.*;

public class jdbc {
    public static void main(String args[]){
        Scanner sc =new Scanner(System.in);
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/professor_schema?autoReconnect=true&useSSL=false","root","$w@teJ1800");
            Statement stmt = con.createStatement();

            int ch=0;
            do{
                System.out.println("\n Menu: \n1.Simple View \n2.Complex View \n3.Drop view \n4.Simple index \n5.Unique Index \n6.Compound index \n7.Show Index \n8.drop index \n9.Auto-Increment \n0.Exit:");
                ch=sc.nextInt();
                switch(ch){
                    case 1:
                        String sql="create or replace view myview1 as select prof_id,prof_fname from professors;";
                        stmt.executeUpdate(sql);
                        ResultSet rs = stmt.executeQuery("select * from myview1");
                        while(rs.next()) {
                            System.out.println(rs.getInt(1) + "\t" + rs.getString(2));
                        }
                        break;
                    case 2:
                        String sql1="create or replace view myview2 as select prof_id,prof_fname,dept_name from professors,departments where professors.dept_id=departments.dept_id";
                        stmt.executeUpdate(sql1);
                        ResultSet rs2 = stmt.executeQuery("select * from myview2");
                        while(rs2.next()) {
                            System.out.println(rs2.getInt(1) + "\t" + rs2.getString(2));
                        }
                        break;
                    case 3:
                        String sql2="drop view myview1";
                        stmt.executeUpdate(sql2);
                        sql2="drop view myview2";
                        stmt.executeUpdate(sql2);
                        System.out.println("Query OK!") ;
                        break;
                    case 4:
                        String sql3="create index prof1 on professors(prof_fname)";
                        stmt.executeUpdate(sql3);
                        break;
                    case 5:
                        String sql4="create unique index prof2 on professors(prof_fname)";
                        stmt.executeUpdate(sql4);
                        break;
                    case 6:
                        String sql5="create index prof3 on professors(prof_fname,prof_lname)";
                        stmt.executeUpdate(sql5);
                        break;
                    case 7:
                        String sql6="show index from professors";
                        ResultSet rs3=stmt.executeQuery(sql6);
                        while(rs3.next()){
                            System.out.println(rs3.getString(1)+" "+rs3.getString(2)+" "+rs3.getString(3)+" "+rs3.getString(5));
                        }
                        break;
                    case 8:
                        String sql7="alter table professors drop index prof1";
                        stmt.executeUpdate(sql7);
                        sql7="alter table professors drop index prof2";
                        stmt.executeUpdate(sql7);
                        sql7="alter table professors drop index prof3";
                        stmt.executeUpdate(sql7);
                        break;
                    case 9:
                        stmt.executeUpdate("create table prof(id int unsigned not null auto_increment, primary key(id), stud_name varchar(20))");

                        String sql8 = "INSERT INTO prof " +
                                "VALUES (null, 'Samir')";
                        stmt.executeUpdate(sql8);
                        sql8 = "INSERT INTO prof " +
                                "VALUES (null, 'Suyash')";
                        stmt.executeUpdate(sql8);
                        ResultSet r = stmt.executeQuery("select * from prof");
                        System.out.println("ID\tName");
                        while(r.next()) {
                            System.out.println(r.getInt(1) + "\t" + r.getString(2));
                        }
                        stmt.executeUpdate("drop table prof");
                }
            }while(ch!=0);

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}



