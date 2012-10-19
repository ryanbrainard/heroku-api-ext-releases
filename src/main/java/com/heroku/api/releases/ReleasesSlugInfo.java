package com.heroku.api.releases;

import com.heroku.api.releases.SlugInfo;
import com.heroku.api.exception.RequestFailedException;
import com.heroku.api.http.Http;
import com.heroku.api.http.HttpUtil;
import com.heroku.api.request.Request;

import java.util.Collections;
import java.util.Map;

import static com.heroku.api.parser.Json.parse;

/**
 * @author Ryan Brainard
 */
public class ReleasesSlugInfo implements Request<SlugInfo> {

    private final String appName;

    public ReleasesSlugInfo(String appName) {
        this.appName = appName;
    }

    public Http.Method getHttpMethod() {
        return Http.Method.GET;
    }

    public String getEndpoint() {
        return "/apps/" + appName + "/release_slug";
    }

    public boolean hasBody() {
        return false;
    }

    public String getBody() {
        throw HttpUtil.noBody();
    }

    public Http.Accept getResponseType() {
        return Http.Accept.JSON;
    }

    public Map<String, String> getHeaders() {
        return Collections.emptyMap();
    }

    public SlugInfo getResponse(byte[] bytes, int status) {
        if (Http.Status.OK.equals(status)){
            return parse(bytes, getClass());
        } else {
            throw new RequestFailedException("Failed to get slug info", status, bytes);
        }
    }
}
