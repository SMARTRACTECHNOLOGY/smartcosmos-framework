package net.smartcosmos.am.pojo.context;

import net.smartcosmos.am.model.context.IGroup;
import net.smartcosmos.pojo.base.AccountTypedNamedObject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.stormpath.sdk.group.GroupStatus;

@JsonInclude(value=Include.NON_EMPTY)
public class Group  extends AccountTypedNamedObject<IGroup> implements IGroup{
	private String name;
	private String description;
	private String href;
	private GroupStatus status;

	public Group() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public GroupStatus getStatus() {
		return status;
	}

	public void setStatus(GroupStatus status) {
		this.status = status;
	}
	
	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		this.type=type;
	}

	@Override
	public String toString() {
		return "Group [name=" + name + ", description=" + description
				+ ", href=" + href + ", status=" + status + "]";
	}	
	
}
