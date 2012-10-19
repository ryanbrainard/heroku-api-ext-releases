package com.heroku.api.releases;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Ryan Brainard
 */
public class ReleaseExt implements Serializable {

    String slug_put_key;
    String user;
    Map<String, String> process_types;
    List<String> addons;
    String release_descr;
    Map<String,String> config_vars;
    String stack = "cedar";
    Boolean run_deploy_hooks = true;
    Integer slug_version = 2;
    String head = "0000000"; //TODO: is this sufficient
}


// cstack: String = "cedar", run_deploy_hooks: Boolean = true, slug_version: Int = 2, head: String = CreateRelease.getHeadVal)
