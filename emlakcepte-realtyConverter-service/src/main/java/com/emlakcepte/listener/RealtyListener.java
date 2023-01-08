package com.emlakcepte.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.emlakcepte.model.Realty;
import com.emlakcepte.model.RealtyType;

@Component
public class RealtyListener {
	public RealtyType RealtyStatusUpdate(Realty realty) {
		realty.setStatus(RealtyType.ACTIVE);
		return realty.getStatus();
	}


}
