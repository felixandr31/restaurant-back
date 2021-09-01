package com.filrouge.restaurantcore.aop;

import java.text.MessageFormat;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.filrouge.restaurantcore.util.MessagesUtil;

/**
 * ApectJ to trace the messages corresponding to the exceptions of the bussiness process
 * process.
 * 
 * @author Hermann
 *
 */
@Aspect
@Component
public class LoggerExceptionAspect {

	// Logger exceptions of job process
	private Logger LOGGER = LoggerFactory.getLogger(LoggerExceptionAspect.class);

	// Key of the message on a job process
	private static final String CLE_MESSAGE_EXCEPTION = "message.service.exception";

	@AfterThrowing(pointcut = "execution(* com.filrouge.springboot.mongodb.service.impl.*ServiceImpl.*(..))", throwing = "ex")
	public void tracerException(final JoinPoint joinpoint, com.filrouge.restaurantcore.exception.FunctionnalException ex)
			throws Throwable {
		final Object[] args = joinpoint.getArgs();
		final StringBuffer sb = new StringBuffer();
		sb.append(joinpoint.getSignature().toString());
		sb.append(" avec les parametres : (");
		for (int i = 0; i < args.length; i++) {
			sb.append(args[i]);
			if (i < args.length - 1) {
				sb.append(", ");
			}
		}
		sb.append(")");

		final MessagesUtil utilitaire = MessagesUtil.getInstance("messages");
		String monMessage = utilitaire.getMessage(CLE_MESSAGE_EXCEPTION);
		MessageFormat messageFormat = new MessageFormat(monMessage);
		Object[] arguments = { sb.toString(), ex.getMessage() };
		final String message = messageFormat.format(arguments);
		LOGGER.error(message);
	}
}
