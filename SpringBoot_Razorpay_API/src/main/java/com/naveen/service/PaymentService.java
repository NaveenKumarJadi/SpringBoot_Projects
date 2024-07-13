package com.naveen.service;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.naveen.dto.StudentOrder;
import com.naveen.repo.StudentOrderRepo;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class PaymentService {

	@Autowired
	private StudentOrderRepo studentOrderRepo;
	
	private RazorpayClient client;

	@Value("${razorpay.key.id}")
	private String keyId;

	@Value("${razorpay.key.secret}")
	private String keySecret;
	
	public StudentOrder createOrder(StudentOrder studentOrder) throws Exception {

		JSONObject orderRequest = new JSONObject();
		
		orderRequest.put("amount", studentOrder.getAmount() * 100); // amount in paise
		orderRequest.put("currency", "INR");
		orderRequest.put("receipt", String.valueOf(studentOrder.getEmail()));
		
		System.out.println(keyId);
		System.out.println(keySecret);
		
		this.client = new RazorpayClient(keyId, keySecret);
		
		//create order in razorpay
		Order razorPayOrder = client.orders.create(orderRequest);

		studentOrder.setRazorPayOrderId(razorPayOrder.get("id"));
		studentOrder.setOrderStatus(razorPayOrder.get("status"));

		System.out.println(razorPayOrder);
		studentOrderRepo.save(studentOrder);

		return studentOrder;
	}
	
	public StudentOrder verifyPaymentAndUpdateOrderStatus(Map<String, String> respPayload) {
		
		StudentOrder studentOrder = null;
		try {

			String razorpayOrderId = respPayload.get("razorpay_order_id");
			String razorpayPaymentId = respPayload.get("razorpay_payment_id");
			String razorpaySignature = respPayload.get("razorpay_signature");

			// Verify the signature to ensure the payload is genuine
			boolean isValidSignature = verifySignature(razorpayOrderId, razorpayPaymentId, razorpaySignature);

			if (isValidSignature) {
				studentOrder = studentOrderRepo.findByRazorPayOrderId(razorpayOrderId);
				if (studentOrder != null) {
					studentOrder.setOrderStatus("PAYMENT_CONFIRMED");
					studentOrderRepo.save(studentOrder);
				}
			} else {
				System.out.println("invalid");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return studentOrder;
	}

	private boolean verifySignature(String orderId, String paymentId, String signature) throws RazorpayException {
		String generatedSignature = HmacSHA256(orderId + "|" + paymentId, keySecret);
		return generatedSignature.equals(signature);
	}

	private String HmacSHA256(String data, String key) throws RazorpayException {

		try {
			javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA256");
			javax.crypto.spec.SecretKeySpec secretKeySpec = new javax.crypto.spec.SecretKeySpec(key.getBytes(),
					"HmacSHA256");
			mac.init(secretKeySpec);
			byte[] hash = mac.doFinal(data.getBytes());
			return new String(org.apache.commons.codec.binary.Hex.encodeHex(hash));
		} catch (Exception e) {
			throw new RazorpayException("Failed to calculate signature.", e);
		}
	}
	
}
