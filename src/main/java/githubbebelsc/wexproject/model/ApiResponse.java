package githubbebelsc.wexproject.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ApiResponse {
    private List<ExchangeRate> data;

    @JsonIgnore
    private Meta meta;

    @JsonIgnore
    private Links links;

    public List<ExchangeRate> getData() {
        return data;
    }
    public void setData(List<ExchangeRate> data) {
        this.data = data;
    }
    public Meta getMeta() {
        return meta;
    }
    public void setMeta(Meta meta) {
        this.meta = meta;
    }
    public Links getLinks() {
        return links;
    }
    public void setLinks(Links links) {
        this.links = links;
    }

}
