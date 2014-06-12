package kfs.kfsSmss.xchange;


/**
 * Text constants for exchange communication droid/tomcat
 *
 * @author Pavel Drimalka
 */
public interface smsConsts {

    /**
     * Call this command for handset incoming SMS from GSM and post this sms on the server
     * <br/><br/>
     * <b>"i"</b>
     * postfix after serverPostFix. SMSS implements in POST action and
     * waiting for JSON for smsIncoming message in http variable varName
     *
     * @see #serverPostFix
     * @see #varName
     * @see smsIncoming smsIncoming
     */
    public static String incoming = "i";

    /**
     * Call this command for obtain data for send SMS from server to the handset/gsm
     * <br/><br/>
     * <b>"o"</b>
     * postfix after serverPostFix. SMSS implements it in both action (POST, GET)
     * and returns null or JSON smsOutgoing. This command don't awaits any input data
     *
     * @see #serverPostFix
     * @see #varName
     * @see smsOutgoing
     */
    public static String outgoing = "o";

    /**
     * Call this command with correctly sent SMS - commit this SMS on server
     * <br/><br/>
     * <b>"c"</b>
     * postfix after serverPostFox. SMSS implements this command in POST action and
     * awaits JSON for class smsCommitOut
     *
     * @see #serverPostFix
     * @see #varName
     * @see smsCommitOut
     */
    public static String commit = "c";

    /**
     * Call this command with error during send SMS optionally with error message
     * <br/><br/>
     * <b>"f"</b>
     * postfix after serverPostFox. SMSS implements this command in POST action and
     * awaits JSON for class smsFailOut
     *
     * @see #serverPostFix
     * @see #varName
     * @see smsFailOut     *
     */
    public static String fail = "f";

    /**
     * Call this command logout handset from server
     * <br/><br/>
     * <b>"q"</b>
     * postfix after serverPostFox. SMSS implements this command in POST and GET action and
     * don't awaits any input
     *
     * @see #serverPostFix
     */
    public static String logout = "q";
    
    public static String callMissed = "cm";
    public static String callIncoming = "ci";
    public static String callOutgoing = "co";

    /**
     * <b>"data"</b><br/><br/>
     * HTTP variable name for data in POST action
     */
    public static String varName = "data";

    /**
     * <b>/kfsRealBotSmss/sms/</b><br/><br/>
     * SMSS server postfix
     */
    public static String serverPostFix = "/kfsRealBotSmss/sms/";

    /**
     * <b>text/html;charset=UTF-8</b><br/><br/>
     * SMSS server content type.
     */
    public static String cntType = "text/html;charset=UTF-8";

    /**
     * format date string for date formating. Use: String.format(fmtDate, new Date());
     *
     * @see String#format(String, Object []) String.format
     *
     */
    public static String fmtDate = "%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS";

}
