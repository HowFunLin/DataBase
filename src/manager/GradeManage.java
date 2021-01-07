package manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.dbutils.QueryRunner;

public class GradeManage
{
	private static Scanner scan = new Scanner(System.in);
	private static QueryRunner qr = new QueryRunner();

	public static boolean insert(Connection conn) throws SQLException
	{
		String sql = "INSERT INTO Grade VALUES(?,?,?,?,?)";

		System.out.println("输入成绩：");
		System.out.print("学号：");
		String studentID = scan.nextLine();
		System.out.print("课程号：");
		String courseID = scan.nextLine();
		System.out.print("平时成绩：");
		int peacetime = Integer.valueOf(scan.nextLine());
		System.out.print("考试成绩：");
		int exam = Integer.valueOf(scan.nextLine());
		System.out.print("总评成绩：");
		int total = Integer.valueOf(scan.nextLine());

		if (qr.update(conn, sql, studentID, courseID, peacetime, exam, total) == 1)
		{
			System.out.println("输入成绩成功！");
			return true;
		} else
		{
			System.out.println("输入成绩失败！请重新进行操作！");
			return false;
		}
	}
	
	public static boolean update(Connection conn) throws SQLException
	{
		System.out.print("选择修改成绩的方式：1-学号，2-课程号");
		int tag = Integer.valueOf(scan.nextLine());
		
		String column;
		String id;
		
		if(tag == 1)
		{
			column = "studentID";
			
			System.out.print("输入要修改成绩的学号：");
			id = scan.nextLine();
		}
		else if(tag == 2)
		{
			column = "courseID";
			
			System.out.print("输入要修改成绩的课程号：");
			id = scan.nextLine();
		}
		else
		{
			System.out.println("请选择正确的方式！");
			return false;
		}
		
		System.out.print("输入要修改的属性名（studentID, courseID, peacetime, exam, total）：");
		String change = scan.nextLine();
		System.out.print("输入修改后的属性内容：");
		String data = scan.nextLine();
		
		String sql = "UPDATE Grade SET " + change + " = ? WHERE " + column + " = ?";
		if (qr.update(conn, sql, data, id) == 1)
		{
			System.out.println("成绩修改成功！");
			return true;
		}
		else
		{
			System.out.println("成绩修改失败！");
			return false;
		}
	}
}
