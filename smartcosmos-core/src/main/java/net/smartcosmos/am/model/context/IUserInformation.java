package net.smartcosmos.am.model.context;

import java.util.List;

import net.smartcosmos.am.pojo.context.UserInformation;
import net.smartcosmos.model.base.IAccountDomainResource;
import net.smartcosmos.model.base.INamedObject;
import net.smartcosmos.model.base.ITypedObject;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.stormpath.sdk.account.AccountStatus;

@JsonDeserialize(as=UserInformation.class)
@JsonSerialize(as=UserInformation.class)
public interface IUserInformation extends IAccountDomainResource<IUserInformation>,
INamedObject<IUserInformation>, ITypedObject {

	public String getSurname() ;

	public void setSurname(String surname) ;

	public String getEmail() ;

	public void setEmail(String email) ;

	public String getGivenName() ;

	public void setGivenName(String givenName) ;

	public String getMiddleName() ;

	public void setMiddleName(String middleName) ;

	public String getHref() ;

	public void setHref(String href) ;

	public IUserCredentials getCredentials() ;

	public void setCredentials(IUserCredentials credentials);

	public ICustomData getCustomData() ;

	public void setCustomData(ICustomData customData);
	
	public List<IGroup> getGroups() ;

	public void setGroups(List<IGroup> groups) ;

	public void setStatus(AccountStatus status) ;
	
	public AccountStatus getStatus();
}
