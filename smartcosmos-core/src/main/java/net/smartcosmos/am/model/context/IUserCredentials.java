package net.smartcosmos.am.model.context;

import net.smartcosmos.am.pojo.context.UserCredentials;
import net.smartcosmos.model.base.IAccountDomainResource;
import net.smartcosmos.model.base.INamedObject;
import net.smartcosmos.model.base.ITypedObject;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonDeserialize(as=UserCredentials.class)
@JsonSerialize(as=UserCredentials.class)
public interface IUserCredentials extends
		IAccountDomainResource<IUserCredentials>,
		INamedObject<IUserCredentials>, ITypedObject {
	
	public String getUsername();

	public void setUsername(String username);

	public String getPassword();

	public void setPassword(String password);
	
}
