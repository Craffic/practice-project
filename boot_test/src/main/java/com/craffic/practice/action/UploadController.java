package com.craffic.practice.action;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class UploadController {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    @PostMapping("/upload")
    public String upload(MultipartFile uploadFile, HttpServletRequest req) {
        String realPath = req.getSession().getServletContext().getRealPath("/uploadFile/");
        System.out.println(realPath);
        String format = sdf.format(new Date());
        File folder = new File(realPath + format);
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
        String oldName = uploadFile.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."), oldName.length());
        try {
            uploadFile.transferTo(new File(folder, newName));
            String filePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/uploadFile/" + format + "/" + newName;
            return filePath;
        } catch (IOException e) {
//            e.printStackTrace();
        }
        return "上传失败!";
    }

    @PostMapping("/uploads")
    public List<String> uploads(MultipartFile[] files, HttpServletRequest request){
        String realPath = request.getSession().getServletContext().getRealPath("/uploadFile/");
        String format = sdf.format(new Date());
        List<String> filePaths = new ArrayList<>();
        int length = files.length;
        for (MultipartFile file : files) {
            File folder = new File(realPath + format);
            if (!folder.isDirectory()) {
                folder.mkdirs();
            }
            String oldName = file.getOriginalFilename();
            String newFile = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."), oldName.length());
            try {
                file.transferTo(new File(folder, newFile));
                String filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/uploadFile/" + format + "/" + newFile;
                filePaths.add(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return filePaths;
        }
        return null;
    }

    // TODO: 2021/10/28 多文件上传接收不到文件 BUG
    @RequestMapping("/uploadFiles")
    public String upload(MultipartFile[] uploadFiles, HttpServletRequest request) {
        List list = new ArrayList();//存储生成的访问路径
        if (uploadFiles.length > 0) {
            for (int i = 0; i < uploadFiles.length; i++) {
                MultipartFile uploadFile = uploadFiles[i];
                //设置上传文件的位置在该项目目录下的uploadFile文件夹下，并根据上传的文件日期，进行分类保存
                String realPath = request.getSession().getServletContext().getRealPath("uploadFile");
                String format = sdf.format(new Date());
                File folder = new File(realPath + format);
                if (!folder.isDirectory()) {
                    folder.mkdirs();
                }

                String oldName = uploadFile.getOriginalFilename();
                System.out.println("oldName = " + oldName);
                String newName = UUID.randomUUID().toString() + oldName.
                        substring(oldName.lastIndexOf("."), oldName.length());
                System.out.println("newName = " + newName);
                try {
                    //保存文件
                    uploadFile.transferTo(new File(folder, newName));

                    //生成上传文件的访问路径
                    String filePath = request.getScheme() + "://" + request.getServerName() + ":"+ request.getServerPort() + "/uploadFile" + format + "/" + newName;
                    list.add(filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return list.toString();
        } else if (uploadFiles.length == 0) {
            return "请选择文件";
        }
        return "上传失败";
    }
}
