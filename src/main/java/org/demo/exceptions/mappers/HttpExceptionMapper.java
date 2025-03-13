package org.demo.exceptions.mappers;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.demo.exceptions.ErrorMessage;
import org.demo.exceptions.HttpException;

@Provider
public class HttpExceptionMapper implements ExceptionMapper<HttpException> {
    @Override
    public Response toResponse(HttpException e) {
        return Response.status(Response.Status.NOT_FOUND).entity(new ErrorMessage(e.getStatus(),e.getMessage())).build();
    }
}
