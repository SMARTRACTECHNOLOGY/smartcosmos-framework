package net.smartcosmos.am.service.config.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;


public class StormpathConfiguration {
	@JsonProperty
	private boolean enabled;
	@JsonProperty
	@NotNull
	private String apiKeyId;
	@JsonProperty
	@NotNull
	private String apiKeySecret;
	@JsonProperty
	@NotNull
	private String applicationRestUrl;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getApiKeyId() {
		return apiKeyId;
	}

	public void setApiKeyId(String apiKeyId) {
		this.apiKeyId = apiKeyId;
	}

	public String getApiKeySecret() {
		return apiKeySecret;
	}

	public void setApiKeySecret(String apiKeySecret) {
		this.apiKeySecret = apiKeySecret;
	}

	public String getApplicationRestUrl() {
		return applicationRestUrl;
	}

	public void setApplicationRestUrl(String applicationRestUrl) {
		this.applicationRestUrl = applicationRestUrl;
	}

}