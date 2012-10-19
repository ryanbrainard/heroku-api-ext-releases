package com.heroku.api.releases;

/**
 * @author Ryan Brainard
 */
public class NewReleaseResponse {

    private String slug_put_url;
    private String slug_put_key;

    public String getSlugPutUrl() {
        return slug_put_url;
    }

    void setSlug_put_url(String slug_put_url) {
        this.slug_put_url = slug_put_url;
    }

    public String getSlugPutKey() {
        return slug_put_key;
    }

    void setSlug_put_key(String slug_put_key) {
        this.slug_put_key = slug_put_key;
    }

    @Override
    public String toString() {
        return "NewReleaseResponse{" +
                "slug_put_url='" + slug_put_url + '\'' +
                ", slug_put_key='" + slug_put_key + '\'' +
                '}';
    }
}
