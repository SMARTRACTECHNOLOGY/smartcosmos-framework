package com.snapbundle.util.json;

import com.snapbundle.util.json.JsonGenerationView;

public enum ViewType
{
    Full(JsonGenerationView.Full.class),
    Standard(JsonGenerationView.Standard.class),
    Minimum(JsonGenerationView.Minimum.class),
    Published(JsonGenerationView.Published.class);

    private final Class<? extends JsonGenerationView.Published> viewClass;

    ViewType(Class<? extends JsonGenerationView.Published> viewClass)
    {
        this.viewClass = viewClass;
    }

    public Class<? extends JsonGenerationView.Published> getViewClass()
    {
        return viewClass;
    }
}

