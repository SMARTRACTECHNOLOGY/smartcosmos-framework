package com.snapbundle.client.endpoint;

import com.snapbundle.util.json.ViewType;

public class UserEndpoints implements IUserEndpoints
{
    public static String create()
    {
        return CREATE__PUT;
    }

    public static String update()
    {
        return UPDATE__POST;
    }

    public static String findByUrn(String urn)
    {
        return findByUrn(urn, ViewType.Standard);
    }

    public static String findByUrn(String urn, ViewType viewType)
    {
        return String.format(FIND_BY_URN__GET, urn, viewType);
    }

    public static String findByEmailAddress(String emailAddress)
    {
        return findByEmailAddress(emailAddress, ViewType.Standard);
    }

    public static String findByEmailAddress(String emailAddress, ViewType viewType)
    {
        return String.format(FIND_BY_EMAIL__GET, emailAddress, viewType);
    }

    public static String changeMyPassword(String userEmailAddress, String setPassword)
    {
        return String.format(CHANGE_USER_PASSWORD__POST, userEmailAddress, setPassword);
    }

    public static String resetMyPassword(String userEmailAddress)
    {
        return String.format(RESET_USER_PASSWORD__POST, userEmailAddress);
    }
}
