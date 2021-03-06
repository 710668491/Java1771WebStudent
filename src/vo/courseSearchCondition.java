package vo;

public class courseSearchCondition {
	private Integer pageNo;
	private Integer pageSize;
	private String id;
	private String name;
	public courseSearchCondition() {
		super();
	}
	public courseSearchCondition(Integer pageNo, Integer pageSize, String name) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.name = name;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
		return "courseSearchCondition [pageNo=" + pageNo + ", pageSize=" + pageSize + ", id=" + id + ", name=" + name
				+ "]";
	}

	
	
	


}
