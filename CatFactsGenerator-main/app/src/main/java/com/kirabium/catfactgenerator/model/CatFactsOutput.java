package com.kirabium.catfactgenerator.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// This class has been generated with https://www.jsonschema2pojo.org/
public class CatFactsOutput implements Serializable
{

    @SerializedName("current_page")
    @Expose
    private Integer currentPage;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("first_page_url")
    @Expose
    private String firstPageUrl;
    @SerializedName("from")
    @Expose
    private Integer from;
    @SerializedName("last_page")
    @Expose
    private Integer lastPage;
    @SerializedName("last_page_url")
    @Expose
    private String lastPageUrl;
    @SerializedName("links")
    @Expose
    private List<Link> links = null;
    @SerializedName("next_page_url")
    @Expose
    private String nextPageUrl;
    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("per_page")
    @Expose
    private Integer perPage;
    @SerializedName("prev_page_url")
    @Expose
    private Object prevPageUrl;
    @SerializedName("to")
    @Expose
    private Integer to;
    @SerializedName("total")
    @Expose
    private Integer total;
    private final static long serialVersionUID = -2988000118926380377L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CatFactsOutput() {
    }

    /**
     * 
     * @param lastPageUrl
     * @param nextPageUrl
     * @param data
     * @param lastPage
     * @param prevPageUrl
     * @param path
     * @param total
     * @param firstPageUrl
     * @param perPage
     * @param from
     * @param links
     * @param to
     * @param currentPage
     */
    public CatFactsOutput(Integer currentPage, List<Datum> data, String firstPageUrl, Integer from, Integer lastPage, String lastPageUrl, List<Link> links, String nextPageUrl, String path, Integer perPage, Object prevPageUrl, Integer to, Integer total) {
        super();
        this.currentPage = currentPage;
        this.data = data;
        this.firstPageUrl = firstPageUrl;
        this.from = from;
        this.lastPage = lastPage;
        this.lastPageUrl = lastPageUrl;
        this.links = links;
        this.nextPageUrl = nextPageUrl;
        this.path = path;
        this.perPage = perPage;
        this.prevPageUrl = prevPageUrl;
        this.to = to;
        this.total = total;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public String getFirstPageUrl() {
        return firstPageUrl;
    }

    public void setFirstPageUrl(String firstPageUrl) {
        this.firstPageUrl = firstPageUrl;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getLastPage() {
        return lastPage;
    }

    public void setLastPage(Integer lastPage) {
        this.lastPage = lastPage;
    }

    public String getLastPageUrl() {
        return lastPageUrl;
    }

    public void setLastPageUrl(String lastPageUrl) {
        this.lastPageUrl = lastPageUrl;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public Object getPrevPageUrl() {
        return prevPageUrl;
    }

    public void setPrevPageUrl(Object prevPageUrl) {
        this.prevPageUrl = prevPageUrl;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(CatFactsOutput.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("currentPage");
        sb.append('=');
        sb.append(((this.currentPage == null)?"<null>":this.currentPage));
        sb.append(',');
        sb.append("data");
        sb.append('=');
        sb.append(((this.data == null)?"<null>":this.data));
        sb.append(',');
        sb.append("firstPageUrl");
        sb.append('=');
        sb.append(((this.firstPageUrl == null)?"<null>":this.firstPageUrl));
        sb.append(',');
        sb.append("from");
        sb.append('=');
        sb.append(((this.from == null)?"<null>":this.from));
        sb.append(',');
        sb.append("lastPage");
        sb.append('=');
        sb.append(((this.lastPage == null)?"<null>":this.lastPage));
        sb.append(',');
        sb.append("lastPageUrl");
        sb.append('=');
        sb.append(((this.lastPageUrl == null)?"<null>":this.lastPageUrl));
        sb.append(',');
        sb.append("links");
        sb.append('=');
        sb.append(((this.links == null)?"<null>":this.links));
        sb.append(',');
        sb.append("nextPageUrl");
        sb.append('=');
        sb.append(((this.nextPageUrl == null)?"<null>":this.nextPageUrl));
        sb.append(',');
        sb.append("path");
        sb.append('=');
        sb.append(((this.path == null)?"<null>":this.path));
        sb.append(',');
        sb.append("perPage");
        sb.append('=');
        sb.append(((this.perPage == null)?"<null>":this.perPage));
        sb.append(',');
        sb.append("prevPageUrl");
        sb.append('=');
        sb.append(((this.prevPageUrl == null)?"<null>":this.prevPageUrl));
        sb.append(',');
        sb.append("to");
        sb.append('=');
        sb.append(((this.to == null)?"<null>":this.to));
        sb.append(',');
        sb.append("total");
        sb.append('=');
        sb.append(((this.total == null)?"<null>":this.total));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.lastPageUrl == null)? 0 :this.lastPageUrl.hashCode()));
        result = ((result* 31)+((this.nextPageUrl == null)? 0 :this.nextPageUrl.hashCode()));
        result = ((result* 31)+((this.data == null)? 0 :this.data.hashCode()));
        result = ((result* 31)+((this.lastPage == null)? 0 :this.lastPage.hashCode()));
        result = ((result* 31)+((this.prevPageUrl == null)? 0 :this.prevPageUrl.hashCode()));
        result = ((result* 31)+((this.path == null)? 0 :this.path.hashCode()));
        result = ((result* 31)+((this.total == null)? 0 :this.total.hashCode()));
        result = ((result* 31)+((this.firstPageUrl == null)? 0 :this.firstPageUrl.hashCode()));
        result = ((result* 31)+((this.perPage == null)? 0 :this.perPage.hashCode()));
        result = ((result* 31)+((this.from == null)? 0 :this.from.hashCode()));
        result = ((result* 31)+((this.links == null)? 0 :this.links.hashCode()));
        result = ((result* 31)+((this.to == null)? 0 :this.to.hashCode()));
        result = ((result* 31)+((this.currentPage == null)? 0 :this.currentPage.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CatFactsOutput) == false) {
            return false;
        }
        CatFactsOutput rhs = ((CatFactsOutput) other);
        return ((((((((((((((this.lastPageUrl == rhs.lastPageUrl)||((this.lastPageUrl!= null)&&this.lastPageUrl.equals(rhs.lastPageUrl)))&&((this.nextPageUrl == rhs.nextPageUrl)||((this.nextPageUrl!= null)&&this.nextPageUrl.equals(rhs.nextPageUrl))))&&((this.data == rhs.data)||((this.data!= null)&&this.data.equals(rhs.data))))&&((this.lastPage == rhs.lastPage)||((this.lastPage!= null)&&this.lastPage.equals(rhs.lastPage))))&&((this.prevPageUrl == rhs.prevPageUrl)||((this.prevPageUrl!= null)&&this.prevPageUrl.equals(rhs.prevPageUrl))))&&((this.path == rhs.path)||((this.path!= null)&&this.path.equals(rhs.path))))&&((this.total == rhs.total)||((this.total!= null)&&this.total.equals(rhs.total))))&&((this.firstPageUrl == rhs.firstPageUrl)||((this.firstPageUrl!= null)&&this.firstPageUrl.equals(rhs.firstPageUrl))))&&((this.perPage == rhs.perPage)||((this.perPage!= null)&&this.perPage.equals(rhs.perPage))))&&((this.from == rhs.from)||((this.from!= null)&&this.from.equals(rhs.from))))&&((this.links == rhs.links)||((this.links!= null)&&this.links.equals(rhs.links))))&&((this.to == rhs.to)||((this.to!= null)&&this.to.equals(rhs.to))))&&((this.currentPage == rhs.currentPage)||((this.currentPage!= null)&&this.currentPage.equals(rhs.currentPage))));
    }

}
