package project;

import lombok.Data;

@Data //Constructor, Getter, Setter, hashCode(), equals(), toString() ÀÚµ¿ »ý¼º
public class User {
	private String userId;
	private String userName;
	private String userPassword;
	private int userAge;
	private String userEmail;
}
