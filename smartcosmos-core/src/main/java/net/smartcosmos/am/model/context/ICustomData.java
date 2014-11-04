package net.smartcosmos.am.model.context;

import net.smartcosmos.am.pojo.context.CustomData;
import net.smartcosmos.model.base.IAccountDomainResource;
import net.smartcosmos.model.base.INamedObject;
import net.smartcosmos.model.base.ITypedObject;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonDeserialize(as=CustomData.class)
@JsonSerialize(as=CustomData.class)
//@JsonInclude(Include.NON_NULL)
public interface ICustomData extends IAccountDomainResource<ICustomData>,
INamedObject<ICustomData>, ITypedObject{
	
	public String getName() ;

	public void setName(String name) ;

	public String getValue() ;

	public void setValue(String value);

}
