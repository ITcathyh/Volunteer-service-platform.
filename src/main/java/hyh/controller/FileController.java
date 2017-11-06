package hyh.controller;

import hyh.entity.BaseUser;
import hyh.global.Variable;
import hyh.service.StudentService;
import hyh.service.TeacherService;
import hyh.service.UserInfoService;
import hyh.service.UserService;
import hyh.util.Excel;
import hyh.util.Zip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.HttpHeaders;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class FileController {
    @Autowired
    private UserService userservice;
    @Autowired
    private TeacherService teacherservice;
    @Autowired
    private StudentService studentservice;

    @RequestMapping("/uploadfile")
    @ResponseBody
    public String uploadFile(@RequestParam("file") CommonsMultipartFile files[],
                             HttpServletRequest request, HttpSession session) {
        Object obj = session.getAttribute("uploadtime");

        if (obj == null){
            session.setAttribute("uploadtime", 1);
        }else {
            int time = (Integer)obj;

            if (time >= 2){
                session.setAttribute("lockupload", 1);
                session.removeAttribute("uploadtime");
                return "lockupload";
            } else {
                session.setAttribute("uploadtime", ++time);
            }
        }

        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        int studentid = 1;
        String content = mRequest.getParameter("content");

        try {
            studentid = Integer.valueOf(mRequest.getParameter("studentid"));

            if (files.length < 3) {
                return "notenough";
            } else if (teacherservice.getByStudentid(studentid) == null &&
                    studentservice.getByStudentid(studentid) == null) {
                return "notfound";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String path = Variable.authpath + Excel.getDeaLTime() + "/";
        String filename;
        File file = new File(path);

        if (!file.exists()) {
            file.mkdirs();
        }

        try {
            for (int i = 0; i < files.length; i++) {
                if (!files[i].isEmpty()) {

                    filename = studentid + Character.toString((char) ('a' + i)) + ".jpg";
                    file = new File(path + filename);

                    files[i].transferTo(file);

                } else {
                    return "error";
                }
            }

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path + studentid + "content.txt")));

            out.write(content);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

        return "done";
    }

    @RequestMapping("/adminmakeexcel")
    @ResponseBody
    public String makeExcel(HttpServletRequest request) {
        ArrayList<BaseUser> list = new ArrayList<BaseUser>();
        String path = Variable.excelpath + Excel.getDeaLTime() + "/";

        list.addAll(userservice.getByType(1));

        if (Excel.write(path, "早餐叫醒", list)) {
            list.clear();
            list.addAll(userservice.getByType(2));
            list.addAll(teacherservice.getAll());
            list.addAll(studentservice.getAll());

            if (Excel.write(path, "相约自习", list)) {
                if (Zip.fileToZip(path, Variable.excelpath, "userinfo")) {
                    return "done";
                } else {
                    return "error";
                }
            } else {
                return "error";
            }
        } else {
            return "error";
        }
    }

    @RequestMapping("/adminmakeauthdatazip")
    @ResponseBody
    public String makeAuthDataZip() {
        String path = Variable.authpath + Excel.getDeaLTime() + "/";

        if (Zip.fileToZip(path, Variable.authpath, "authdata")) {
            return "done";
        } else {
            return "error";
        }
    }

    @RequestMapping("/admindownload")
    public String download(@RequestParam("type") String type, @RequestParam("filename") String filename,
                           HttpServletResponse response) {
        try {
            String path;

            if (type.equals("userinfoexcel")) {
                path = Variable.excelpath;
            } else if (type.equals("authdatazip")) {
                path = Variable.authpath;
            } else if (type.equals("errormsg")) {
                path = "";
            } else {
                return "error";
            }

            File file = new File(path + filename);

            if (!file.exists()) {
                return "error";
            }

            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName="
                    + filename);

            InputStream inputStream = new FileInputStream(new File(path + filename));
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;

            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }

            os.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
