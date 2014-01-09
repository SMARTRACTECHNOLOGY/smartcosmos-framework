package com.snapbundle.client.endpoint;

public interface IUserEndpoints
{
    final String BASE = "/users";

    final String CREATE__PUT = BASE;

    final String FIND_BY_URN__GET = BASE.concat("/%s?view=%s");

    final String FIND_BY_EMAIL__GET = BASE.concat("/user/%s?view=%s");

    final String CHANGE_USER_PASSWORD__POST = BASE.concat("/user/%s?resetPassword=false&setPassword=%s");

    final String RESET_USER_PASSWORD__POST = BASE.concat("/user/%s?resetPassword=true");

    final String UPDATE__POST = BASE;
}

