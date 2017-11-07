package hyh.action;

import hyh.global.Variable;

public class FileAction {
    private static final String EXCEL_PATH = "pair/excel/";
    private static final String AUTH_DATA_PATH = "pair/auth/";

    public static String getPath(String type){
        if (type.equals("userinfoexcel")) {
            return EXCEL_PATH;
        } else if (type.equals("authdatazip")) {
            return AUTH_DATA_PATH;
        } else if (type.equals("errormsg")) {
            return "";
        } else {
            return null;
        }
    }

    public static String getExcelPath() {
        return EXCEL_PATH;
    }

    public static String getAuthDataPath() {
        return AUTH_DATA_PATH;
    }
}
