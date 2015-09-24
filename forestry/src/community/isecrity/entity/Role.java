package community.isecrity.entity;

import java.util.Date;
import java.util.List;

public class Role {
    private Integer id;

    private String name;

    private Date addtime;

    private Date editime;

    private String remark;
    
    private List<User> ulist;
    
    private List<Privilege> plist;

	public Role() {
		super();
	}

	public Role(Integer id, String name, Date addtime, Date editime,
			String remark) {
		super();
		this.id = id;
		this.name = name;
		this.addtime = addtime;
		this.editime = editime;
		this.remark = remark;
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

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Date getEditime() {
		return editime;
	}

	public void setEditime(Date editime) {
		this.editime = editime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<User> getUlist() {
		return ulist;
	}

	public void setUlist(List<User> ulist) {
		this.ulist = ulist;
	}

	public List<Privilege> getPlist() {
		return plist;
	}

	public void setPlist(List<Privilege> plist) {
		this.plist = plist;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", addtime=" + addtime
				+ ", editime=" + editime + ", remark=" + remark + "]";
	}

    
}