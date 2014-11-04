package net.smartcosmos.am.builder;

import java.util.List;

import net.smartcosmos.am.model.context.ICustomData;
import net.smartcosmos.am.model.context.IGroup;
import net.smartcosmos.am.model.context.IUserCredentials;
import net.smartcosmos.am.model.context.IUserInformation;
import net.smartcosmos.am.pojo.context.UserInformation;
import net.smartcosmos.builder.AbstractNamedObjectBuilder;

import com.stormpath.sdk.account.AccountStatus;

public class UserInformationBuilder extends
		AbstractNamedObjectBuilder<IUserInformation, UserCredentialsBuilder> {

	public UserInformationBuilder() {
		super(new UserInformation());
	}


	public UserInformationBuilder setSurname(String surname) {
		instance.setSurname(surname);
		return this;
	}

	public UserInformationBuilder setEmail(String email) {
		instance.setEmail(email);
		return this;

	}

	public UserInformationBuilder setGivenName(String givenName) {
		instance.setGivenName(givenName);
		return this;
	}

	public UserInformationBuilder setMiddleName(String middleName) {
		instance.setMiddleName(middleName);
		return this;
	}
	
	public UserInformationBuilder setHref(String href) {
		instance.setHref(href);
		return this;
	}

	public UserInformationBuilder setCredentials(IUserCredentials credentials) {
		instance.setCredentials(credentials);
		return this;
	}

	public UserInformationBuilder setCustomData(ICustomData customData) {
		instance.setCustomData(customData);
		return this;
	}
	public UserInformationBuilder setGroups(List<IGroup> groups) {
		instance.setGroups(groups);
		return this;
	}
	public UserInformationBuilder setType(String type) {
		instance.setType(type);
		return this;
	}

	public UserInformationBuilder setStatus(AccountStatus status) {
		instance.setStatus(status);
		return this;
	}
}
