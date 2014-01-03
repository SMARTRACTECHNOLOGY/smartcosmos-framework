package com.snapbundle.client.endpoint;

public interface IAccount
{
    final String BASE = "/account";

    final String CHANGE_MY_PASSWORD__POST = BASE.concat("?resetPassword=false&setPassword=%s");

    final String RESET_MY_PASSWORD__POST = BASE.concat("?resetPassword=true");

}
