package dto;

public class kadi {
	private String name;
	private int age;
	private String sei;
	private int number;
	private String mail;
	private String pass;
	private String salt;
	private String hash;
	public kadi(String name, int age, String sei, int number, String mail, String pass, String salt, String hash) {
		super();
		this.name = name;
		this.age = age;
		this.sei = sei;
		this.number = number;
		this.mail = mail;
		this.pass = pass;
		this.salt = salt;
		this.hash = hash;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSei() {
		return sei;
	}
	public void setSei(String sei) {
		this.sei = sei;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	
	
}
