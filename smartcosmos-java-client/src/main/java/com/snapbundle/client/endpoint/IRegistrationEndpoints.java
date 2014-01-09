package com.snapbundle.client.endpoint;

public interface IRegistrationEndpoints
{
    final String BASE = "/registration";

    final String CREATE_REGISTRATION__PUT = BASE.concat("/register");

    final String CHECK_REALM_AVAILABILITY__GET = BASE.concat("/realm/%s");

    final String CONFIRM_REGISTRATION__GET = BASE.concat("/confirm/%s/%s");
}
