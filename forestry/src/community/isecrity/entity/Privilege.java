package community.isecrity.entity;

import java.util.List;

public class Privilege {
    private Integer id;

    private String name;

    private Integer desc;

    private Integer mid;

    private Integer style;
    
    private List<Role> list;

    private List<Menu> mList;
    
	public Privilege() {
		super();
	}

	public Privilege(Integer id, String name, Integer desc, Integer mid,
			Integer style) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.mid = mid;
		this.style = style;
	}

	public Integer getId() {
		return id;
	}

	public void setPid(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDesc() {
		return desc;
	}

	public void setDesc(Integer desc) {
		this.desc = desc;
	}

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public Integer getStyle() {
		return style;
	}

	public void setStyle(Integer style) {
		this.style = style;
	}

	public List<Role> getList() {
		return list;
	}

	public void setList(List<Role> list) {
		this.list = list;
	}

	public List<Menu> getmList() {
		return mList;
	}

	public void setmList(List<Menu> mList) {
		this.mList = mList;
	}

	@Override
	public String toString() {
		return "Privilege [id=" + id + ", name=" + name + "]";
	}
}