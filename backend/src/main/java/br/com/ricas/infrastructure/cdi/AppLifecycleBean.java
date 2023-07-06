// =====================================================
// Project: mja-api
// (c) Heike Winkelvoß
// =====================================================
package br.com.ricas.infrastructure.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.runtime.StartupEvent;

/**
 * AppLifecycleBean
 */
@ApplicationScoped
public class AppLifecycleBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppLifecycleBean.class);

	@ConfigProperty(name = "quarkus.oidc.credentials.secret")
	String clientSecret;

	void onStartup(@Observes final StartupEvent ev) {

		LOGGER.info(" ===========>  clientSecret={}", clientSecret);

	}

}
