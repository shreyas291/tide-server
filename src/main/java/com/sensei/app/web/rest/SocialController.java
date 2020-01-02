package com.sensei.app.web.rest;

import com.sensei.app.config.ApplicationProperties;
import com.sensei.app.config.Constants;
import com.sensei.app.service.SocialService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.support.URIBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/social")
public class SocialController {

    private final Logger log = LoggerFactory.getLogger(SocialController.class);

    private final SocialService socialService;

    private final ProviderSignInUtils providerSignInUtils;
    
    private final ApplicationProperties applicationProperties;

    public SocialController(SocialService socialService, ProviderSignInUtils providerSignInUtils, ApplicationProperties applicationProperties) {
        this.socialService = socialService;
        this.providerSignInUtils = providerSignInUtils;
        this.applicationProperties = applicationProperties;
    }

    @GetMapping("/signup")
    public RedirectView signUp(WebRequest webRequest, @CookieValue(name = "NG_TRANSLATE_LANG_KEY", required = false, defaultValue = Constants.DEFAULT_LANGUAGE) String langKey) {
        try {
            Connection<?> connection = providerSignInUtils.getConnectionFromSession(webRequest);
            socialService.createSocialUser(connection, langKey.replace("\"", ""));
            return new RedirectView(URIBuilder.fromUri(applicationProperties.getClientProject() + "/#/social-register/" + connection.getKey().getProviderId())
                .queryParam("success", "true")
                .build().toString(), true);
        } catch (Exception e) {
            log.error("Exception creating social user: ", e);
            return new RedirectView(URIBuilder.fromUri(applicationProperties.getClientProject() + "/#/social-register/no-provider")
                .queryParam("success", "false")
                .build().toString(), true);
        }
    }
}
