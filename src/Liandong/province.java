package Liandong;

public class province {
	private Integer id;
	private String province;
	private Integer sort;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	@Override
	public String toString() {
		return "province [id=" + id + ", province=" + province + ", sort=" + sort + "]";
	}
	
	
	
		
	
}
