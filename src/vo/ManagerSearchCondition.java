package vo;

import java.util.List;
import java.util.Map;

public class ManagerSearchCondition {
	private Integer pageNo;
	private Integer pageSize;
	private String name;
	private String age;
	private String banji;
	private String course;
	private String credit;
	public ManagerSearchCondition() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ManagerSearchCondition(Integer pageNo, Integer pageSize, String name, String age, String banji,
			String course, String credit) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.name = name;
		this.age = age;
		this.banji = banji;
		this.course = course;
		this.credit = credit;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getBanji() {
		return banji;
	}
	public void setBanji(String banji) {
		this.banji = banji;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	@Override
	public String toString() {
		return "ManagerSearchCondition [pageNo=" + pageNo + ", pageSize=" + pageSize + ", name=" + name + ", age=" + age
				+ ", banji=" + banji + ", course=" + course + ", credit=" + credit + "]";
	}
	
	
}
