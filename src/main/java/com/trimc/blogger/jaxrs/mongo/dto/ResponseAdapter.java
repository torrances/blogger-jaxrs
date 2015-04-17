package com.trimc.blogger.jaxrs.mongo.dto;

import javax.ws.rs.core.Response;

public final class ResponseAdapter {

	public static Response getFail(String type, Long id) {
		return Response.status(404).entity(String.format("Object not found (type = %s, id = %s)", type, id)).build();
	}

	public static Response getSuccess(Object obj) {
		return Response.status(200).entity(obj).build();
	}

	public static Response postFail(String type, Long id) {
		return Response.status(400).entity(String.format("Object not posted (type = %s, id = %s)", type, id)).build();
	}

	public static Response postFail(String type, String payload) {
		return Response.status(400).entity(String.format("Object not posted (type = %s, payload = %s)", type, payload)).build();
	}

	public static Response postSuccess(String type, Long id) {
		return Response.status(200).entity(String.format("Object posted (type = %s, id = %s)", type, id)).build();
	}
}
