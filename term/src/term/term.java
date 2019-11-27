package term;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
public class term {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/pratice?serverTimezone=UTC","root","root1234");
			Scanner sc=new Scanner(System.in);
			Statement stmt=con.createStatement();
			int menu = 0;
			int r;
			int num;
			String a;
			String b;
			String c;
			String d;
			System.out.println("menu");
			System.out.println("1. insert player  2.insert team  3.insert coach");
			System.out.println("4. select ");
			menu=sc.nextInt();
			String table;
			switch (menu) {
			case 1:
				ResultSet rs=stmt.executeQuery("select * from member");
				while(rs.next())
					System.out.println(rs.getString(1)+" "+rs.getString(2));
				break;
			case 2:
				System.out.println("player table 값 입력 : (player_id, name, position, age, height, weight, t_name");
				a=sc.next();
				b=sc.next();
				c=sc.next();
				d=sc.next();
				//num=sc.nextInt();
				PreparedStatement pstmt = con.prepareStatement("insert into member value(?,?,?,?);");
				pstmt.setString(1,a);
		        pstmt.setString(2,b);
		        pstmt.setString(3,c);
		        pstmt.setString(4,d);
		        r=pstmt.executeUpdate();
		        if(r==0) {
		        	System.out.println("내용 입력에 실패하였습니다.");
		        }
		        else
		        	System.out.println("내용이 입력 되었습니다.");
		        break;
			case 3:
				System.out.println("삭제할 내용 입력 (ex player_id , name , position , back_num , age , height , weight , t_name");
				String x=sc.next();
				stmt = con.createStatement();
                r = stmt.executeUpdate("delete from member where "+ x);
                 
                if( r == 0 ){
                    System.out.println("삭제 할  내용을 찾을 수 없습니다.");
                }else{
                    System.out.println("삭제 되었습니다.");
                }
                break;
			case 4:
				System.out.println("수정할 내용 입력 : ");
				a=sc.next();
				System.out.println("조건 입력 : ");
				b=sc.next();
				stmt = con.createStatement();
                r = stmt.executeUpdate("update member set "+ a +" where "+ b);
                if( r == 0 ){
                    System.out.println("수정 할  내용을 찾을 수 없습니다.");
                }else{
                    System.out.println("수정 되었습니다.");
                }
                break;
			case 5:
				System.out.println("검색할 테이블 입력 : ");
				a=sc.next();
				System.out.println("검색할 내용 입력 : ");
				b=sc.next();
				rs=stmt.executeQuery("select * from "+a+" where "+b);
				int i=1;
				while(rs.next())
					while(rs.getString(i)!=null) {
					System.out.print(rs.getString(i)+" ");
					i++;
					}
				break;
			}
			con.close();
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
