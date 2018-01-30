package Student.entity;

import java.io.Serializable;
import java.util.Date;

public class Student {
	private Integer id;
	private String name;
	private Integer age;
	private String gender;
	private String address;
	private Date addTime;
	private Date birthday;
	private String banji;
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(String name, Integer age, String gender, String address, Date addTime, Date birthday, String banji) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.addTime = addTime;
		this.birthday = birthday;
		this.banji = banji;
	}
	public Student(Integer id, String name, Integer age, String gender, String address, Date addTime, Date birthday) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.addTime = addTime;
		this.birthday = birthday;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getBanji() {
		return banji;
	}
	public void setBanji(String banji) {
		this.banji = banji;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", address=" + address
				+ ", addTime=" + addTime + ", birthday=" + birthday + ", banji=" + banji + "]";
	}
	
}
