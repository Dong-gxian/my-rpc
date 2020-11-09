package com.dgx.myrpc.transport;
//这一部分是javaweb的知识
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * server
 *
 * @author dgx
 */
@Slf4j
public class HTTPTransportServer implements TransportServer {
    private RequestHandler handler;
    private Server server;//server instance of jetty

    @Override
    public void init(int port, RequestHandler handler) { //need help
        this.handler = handler;//real handler to handle requests
        this.server = new Server(port);
        //servlet receive request
        ServletContextHandler ctx = new ServletContextHandler();//ctx is the handler of jetty server
        server.setHandler(ctx);
        ServletHolder holder = new ServletHolder(new RequestServlet());//an abstract of jetty handling request
        ctx.addServlet(holder, "/*");//handle any path
    }

    @Override
    public void start() {
        try {
            server.start();//start jetty server, this method will return immediately
            server.join();//wait server to stop why to prevent  server.start() return immediately?
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void stop() {

    }

    //不对：client->request->jetty server->ctx(server handler)->RequestServlet->RequestHandler->HttpServletResponse->client
    class RequestServlet extends HttpServlet {
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            log.info("client connect");
            InputStream in = req.getInputStream();//get the data from client
            OutputStream out = resp.getOutputStream();//return the results

            if (handler != null) {
                handler.onRequest(in, out);//handle the request
            }
            out.flush();//clean not delete the rest of the data in buffer
        }
    }
}
