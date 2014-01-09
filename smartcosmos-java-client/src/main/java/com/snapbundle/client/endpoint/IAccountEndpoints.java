package com.snapbundle.client.endpoint;

public interface IAccountEndpoints
{
    final String BASE = "/account";

    final String CHANGE_MY_PASSWORD__POST = BASE.concat("/password?resetPassword=false&setPassword=%s");

    final String RESET_MY_PASSWORD__POST = BASE.concat("/password?resetPassword=true");

}
