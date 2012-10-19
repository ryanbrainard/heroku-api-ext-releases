package com.heroku.api.releases;

import com.heroku.api.Heroku;
import com.heroku.api.releases.NewReleaseResponse;
import com.heroku.api.exception.HerokuAPIException;
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
public class NewRelease implements Request<NewReleaseResponse> {

    private String app;

    public NewRelease(String app) {
        this.app = app;
    }

    @Override
    public Http.Method getHttpMethod() {
        return Http.Method.GET;
    }

    @Override
    public String getEndpoint() {
        return Heroku.Resource.Releases.format(app) + "/new";
    }

    @Override
    public boolean hasBody() {
        return false;
    }

    @Override
    public String getBody() {
        throw HttpUtil.noBody();
    }

    @Override
    public Http.Accept getResponseType() {
        return Http.Accept.JSON;
    }

    @Override
    public Map<String, String> getHeaders() {
        return Collections.emptyMap();
    }

    @Override
    public NewReleaseResponse getResponse(byte[] body, int code) {
        if (code == 200) {
            return parse(body, getClass());
        } else if (code == 422) {
            throw new HerokuAPIException("Deployment is not enabled for this Heroku account. Please email anand@heroku.com to have it enabled.");
        } else {
            throw new RequestFailedException("Failed to Create New Release", code, body);
        }
    }
}