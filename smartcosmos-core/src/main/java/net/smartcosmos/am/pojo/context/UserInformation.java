package net.smartcosmos.am.pojo.context;

import java.util.List;

import net.smartcosmos.am.model.context.ICustomData;
import net.smartcosmos.am.model.context.IGroup;
import net.smartcosmos.am.model.context.IUserCredentials;
import net.smartcosmos.am.model.context.IUserInformation;
import net.smartcosmos.pojo.base.AccountTypedNamedObject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.stormpath.sdk.account.AccountStatus;

@JsonInclude(value=Include.NON_EMPTY)
public class UserInformation extends AccountTypedNamedObject<IUserInformation>
implements IUserInformation{

	private String surname;
	private String email;
	private String givenName;
	private String middleName;
	private AccountStatus status;
	private String href;
	private ICustomData customData;
	private IUserCredentials credentials;
	private List<IGroup> groups;

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public IUserCredentials getCredentials() {
		return credentials;
	}

	public void setCredentials(IUserCredentials credentials) {
		this.credentials = credentials;
	}

	public ICustomData getCustomData() {
		return customData;
	}

	public void setCustomData(ICustomData customData) {
		this.customData = customData;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		this.type=type;
	}

	public List<IGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<IGroup> groups) {
		this.groups = groups;
	}
	
	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "UserInformation [surname=" + surname + ", email=" + email
				+ ", givenName=" + givenName + ", middleName=" + middleName
				+ ", href=" + href + ", customData=" + customData
				+ ", credentials=" + credentials + ", groups=" + groups + "]";
	}
		
}
