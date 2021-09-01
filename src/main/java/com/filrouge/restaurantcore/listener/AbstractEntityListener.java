package com.filrouge.restaurantcore.listener;

import java.time.Instant;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import com.filrouge.restaurantcore.entity.AbstractEntity;


/**
 * Listener to fill in the common attributes of the AbstractEntity.
 * @author Hermann
 *
 */
@Component
public class AbstractEntityListener extends AbstractMongoEventListener<AbstractEntity> {

	@Override
	  public void onBeforeConvert(BeforeConvertEvent<AbstractEntity> event) {
	    super.onBeforeConvert(event);

	    Instant date = Instant.now();
	    
	    event.getSource().setCreateDate(date);
	    event.getSource().setUpdateDate(date);
	  }
}
