package com.accenture.empportal.filters;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.TeeOutputStream;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;
import java.util.logging.Logger;

@Component
public class RequestResponseLoggers implements Filter {

	static Logger log = Logger.getLogger(RequestResponseLoggers.class.getName());

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		MyCustomHttpRequestWrapper requestWrapper = new MyCustomHttpRequestWrapper((HttpServletRequest) request);

		String uri = requestWrapper.getRequestURI();
		log.info(uri);

		log.info("Requeust Method: {}" + requestWrapper.getMethod());
		String requestData = new String(requestWrapper.getByteArray()).replaceAll("\n", " ");

		log.info("Requeust Body: {}" + requestData);

		MyCustomHttpResponseWrapper responseWrapper = new MyCustomHttpResponseWrapper((HttpServletResponse) response);

		chain.doFilter(requestWrapper, responseWrapper);

		String responseResult = new String(responseWrapper.getBaos().toByteArray());

		log.info("Response status - {}" + responseWrapper.getStatus());
		log.info("Response Body - {}" + responseResult);
	}

	private class MyCustomHttpRequestWrapper extends HttpServletRequestWrapper {

		private byte[] byteArray;

		public MyCustomHttpRequestWrapper(HttpServletRequest request) {
			super(request);
			try {
				byteArray = IOUtils.toByteArray(request.getInputStream());
			} catch (IOException e) {
				throw new RuntimeException("Issue while reading request stream");
			}
		}

		@Override
		public ServletInputStream getInputStream() throws IOException {

			return new MyDelegatingServletInputStream(new ByteArrayInputStream(byteArray));

		}

		public byte[] getByteArray() {
			return byteArray;
		}
	}

	private class MyCustomHttpResponseWrapper extends HttpServletResponseWrapper {

		private ByteArrayOutputStream baos = new ByteArrayOutputStream();

		private PrintStream printStream = new PrintStream(baos);

		public ByteArrayOutputStream getBaos() {
			return baos;
		}

		public MyCustomHttpResponseWrapper(HttpServletResponse response) {
			super(response);
		}

		@Override
		public ServletOutputStream getOutputStream() throws IOException {
			return new MyDelegatingServletOutputStream(new TeeOutputStream(super.getOutputStream(), printStream));
		}

		@Override
		public PrintWriter getWriter() throws IOException {
			return new PrintWriter(new TeeOutputStream(super.getOutputStream(), printStream));
		}
	}
}
