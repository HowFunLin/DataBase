package manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import entity.Course;

public class CourseManage
{
	private static Scanner scan = new Scanner(System.in);
	private static QueryRunner qr = new QueryRunner();

	public static boolean insert(Connection conn) throws SQLException
	{
		String sql = "INSERT INTO Course VALUES(?,?,?,?,?,?,?,?)";

		System.out.println("输入课程信息：");
		System.out.print("课程号：");
		String id = scan.nextLine();
		System.out.print("课程名称：");
		String name = scan.nextLine();
		System.out.print("任课教师号：");
		String staffID = scan.nextLine();
		System.out.print("学时：");
		int hours = Integer.valueOf(scan.nextLine());
		System.out.print("学分：");
		int credits = Integer.valueOf(scan.nextLine());
		System.out.print("上课时间：");
		String classTime = scan.nextLine();
		System.out.print("上课地点：");
		String classLocate = scan.nextLine();
		System.out.print("考试时间：");
		String examDate = scan.nextLine();

		if (qr.update(conn, sql, id, name, staffID, hours, credits, classTime, classLocate, examDate) == 1)
		{
			System.out.println("输入课程信息成功！");
			return true;
		} else
		{
			System.out.println("输入课程信息失败！请重新进行操作！");
			return false;
		}
	}

	public static boolean update(Connection conn) throws SQLException
	{
		String sql = "SELECT * FROM Course WHERE id = ?";
		
		System.out.print("输入要修改的课程的课程号：");
		String id = scan.nextLine();
		
		Course d = qr.query(conn, sql, new BeanHandler<Course>(Course.class), id);
		if (d == null)
		{
			System.out.println("查无该课程！请重新输入！");
			return false;
		}

		System.out.print("输入要修改的属性名（id, name，staffID, hours, credits, classTime, classLocate, examDate）：");
		String column = scan.nextLine();
		System.out.print("输入修改后的属性内容：");
		String data = scan.nextLine();

		String update = "UPDATE Course SET " + column + " = ? WHERE id = ?";

		if (qr.update(conn, update, data, id) == 1)
		{
			if (column.equals("id"))
				id = data;

			System.out.println("修改成功！修改后的信息为：");
			Course nd = qr.query(conn, sql, new BeanHandler<Course>(Course.class), id);
			System.out.println(nd);

			return true;
		} else
		{
			System.out.println("修改失败！");

			return false;
		}
	}
	
	public static boolean delete(Connection conn) throws SQLException
	{
		System.out.print("输入要删除的课程的课程号：");
		String id = scan.nextLine();

		String sql = "DELETE FROM Course WHERE id = ?";

		if (qr.update(conn, sql, id) == 1)
		{
			System.out.println("已将该课程的信息删除！");
			
			return true;
		} else
		{
			System.out.println("从课程表删除该课程信息失败！请重新进行操作！");
			
			return false;
		}
	}
}