package manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import entity.Student;

public class StudentManage
{
	private static Scanner scan = new Scanner(System.in);
	private static QueryRunner qr = new QueryRunner();

	// 录入新生信息
	public static boolean newStudentRecord(Connection conn) throws SQLException
	{
		String sql = "INSERT INTO Student VALUES(?,?,?,?,?,?)";

		System.out.println("输入新生信息：");
		System.out.print("学号：");
		String id = scan.nextLine();
		System.out.print("姓名：");
		String name = scan.nextLine();
		System.out.print("性别：");
		String sex = scan.nextLine();

		System.out.print("出生日期（输入形式 YYYY-MM-DD）：");
		String birth = scan.nextLine();

		System.out.print("入学成绩：");
		int grade = scan.nextInt();
		System.out.print("所在系号：");
		int departmentID = scan.nextInt();

		int row = 0;

		try
		{
			qr.update(conn, sql, id, name, sex, birth, grade, departmentID);
		} catch (Exception e)
		{
			System.out.println("录入新生信息失败！请重新进行操作！");
			return false;
		}

		if (row == 1)
		{
			System.out.println("录入新生信息成功！");
			return true;
		} else
		{
			System.out.println("录入新生信息失败！请重新进行操作！");
			return false;
		}
	}

	// 学生信息修改
	public static boolean selectByIDandUpdate(Connection conn) throws SQLException
	{
		String sql = "SELECT * FROM Student WHERE id = ?";

		System.out.print("输入要查询的学生的学号：");
		String id = scan.nextLine();

		Student student = qr.query(conn, sql, new BeanHandler<Student>(Student.class), id);
		if (student == null)
		{
			System.out.println("查无该学生！请重新输入！");
			return false;
		}
		System.out.println(student);

		System.out.print("输入你要修改的属性名（id, name，sex，birth，grade，departmentID）：");
		String column = scan.nextLine();
		System.out.print("输入修改后的属性内容：");
		String data = scan.nextLine();

		String update = "UPDATE Student SET " + column + " = ? WHERE id = ?";

		if (qr.update(conn, update, data, id) == 1)
		{
			if (column.equals("id"))
				id = data;

			System.out.println("修改成功！修改后的信息为：");
			Student newStudent = qr.query(conn, sql, new BeanHandler<Student>(Student.class), id);
			System.out.println(newStudent);

			return true;
		} else
		{
			System.out.println("修改失败！");

			return false;
		}
	}

	// 学生转学或毕业
	public static boolean transferOrGraduate(Connection conn) throws SQLException
	{
		System.out.print("选择操作（1-转入，2-转出，3-毕业）：");
		int tag = Integer.valueOf(scan.nextLine());

		if (tag == 1)
			return newStudentRecord(conn);
		else if (tag == 2 || tag == 3)
		{
			System.out.print("输入学生的学号：");
			String id = scan.nextLine();

			String sql = "SELECT * FROM Student WHERE id = ?";

			Student s = qr.query(conn, sql, new BeanHandler<Student>(Student.class), id);
			System.out.println(s);

			conn.setAutoCommit(false);
			sql = "DELETE FROM Student WHERE id = ?";

			if (qr.update(conn, sql, id) == 1)
			{
				System.out.println("该生信息已从学生表中移出！");
			} else
			{
				System.out.println("从学生表删除该生信息失败！请重新进行操作！");
				DbUtils.rollback(conn);
				conn.setAutoCommit(true);
				return false;
			}

			sql = "INSERT INTO History VALUES(?,?,?,?,?,?)";

			if (qr.update(conn, sql, s.getId(), s.getName(), s.getSex(), s.getBirth(), s.getGrade(),
					s.getDepartmentID()) == 1)
			{
				System.out.println("该生信息已存入历史库中！");
				conn.setAutoCommit(true);
				DbUtils.commitAndCloseQuietly(conn);
				return true;
			} else
			{
				DbUtils.rollback(conn);
				conn.setAutoCommit(true);
				System.out.println("将该生信息存入历史库失败！请重新操作！");
				return false;
			}
		} else
		{
			System.out.println("请输入正确的数字！");
			return false;
		}
	}
}