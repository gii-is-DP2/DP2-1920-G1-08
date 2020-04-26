package org.springframework.inmocasa.web;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.configuration.PaypalPaymentIntent;
import org.springframework.inmocasa.configuration.PaypalPaymentMethod;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.service.PaypalService;
import org.springframework.inmocasa.service.ViviendaService;
import org.springframework.inmocasa.util.URLUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

@Controller
@RequestMapping("/")
public class PaymentController {
	
	public static final String PAYPAL_SUCCESS_URL = "pay/success";
	public static final String PAYPAL_CANCEL_URL = "pay/cancel";
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PaypalService paypalService;
	
	@PostMapping("pay/{viviendaId}")
	public String pay(HttpServletRequest request, @PathVariable("viviendaId") int viviendaId, ModelMap model){
		String cancelUrl = URLUtils.getBaseURl(request) + "/" + PAYPAL_CANCEL_URL;
		String successUrl = URLUtils.getBaseURl(request) + "/" + PAYPAL_SUCCESS_URL;
		model.addAttribute("viviendaId", viviendaId);
		try {
			Payment payment = paypalService.createPayment(
					50, 
					"EUR", 
					PaypalPaymentMethod.paypal, 
					PaypalPaymentIntent.sale,
					"publicitar vivienda", 
					cancelUrl, 
					successUrl);
			for(Links links : payment.getLinks()){
				if(links.getRel().equals("approval_url")){
					return "redirect:" + links.getHref();
				}
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/";
	}

	@PostMapping(PAYPAL_CANCEL_URL)
	public String cancelPay(){
		return "redirect:/";
	}

	@GetMapping(PAYPAL_SUCCESS_URL)
	public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, Integer viviendaId, ModelMap model){
		try {
			Payment payment = paypalService.executePayment(paymentId, payerId);
			if(payment.getState().equals("approved")){
				viviendaId = (Integer) model.getAttribute("viviendaId");
				return "viviendas/publicitar/"+viviendaId;
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/";
	}
}
