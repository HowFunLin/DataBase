package manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import entity.Course;
import entity.Department;
import entity.Staff;
import entity.Student;

public class InformationQuery
{
	private static Scanner scan = new Scanner(System.in);
	private static QueryRunner qr = new QueryRunner();

	public static boolean queryStudent(Connection conn) throws SQLException
	{
		System.out.print("选择查询学生基本信息的方式：1-学号，2-姓名，3-系号");
		int tag = Integer.valueOf(scan.nextLine());

		String column;
		String id;

		if (tag == 1)
		{
			column = "id";

			System.out.print("输入要查询的学号：");
			id = scan.nextLine();
		} else if (tag == 2)
		{
			column = "name";

			System.out.print("输入要查询的姓名：");
			id = scan.nextLine();
		} else if (tag == 3)
		{
			column = "departmentID";

			System.out.print("输入要查询的系号：");
			id = scan.nextLine();
		} else
		{
			System.out.println("请选择正确的方式！");
			return false;
		}

		String sql = "SELECT * FROM Student WHERE " + column + " = ?";

		List<Student> students = qr.query(conn, sql, new BeanListHandler<Student>(Student.class), id);

		if (students.size() == 0)
		{
			System.out.println("查询学生失败！请检查输入是否正确！");
			return false;
		} else
		{
			for (Student s : students)
				System.out.println(s);

			return true;
		}
	}

	public static boolean queryStaff(Connection conn) throws SQLException
	{
		System.out.print("选择查询教职工基本信息的方式：1-职工号，2-姓名，3-系号");
		int tag = Integer.valueOf(scan.nextLine());

		String column;
		String id;

		if (tag == 1)
		{
			column = "id";

			System.out.print("输入要查询的职工号：");
			id = scan.nextLine();
		} else if (tag == 2)
		{
			column = "name";

			System.out.print("输入要查询的姓名：");
			id = scan.nextLine();
		} else if (tag == 3)
		{
			column = "departmentID";

			System.out.print("输入要查询的系号：");
			id = scan.nextLine();
		} else
		{
			System.out.println("请选择正确的方式！");
			return false;
		}

		String sql = "SELECT * FROM Staff WHERE " + column + " = ?";

		List<Staff> staffs = qr.query(conn, sql, new BeanListHandler<Staff>(Staff.class), id);

		if (staffs.size() == 0)
		{
			System.out.println("查询教职工失败！请检查输入是否正确！");
			return false;
		} else
		{
			for (Staff s : staffs)
				System.out.println(s);

			return true;
		}
	}

	public static boolean queryDepartment(Connection conn) throws SQLException
	{
		System.out.print("选择查询系的基本信息的方式：1-系号，2-系名称");
		int tag = Integer.valueOf(scan.nextLine());

		String column;
		String id;

		if (tag == 1)
		{
			column = "id";

			System.out.print("输入要查询的系号：");
			id = scan.nextLine();
		} else if (tag == 2)
		{
			column = "name";

			System.out.print("输入要查询的系名称：");
			id = scan.nextLine();
		} else
		{
			System.out.println("请选择正确的方式！");
			return false;
		}

		String sql = "SELECT * FROM Department WHERE " + column + " = ?";

		List<Department> ds = qr.query(conn, sql, new BeanListHandler<Department>(Department.class), id);

		if (ds.size() == 0)
		{
			System.out.println("查询系失败！请检查输入是否正确！");
			return false;
		} else
		{
			for (Department s : ds)
				System.out.println(s);

			return true;
		}
	}

	public static boolean queryCourse(Connection conn) throws SQLException
	{
		System.out.print("选择查询课程基本信息的方式：1-课程号，2-课程名称，3-上课教师姓名");
		int tag = Integer.valueOf(scan.nextLine());

		String column;
		String id;

		if (tag == 1)
		{
			column = "id";

			System.out.print("输入要查询的课程号：");
			id = scan.nextLine();
		} else if (tag == 2)
		{
			column = "name";

			System.out.print("输入要查询的课程名称：");
			id = scan.nextLine();
		} else if (tag == 3)
		{
			column = "staffID";

			System.out.print("输入要查询的上课教师姓名：");
			id = scan.nextLine();

			String sql = "SELECT Course.id, Course.name, Course.staffID, Course.hours, Course.credits, Course.classTime, Course.classLocate, Course.examDate FROM Course, Staff WHERE "
					+ column + " = Staff.id AND Staff.name = ?";

			List<Course> courses = qr.query(conn, sql, new BeanListHandler<Course>(Course.class), id);

			if (courses.size() == 0)
			{
				System.out.println("查询课程失败！请检查输入是否正确！");
				return false;
			} else
			{
				for (Course c : courses)
					System.out.println(c);

				return true;
			}
		} else
		{
			System.out.println("请选择正确的方式！");
			return false;
		}

		String sql = "SELECT * FROM Course WHERE " + column + " = ?";

		List<Course> courses = qr.query(conn, sql, new BeanListHandler<Course>(Course.class), id);

		if (courses.size() == 0)
		{
			System.out.println("查询课程失败！请检查输入是否正确！");
			return false;
		} else
		{
			for (Course c : courses)
				System.out.println(c);

			return true;
		}
	}

	public static boolean queryGrade(Connection conn) throws SQLException
	{
		System.out.print("选择查询学生成绩的方式：1-学号，2-学生姓名，3-课程号，4-课程名称，5-上课教师姓名，6-系号 ");
		int tag = Integer.valueOf(scan.nextLine());

		String id;

		if (tag == 1)
		{
			System.out.print("输入要查询的学生学号：");
			id = scan.nextLine();

			String sql = "SELECT total 总评成绩, Grade.studentID 学号, Grade.courseID 课程号, peacetime 平时成绩, exam 考试成绩, name 课程名称, staffID 任课教师号, hours 学时, credits 学分, classTime 上课时间, classLocate 上课地点, examDate 考试时间 "
					+ "FROM Grade, Course WHERE Course.id = Grade.courseID AND studentID = ? " + "GROUP BY courseID "
					+ "ORDER BY total DESC";

			return queryGradeMethod(conn, sql, id);
		} else if (tag == 2)
		{
			System.out.print("输入要查询的学生姓名：");
			id = scan.nextLine();

			String sql = "SELECT total 总评成绩, Grade.studentID 学号, Student.name 学生姓名, Grade.courseID 课程号, peacetime 平时成绩, exam 考试成绩, Course.name 课程名称, staffID 任课教师号, hours 学时, credits 学分, classTime 上课时间, classLocate 上课地点, examDate 考试时间 "
					+ "FROM Grade, Course, Student WHERE Course.id = Grade.courseID AND Student.id = Grade.studentID AND Student.name = ? "
					+ "GROUP BY courseID " + "ORDER BY total DESC";
			
			return queryGradeMethod(conn, sql, id);
		}
		else if(tag == 3)
		{
			System.out.print("输入要查询的课程号：");
			id = scan.nextLine();

			String sql = "SELECT total 总评成绩, Grade.studentID 学号, Grade.courseID 课程号, peacetime 平时成绩, exam 考试成绩, name 课程名称, staffID 任课教师号, hours 学时, credits 学分, classTime 上课时间, classLocate 上课地点, examDate 考试时间 "
					+ "FROM Grade, Course WHERE Course.id = Grade.courseID AND courseID = ? " + "GROUP BY courseID "
					+ "ORDER BY total DESC";

			return queryGradeMethod(conn, sql, id);
		}
		else if(tag == 4)
		{
			System.out.print("输入要查询的课程名称：");
			id = scan.nextLine();

			String sql = "SELECT total 总评成绩, Grade.studentID 学号, Grade.courseID 课程号, peacetime 平时成绩, exam 考试成绩, name 课程名称, staffID 任课教师号, hours 学时, credits 学分, classTime 上课时间, classLocate 上课地点, examDate 考试时间 "
					+ "FROM Grade, Course WHERE Course.id = Grade.courseID AND name = ? " + "GROUP BY courseID "
					+ "ORDER BY total DESC";

			return queryGradeMethod(conn, sql, id);
		}
		else if(tag == 5)
		{
			System.out.print("输入要查询的上课教师姓名：");
			id = scan.nextLine();
			
			String sql = "SELECT total 总评成绩, Grade.studentID 学号, Staff.name 上课教师姓名, Grade.courseID 课程号, peacetime 平时成绩, exam 考试成绩, Course.name 课程名称, staffID 任课教师号, hours 学时, credits 学分, classTime 上课时间, classLocate 上课地点, examDate 考试时间 "
					+ "FROM Grade, Course, Staff WHERE Course.id = Grade.courseID AND Staff.id = Course.staffID AND Staff.name = ? "
					+ "GROUP BY courseID " + "ORDER BY total DESC";
			
			return queryGradeMethod(conn, sql, id);
		}
		else if(tag == 6)
		{
			System.out.print("输入要查询的系号：");
			id = scan.nextLine();
			
			String sql = "SELECT total 总评成绩, Grade.studentID 学号, Student.name 学生姓名, Grade.courseID 课程号, peacetime 平时成绩, exam 考试成绩, Course.name 课程名称, staffID 任课教师号, hours 学时, credits 学分, classTime 上课时间, classLocate 上课地点, examDate 考试时间, departmentID 所在系号 "
					+ "FROM Grade, Course, Student WHERE Course.id = Grade.courseID AND Student.id = Grade.studentID AND Student.departmentID = ? "
					+ "GROUP BY courseID " + "ORDER BY total DESC";
			
			return queryGradeMethod(conn, sql, id);
		}
		else
		{
			System.out.println("请选择正确的方式！");
			return false;
		}
	}
	
	/**
	 * 查询成绩的方法封装
	 * @param conn 数据库连接
	 * @param sql SQL语句
	 * @param id 查询使用的数据
	 * @return 查询是否成功
	 * @throws SQLException
	 */
	private static boolean queryGradeMethod(Connection conn, String sql, String id) throws SQLException
	{
		List<Map<String, Object>> grades = qr.query(conn, sql, new MapListHandler(), id);

		if (grades.size() == 0)
		{
			System.out.println("查询成绩失败！请检查输入是否正确！");
			return false;
		} else
		{
			for (Map<String, Object> map : grades)
				System.out.println(map.toString().replaceAll("=", "：").replaceAll("\\{|\\}", ""));

			return true;
		}
	}
}