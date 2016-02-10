package ${package}.server;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EndpointsFactory
{
    @JsonProperty
    private Boolean exampleEndpoints = true;

    @JsonProperty
    private Boolean moreInterestingExampleEndpoints = true;

    public Boolean getExampleEndpoints()
    {
        return exampleEndpoints;
    }

    public Boolean getMoreInterestingExampleEndpoints()
    {
        return moreInterestingExampleEndpoints;
    }
}
