package net.smartcosmos.am.pojo.context;

import net.smartcosmos.am.model.context.IUserCredentials;
import net.smartcosmos.pojo.base.AccountTypedNamedObject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value=Include.NON_EMPTY)
public class UserCredentials extends AccountTypedNamedObject<IUserCredentials> implements IUserCredentials{
	private String username;
	private String password;

	public UserCredentials() {
		// TODO Auto-generated constructor stub
	}

	public UserCredentials(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		this.type=type;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserCredentials [username=" + username + ", password="
				+ password + "]";
	}
	
}
