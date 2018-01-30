package Student.entity;

public class banji {
	private Integer id;
	private String name;
	
	
	
	public banji(String name) {
		super();
		this.name = name;
	}



	public banji() {
		super();
	}



	public banji(Integer id, String name) {
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
		return "banji [id=" + id + ", name=" + name + "]";
	}
	
	
	

	

	
	

	
}
