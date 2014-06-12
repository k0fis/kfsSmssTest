package kfs.kfsSmss;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static kfs.kfsSmss.xchange.smsConsts.*;
import kfs.kfsSmss.xchange.*;

/**
 * @author Pavel Drimalka
 */
public class sms extends HttpServlet {

    private static final Logger l = Logger.getLogger(sms.class.getName());

    @Override
    public String getServletInfo() {
        return "kfsRealBotTest-sms server";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = getUser(req);
        String cmd = getCommand(req);
        Object o = null;
        if (outgoing.equals(cmd)) {
            o = doOutgoing(user);
        } else if (logout.equals(cmd)) {
            doLogout(req, resp);
        }
        out(resp, o);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = getUser(req);
        String cmd = getCommand(req);
        Gson gs = new Gson();
        Object o = null;
        if (commit.equals(cmd)) {
            o = doCommit(user, gs.fromJson(req.getParameter(varName), smsCommitOut.class));
        } else if (incoming.equals(cmd)) {
            o = doIncoming(user, gs.fromJson(req.getParameter(varName), smsIncoming.class));
        } else if (outgoing.equals(cmd)) {
            o = doOutgoing(user);
        } else if (fail.equals(cmd)) {
            o = doFail(user, gs.fromJson(req.getParameter(varName), smsFailOut.class));
        } else if (logout.equals(cmd)) {
            doLogout(req, resp);
        } else if (callMissed.equals(cmd)) {
            o = doCallMissed(user, gs.fromJson(req.getParameter(varName), call.class));
        } else if (callIncoming.equals(cmd)) {
            o = doCallIncoming(user, gs.fromJson(req.getParameter(varName), call.class));
        } else if (callOutgoing.equals(cmd)) {
            o = doCallOutgoing(user, gs.fromJson(req.getParameter(varName), call.class));
        }
        out(resp, o);
    }

    private void out(HttpServletResponse resp, Object o) throws IOException {
        Gson gs = new Gson();
        resp.setContentType(cntType);
        PrintWriter out = resp.getWriter();
        out.print(gs.toJson(o));
        out.close();
    }

    private String getUser(HttpServletRequest req) throws ServletException {
        String user = null;
        // check user name
        Principal p = req.getUserPrincipal();
        if (p != null) {
            user = p.getName();
        } else {
            l.log(Level.SEVERE, "Cannot access without authorization");
            throw new ServletException("Cannot access without authorization");
        }
        return user;
    }

    private String getCommand(HttpServletRequest req) {
        String cmd = req.getPathInfo();
        if (cmd != null) {
            if (cmd.startsWith("/")) {
                cmd = cmd.substring(1);
            }
        }
        return cmd;
    }

    private void doLogout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("SMSS logout");
        req.getSession().invalidate();
        resp.sendRedirect(serverPostFix);
    }

    private Object doFail(String user, smsFailOut data) {
        if (data == null) {
            l.log(Level.SEVERE, "SMSS fail - input object is null");
            return "input data is null";
        } else {
            l.log(Level.INFO, "SMSS fail - {0} - {1}", new Object[]{data.id, data.mesg});
            return "danke " + user + " fur fail sended " + data.id;
        }
    }

    private Object doCommit(String user, smsCommitOut data) {
        if (data == null) {
            l.log(Level.SEVERE, "SMSS commit - input object is null");
            return "input data is null";
        } else {
            l.log(Level.INFO, "SMSS commit - {0} : {1}", new Object[]{data.id, user});
            return "danke " + user + " fur commit " + data.id;
        }
    }

    private Object doIncoming(String user, smsIncoming data) {
        if (data == null) {
            l.log(Level.SEVERE, "SMSS incoming - input object is null");
            return "input data is null";
        } else {
            l.log(Level.INFO, "SMSS incoming {0} - {1} - {2} - {3}",
                    new Object[]{user, data.numb, data.time, data.text});
            return "danke " + user + " fur incoming " + data.numb;
        }
    }

    private Object doCallMissed(String user, call mc) {
        l.log(Level.INFO, "Missed Call ts: {0}, msisdn: {1}", //
                new Object[]{mc.unxTimeMilisec, mc.msisdn});
        return "danke";
    }

    private Object doCallIncoming(String user, call mc) {
        l.log(Level.INFO, "Incoming Call ts: {0}, msisdn: {1}, durtion: {2}", //
                new Object[]{mc.unxTimeMilisec, mc.msisdn, mc.durationMilisec});
        return "danke";
    }

    private Object doCallOutgoing(String user, call mc) {
        l.log(Level.INFO, "Outgoing Call ts: {0}, msisdn: {1}, durtion: {2}", //
                new Object[]{mc.unxTimeMilisec, mc.msisdn, mc.durationMilisec});
        return "danke";
    }

    private int inx = 0;

    private Object doOutgoing(String user) {
        smsOutgoing oo = new smsOutgoing();
        inx++;
        if (inx % 3 == 0) {
            oo.id = "123";
            oo.numb = "+" + "+4207771234567";
            oo.text = "test sms";
        } else {
            oo.id = null;
            oo.numb = null;
            oo.text = null;
        }
        return oo;
    }
}
