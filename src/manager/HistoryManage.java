package manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import entity.Student;

public class HistoryManage
{
	private static QueryRunner qr = new QueryRunner();
	
	public static boolean select(Connection conn) throws SQLException
	{
		String sql = "SELECT * FROM History";
		
		List<Student> students = qr.query(conn, sql, new BeanListHandler<Student>(Student.class));
		
		if (students.size() == 0)
		{
			System.out.println("查询成绩失败！请检查输入是否正确！");
			return false;
		} else
		{
			for (Student s : students)
				System.out.println(s);

			return true;
		}
	}
}
