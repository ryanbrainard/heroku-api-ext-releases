import com.heroku.api.App;
import com.heroku.api.SlugInfo;
import com.heroku.api.connection.Connection;
import com.heroku.api.connection.JerseyClientAsyncConnection;
import com.heroku.api.request.ReleasesSlugInfo;
import com.heroku.api.request.app.AppCreate;
import com.heroku.api.request.app.AppDestroy;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author Ryan Brainard
 */
public class ReleasesSlugInfoIT {

    private String apiKey = System.getenv("HEROKU_API_KEY");
    private Connection connection = new JerseyClientAsyncConnection();

    @Test
    public void testGetSlugFromNewApp() throws Exception {
        run(new AppRunnable() {
            @Override
            public void run(App app) throws Exception {
                final SlugInfo slugInfo = connection.execute(new ReleasesSlugInfo(app.getName()), apiKey);
                assertEquals(slugInfo.getSlugUrl(), null);
            }
        });
    }

    void run(AppRunnable runnable) throws Exception {
        App newApp = null;
        try {
            newApp = connection.execute(new AppCreate(new App()), apiKey);
            runnable.run(newApp);
        } finally {
            if (newApp != null) connection.execute(new AppDestroy(newApp.getName()), apiKey);
        }
    }

    interface AppRunnable {
        void run(App app) throws Exception;
    }
}
