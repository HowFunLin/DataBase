package entity;

public class Course
{
	private String id;
	private String name;
	private String staffID;
	private int hours;
	private int credits;
	private String classTime;
	private String classLocate;
	private String examDate;

	public Course(String id, String name, String staffID, int hours, int credits, String classTime, String classLocate,
			String examDate)
	{
		this.id = id;
		this.name = name;
		this.staffID = staffID;
		this.hours = hours;
		this.credits = credits;
		this.classTime = classTime;
		this.classLocate = classLocate;
		this.examDate = examDate;
	}

	public Course()
	{

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

	public String getStaffID()
	{
		return staffID;
	}

	public void setStaffID(String staffID)
	{
		this.staffID = staffID;
	}

	public int getHours()
	{
		return hours;
	}

	public void setHours(int hours)
	{
		this.hours = hours;
	}

	public int getCredits()
	{
		return credits;
	}

	public void setCredits(int credits)
	{
		this.credits = credits;
	}

	public String getClassTime()
	{
		return classTime;
	}

	public void setClassTime(String classTime)
	{
		this.classTime = classTime;
	}

	public String getClassLocate()
	{
		return classLocate;
	}

	public void setClassLocate(String classLocate)
	{
		this.classLocate = classLocate;
	}

	public String getExamDate()
	{
		return examDate;
	}

	public void setExamDate(String examDate)
	{
		this.examDate = examDate;
	}

	@Override
	public String toString()
	{
		return "课程信息：课程号：" + id + " 课程名称：" + name + " 任课教师号：" + staffID + " 学时：" + hours + " 学分：" + credits + " 上课时间："
				+ classTime + " 上课地点：" + classLocate + " 考试时间：" + examDate;
	}
}