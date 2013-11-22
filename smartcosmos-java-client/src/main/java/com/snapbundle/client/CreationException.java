package com.snapbundle.client;

public class CreationException extends Exception
{
    public CreationException(String message)
    {
        super(message);
    }

    public CreationException(Throwable cause)
    {
        super(cause);
    }
}
