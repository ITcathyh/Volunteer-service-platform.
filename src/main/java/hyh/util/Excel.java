package hyh.util;

import hyh.action.FileAction;
import hyh.entity.BaseUser;
import hyh.entity.Student;
import hyh.entity.Teacher;
import hyh.global.Variable;
import hyh.service.StudentService;
import hyh.service.TeacherService;
import hyh.service.UserService;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Stack;
import java.util.concurrent.Callable;

public final class Excel {
    private static Logger log = Logger.getLogger("hyh.tuil.Excel");

    public static boolean write(UserService userservice, TeacherService teacherservice,
                                StudentService studentservice, String path) {
        ArrayList<BaseUser> list = new ArrayList<BaseUser>();

        list.addAll(userservice.getByType(1));

        if (Excel.write(path, "早餐叫醒", list)) {
            list.clear();
            list.addAll(userservice.getByType(2));
            list.addAll(teacherservice.getAll());
            list.addAll(studentservice.getAll());

            return Excel.write(path, "相约自习", list);
        } else {
            return false;
        }
    }

    public static boolean write(String path, String filenmame, ArrayList<BaseUser> list) {
        if (!makeFolder(path)) {
            Variable.errornum++;
            log.error("make folder error\n");
            return false;
        }

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(filenmame);
        HSSFRow row = sheet.createRow(0);
        BaseUser user;

        writeTitle(row);

        for (int i = 0; i < list.size(); i++) {
            user = list.get(i);
            row = sheet.createRow(i + 1);

            write(row, user);
        }

        return writeToFile(wb, path + filenmame + ".xls");
    }

    private static void write(HSSFRow row, BaseUser user) {
        String strs[] = user.makeStrings();

        for (int i = 0; i < strs.length; i++) {
            row.createCell(i).setCellValue(strs[i]);
        }
    }

    private static void writeTitle(HSSFRow row) {
        int i = 0;

        row.createCell(i++).setCellValue("姓名");
        row.createCell(i++).setCellValue("学号");
        row.createCell(i++).setCellValue("qq");
        row.createCell(i++).setCellValue("手机号");
        row.createCell(i++).setCellValue("邮箱");
        row.createCell(i++).setCellValue("配对的学号");
        row.createCell(i).setCellValue("活动类型");
    }

    private static boolean writeTitle(HSSFRow row, String...strs) {
        int i = 0;

        for (String str : strs){
            if (str == null){
                return false;
            }

            row.createCell(i++).setCellValue(str);
        }

        return true;
    }

    private static boolean makeFolder(String foldername) {
        File file = new File(foldername);

        if (!file.exists()) {
            if (!file.mkdirs()) {
                return false;
            }
        }

        return true;
    }

    private static boolean writeToFile(HSSFWorkbook wb, String filename) {
        try {
            FileOutputStream out = new FileOutputStream(filename);

            wb.write(out);
            out.close();
        } catch (Exception e) {
            Variable.errornum++;
            log.error("write xls error\n" + e + "\n");
            return false;
        }

        return true;
    }
}
