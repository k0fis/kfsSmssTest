package kfs.kfsSmss;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Log service for LOG/DB storage phone statuses
 * 
 * @author Pavel Drimalka
 */
public class log extends HttpServlet {

    private static final String hb[] = new String[]{
        "WSMS:onStartCommand(): alarm heartbeat",
        "WSMS:app heartbeat",
        "WSMS:onStartCommand(): calls heartbeat",};

    static {
        Arrays.sort(hb);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String p = req.getParameter("data");
        if (p != null) {
            Logger.getLogger(log.class.getName()).log(Level.INFO, "{0} -{1} {2}", 
                    new Object[]{getUser(req), Arrays.binarySearch(hb, p)<0 ? "" : " NO LOG:", p});
        }
    }

    private String getUser(HttpServletRequest req) throws ServletException {
        String user = null;
        // check user name
        Principal p = req.getUserPrincipal();
        if (p != null) {
            user = p.getName();
        } else {
            System.err.println("Cannot access without authorization");
            throw new ServletException("Cannot access without authorization");
        }
        return user;
    }
}
