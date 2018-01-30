package Student.entity;

public class course {
	private Integer id;
	private String name;
	
	
	
	public course(String name) {
		super();
		this.name = name;
	}



	public course() {
		super();
	}



	public course(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
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



	@Override
	public String toString() {
		return "course [id=" + id + ", name=" + name + "]";
	}
	
	
	

	

	
	

	
}
