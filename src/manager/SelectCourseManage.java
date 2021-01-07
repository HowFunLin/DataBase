package manager;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class SelectCourseManage
{
	private static Scanner scan = new Scanner(System.in);
	private static QueryRunner qr = new QueryRunner();

	public static boolean select(Connection conn) throws SQLException
	{
		String sql = "INSERT INTO SC VALUES(?,?,?)";

		System.out.println("输入选课信息：");
		System.out.print("学号：");
		String studentID = scan.nextLine();
		System.out.print("课程号：");
		String courseID = scan.nextLine();
		System.out.print("学分：");
		int credits = Integer.valueOf(scan.nextLine());

		if (qr.update(conn, sql, studentID, courseID, credits) == 1)
		{
			sql = "SELECT SUM(credits) FROM SC WHERE studentID = ?";

			BigDecimal sum = qr.query(conn, sql, new ScalarHandler<>(), studentID);

			if (Integer.valueOf(sum.toPlainString()) > 15)
			{
				sql = "DELETE FROM SC WHERE studentID = ? AND courseID = ?";
				qr.update(conn, sql, studentID, courseID);

				System.out.println("选课后学分超过15分！无法进行选课！");
				return false;
			}

			System.out.println("选课成功！");
			return true;
		} else
		{
			System.out.println("选课失败！请重新进行操作！");
			return false;
		}
	}

	public static boolean delete(Connection conn) throws SQLException
	{
		System.out.println("输入要删除的选课信息：");
		System.out.print("输入学号：");
		String studentID = scan.nextLine();
		System.out.print("输入课程号");
		String courseID = scan.nextLine();

		String sql = "DELETE FROM SC WHERE studentID = ? AND courseID = ?";

		if (qr.update(conn, sql, studentID, courseID) == 1)
		{
			System.out.println("已将该选课信息删除！");

			return true;
		} else
		{
			System.out.println("从选课表删除该选课信息失败！请重新进行操作！");

			return false;
		}
	}

	public static boolean update(Connection conn) throws SQLException
	{
		System.out.println("输入要修改的选课信息：");
		System.out.print("输入学号：");
		String studentID = scan.nextLine();
		System.out.print("输入课程号：");
		String courseID = scan.nextLine();

		System.out.print("输入要修改的属性名（studentID, courseID, credits）：");
		String column = scan.nextLine();
		System.out.print("输入修改后的属性内容：");
		String data = scan.nextLine();

		String update = "UPDATE SC SET " + column + " = ? WHERE studentID = ? AND courseID = ?";

		if (qr.update(conn, update, data, studentID, courseID) == 1)
		{
			System.out.println("修改成功！");
			return true;
		} else
		{
			System.out.println("修改失败！");
			return false;
		}
	}
}