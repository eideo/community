package community.isecrity.entity;

import java.util.Date;
import java.util.List;

public class Menu {
    private Integer id;

    private Integer pid;

    private String name;

    private String url;

    private Integer place;

    private Date addtime;

    private Date editime;

    private String remark;

    private Integer isdisplay;
    
    private List<Privilege> list;
    
    private List<Menu> clist;
    

	public Menu() {
		super();
	}

	public Menu(Integer id, Integer pid, String name, String url,
			Integer place, Date addtime, Date editime, String remark,
			Integer isdisplay) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.url = url;
		this.place = place;
		this.addtime = addtime;
		this.editime = editime;
		this.remark = remark;
		this.isdisplay = isdisplay;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getPlace() {
		return place;
	}

	public void setPlace(Integer place) {
		this.place = place;
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

	public Integer getIsdisplay() {
		return isdisplay;
	}

	public void setIsdisplay(Integer isdisplay) {
		this.isdisplay = isdisplay;
	}

	public List<Privilege> getList() {
		return list;
	}

	public void setList(List<Privilege> list) {
		this.list = list;
	}

	public List<Menu> getClist() {
		return clist;
	}

	public void setClist(List<Menu> clist) {
		this.clist = clist;
	}

	
	@Override
	public String toString() {
		return "Menu [id=" + id + ", pid=" + pid + ", name=" + name + ", url="
				+ url + ", place=" + place + ", addtime=" + addtime
				+ ", editime=" + editime + ", remark=" + remark
				+ ", isdisplay=" + isdisplay + "]";
	}
	
   
}