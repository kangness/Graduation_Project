package com.netdist.driver;

public class NetDiskFile {
	private String User;
	private String CreateDate;
	private String Name;
	private int Size;
	private String Permission;
	private String Group;
	private boolean Dirctory;
	public NetDiskFile(String Name,String User,String CreateDate,int Size,String Permission,boolean Dirctory){
		this.Name = Name;
		this.User = User;
		this.CreateDate = CreateDate;
		this.Size = Size;
		this.Permission = Permission;
		this.Dirctory = Dirctory;
	}
	
	public NetDiskFile(String Name,String User,String CreateDate,String Size,String Permission,boolean Dirctory){
		this.Name = Name;
		this.User = User;
		this.CreateDate = CreateDate;
		setSize(Size);
		this.Permission = Permission;
		this.Dirctory = Dirctory;
	}
	
	public NetDiskFile(){
		
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getUser() {
		return User;
	}
	public void setUser(String user) {
		User = user;
	}
	public String getCreateDate() {
		return CreateDate;
	}
	public void setCreateDate(String createDate) {
		CreateDate = createDate;
	}
	public void setGroup(String Group){
		this.Group = Group;
	}
	
	public String getGroup(){
		return this.Group;
	}
	public int getSize() {
		return Size;
	}
	public void setSize(int size) {
		Size = size;
	}
	
	public void setSize(String size) {
		try{
			this.Size = Integer.valueOf(size);
			}
		catch (Exception ex)
		{
			System.out.println(ex.toString() + size);
		}
		
	}
	public String getPermission() {
		return Permission;
	}
	public void setPermission(String permission) {
		Permission = permission;
	}
	public boolean getDirctory(){
		return this.Dirctory;
	}

}
