package entity;

import java.sql.Date;

public class Staff
{
	private String id;
	private String name;
	private String sex;
	private Date birth;
	private int departmentID;
	private String title;
	private String major;
	private String direct;
	
	public Staff(String id, String name, String sex, Date birth, int departmentID, String title, String major,
			String direct)
	{
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.birth = birth;
		this.departmentID = departmentID;
		this.title = title;
		this.major = major;
		this.direct = direct;
	}
	
	public Staff()
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

	public int getDepartmentID()
	{
		return departmentID;
	}

	public void setDepartmentID(int departmentID)
	{
		this.departmentID = departmentID;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getMajor()
	{
		return major;
	}

	public void setMajor(String major)
	{
		this.major = major;
	}

	public String getDirect()
	{
		return direct;
	}

	public void setDirect(String direct)
	{
		this.direct = direct;
	}

	@Override
	public String toString()
	{
		return "教职工信息：职工号：" + id + " 姓名：" + name + " 性别：" + sex + " 出生年月：" + birth + " 所在系号："
				+ departmentID + " 职称：" + title + " 专业：" + major + " 教学方向：" + direct;
	}
}
