package com.thoughtworks.clean.infrastructure.web;

import com.thoughtworks.clean.delivery.web.controller.AccountController;
import com.thoughtworks.clean.infrastructure.Configuration;

import static spark.Spark.before;
import static spark.Spark.port;
import static spark.Spark.post;

public class WebApp {

    private final AccountController accountController;
    private final Configuration configuration;

    public WebApp(Configuration configuration, AccountController accountController) {
        this.configuration = configuration;
        this.accountController = accountController;
    }

    public void start() {
        port(configuration.getPort());
        before("/*", (request, response) -> request.attribute("objectMapper", configuration.getObjectMapper()));
        post("/transfer", accountController::transfer, configuration.getJsonTransformer());
    }
}
