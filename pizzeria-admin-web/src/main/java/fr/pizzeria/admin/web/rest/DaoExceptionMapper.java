package fr.pizzeria.admin.web.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import fr.pizzeria.exception.DaoException;

@Provider
public class DaoExceptionMapper implements ExceptionMapper<DaoException>{

	@Override
	public Response toResponse(DaoException exception) {
		return Response.status(Status.BAD_REQUEST).build();
	}

}
