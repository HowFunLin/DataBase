package entity;

import java.sql.Date;

public class Student
{
	private int grade;
	private int departmentID;
	private Date birth;
	private String sex;
	private String name;
	private String id;
	
	public Student(int grade, int departmentID, Date birth, String sex, String name, String id)
	{
		this.grade = grade;
		this.departmentID = departmentID;
		this.birth = birth;
		this.sex = sex;
		this.name = name;
		this.id = id;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getSex()
	{
		return sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public Date getBirth()
	{
		return birth;
	}

	public void setBirth(Date birth)
	{
		this.birth = birth;
	}

	public int getGrade()
	{
		return grade;
	}

	public void setGrade(int grade)
	{
		this.grade = grade;
	}

	public int getDepartmentID()
	{
		return departmentID;
	}

	public void setDepartmentID(int departmentID)
	{
		this.departmentID = departmentID;
	}

	@Override
	public String toString()
	{
		return "学生信息：学号：" + id + " 姓名：" + name + " 性别：" + sex + " 出生日期：" + birth
				+ " 入学成绩：" + grade + " 所在系号：" + departmentID;
	}

	public Student()
	{
		
	}
}