package com.heroku.api;

/**
 * @author Ryan Brainard
 */
public class SlugInfo {

    String name;
    String slug_version;
    String slug_url;

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public String getSlugVersion() {
        return slug_version;
    }

    void setSlug_version(String slug_version) {
        this.slug_version = slug_version;
    }

    public String getSlugUrl() {
        return slug_url;
    }

    void setSlug_url(String slug_url) {
        this.slug_url = slug_url;
    }

    @Override
    public String toString() {
        return "SlugInfo{" +
                "name='" + name + '\'' +
                ", slug_version='" + slug_version + '\'' +
                ", slug_url='" + slug_url + '\'' +
                '}';
    }
}
