package com.heroku.api.releases;

import com.heroku.api.Heroku;
import com.heroku.api.exception.HerokuAPIException;
import com.heroku.api.exception.RequestFailedException;
import com.heroku.api.http.Http;
import com.heroku.api.request.Request;

import java.util.Collections;
import java.util.Map;

import static com.heroku.api.parser.Json.parse;

/**
 * @author Ryan Brainard
 */
public class CreateReleaseRequest implements Request<CreateReleaseResponse> {

    String app;
    CreateReleaseRequest release;


    @Override
    public Http.Method getHttpMethod() {
        return Http.Method.POST;
    }

    @Override
    public String getEndpoint() {
        return Heroku.Resource.Releases.format(app);
    }

    @Override
    public boolean hasBody() {
        return true;
    }

    @Override
    public String getBody() {

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Http.Accept getResponseType() {
        return Http.Accept.JSON
    }

    @Override
    public Map<String, String> getHeaders() {
        return Collections.emptyMap();
    }

    @Override
    public CreateReleaseResponse getResponse(byte[] body, int code) {
        if (code == 200) {
            return parse(body, getClass());
        } else if (code == 422) {
            throw new HerokuAPIException("Deployment is not enabled for this Heroku account. Please email anand@heroku.com to have it enabled.");
        } else {
            throw new RequestFailedException("Failed to Create New Release", code, body);
        }
    }
}
