package com.filrouge.restaurantcore.aop;

import java.text.MessageFormat;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.filrouge.restaurantcore.util.MessagesUtil;



/**
 * ApectJ pour effectuer un traitement post service métier même en cas d'exception
 * @author pgosse
 *
 */
@Aspect
@Component
public class AfterServiceAOP {

	// Logger exceptions des services métier
	private Logger LOGGER = LoggerFactory.getLogger(AfterServiceAOP.class);
	
	// Key of the message on a job process
	private static final String CLE_MESSAGE_POST_TRAITEMENT = "message.service.postTraitement";

	@After(value = "execution(* com.filrouge.restaurantcore.service.impl.*ServiceImpl.*(..))")
	public void tracer(final JoinPoint joinpoint)
			throws Throwable {
		final MessagesUtil utilitaire = MessagesUtil.getInstance("messages");
		String monMessage = utilitaire.getMessage(CLE_MESSAGE_POST_TRAITEMENT);
		MessageFormat messageFormat = new MessageFormat(monMessage);
		Object[] arguments = { joinpoint.getSignature().toString()};
		final String message = messageFormat.format(arguments);
		LOGGER.error(message);
	}
}
