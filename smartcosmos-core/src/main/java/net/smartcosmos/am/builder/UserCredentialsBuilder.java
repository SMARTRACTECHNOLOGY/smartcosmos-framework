package net.smartcosmos.am.builder;

import com.google.common.base.Preconditions;

import net.smartcosmos.am.model.context.IUserCredentials;
import net.smartcosmos.am.pojo.context.UserCredentials;
import net.smartcosmos.builder.AbstractNamedObjectBuilder;

public class UserCredentialsBuilder  extends
AbstractNamedObjectBuilder<IUserCredentials, UserCredentialsBuilder> {

	public UserCredentialsBuilder() {
		super(new UserCredentials());
	}
	
	public UserCredentialsBuilder setUsername(String username) {
		instance.setUsername(username);
		return this;
	}
	
	public UserCredentialsBuilder setPassword(String username) {
		instance.setPassword(username);
		return this;
	}
	public UserCredentialsBuilder setType(String type) {
		instance.setType(type);
		return this;
	}
	
	@Override
	protected void onValidate() {
		Preconditions.checkNotNull(instance.getUsername(),
				"username must not be null");
		Preconditions.checkNotNull(instance.getPassword(),
				"password must not be null");
	}

}
