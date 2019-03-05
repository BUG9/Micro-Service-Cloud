package com.zhc.msccommon.fileController;

import java.io.File;
import java.io.IOException;
import java.util.List;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.annotations.Api;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jingxian on 2019/3/1.
 */
@Controller
@Api(tags = "文件上传模块", description = "主要为移动端和嵌入式端提供app安装包和固件的上传功能")
public class FileController {
    private static final Logger log = LoggerFactory.getLogger(FileController.class);

    @Value("${appFilePath}")
    private String appFilePath;

    @Value("${firmwareFilePath}")
    private String firmwareFilePath;

    @ApiOperation(value = "移动端上传接口", notes = "移动端单文件上传")
    @PostMapping(value = "/app/upload")
    @ResponseBody
    public String appUpload(@RequestParam("file") MultipartFile file) {
        String x = upload(file,"app");
        if (x != null) return x;
        return "上传失败";
    }

    @ApiOperation(value = "嵌入式端上传接口", notes = "嵌入式单文件上传")
    @PostMapping(value = "/firmware/upload")
    @ResponseBody
    public String firmwareUpload(@RequestParam("file") MultipartFile file) {
        String x = upload(file,"firmware");
        if (x != null) return x;
        return "上传失败";
    }

    @ApiOperation(value = "移动端上传接口", notes = "移动端多文件上传")
    @PostMapping(value = "/app/uploads")
    @ResponseBody
    public String appUpload(HttpServletRequest request) {
        String x = multiUpload(request,"firmware");
        if (x != null) return x;
        return "上传失败";
    }

    @ApiOperation(value = "嵌入式端上传接口", notes = "嵌入式多文件上传")
    @PostMapping(value = "/firmware/uploads")
    @ResponseBody
    public String firmwareUpload(HttpServletRequest request) {
        String x = multiUpload(request,"firmware");
        if (x != null) return x;
        return "上传失败";
    }


    private String multiUpload(HttpServletRequest request ,String type) {
        String result = "";
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        String filePath = "";
        if ("app".equals(type)) {
            // 设置文件存储路径
            filePath = appFilePath;
        }
        if ("firmware".equals(type)) {
            // 设置文件存储路径
            filePath = firmwareFilePath;
        }

        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            if (file.isEmpty()) {
                result = "第" + (i + 1) + "个文件为空";
            }
            String fileName = file.getOriginalFilename();

            File dest = new File(filePath + fileName);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            try {
                file.transferTo(dest);
                log.info("第" + (i + 1) + "个文件上传成功");
                result = ("第" + (i + 1) + "个文件上传成功");
            } catch (IOException e) {
                log.error(e.toString(), e);
                result =  "上传第" + (i + 1) + "个文件失败";
            }
        }
        return result;
    }


    private String upload(MultipartFile file , String type) {
        try {
            if (file.isEmpty()) {
                return "文件为空";
            }
            // 获取文件名
            String fileName = file.getOriginalFilename();
            log.info("上传的文件名为：" + fileName);
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            log.info("文件的后缀名为：" + suffixName);

            String path = "";
            if ("app".equals(type)) {
                // 设置文件存储路径
                path = appFilePath + fileName;
            }
            if ("firmware".equals(type)){
                // 设置文件存储路径
                path = firmwareFilePath + fileName;
            }
            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
            return "上传成功";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = {"/"} ,method = RequestMethod.GET)
    public String index(){
        return "index";
    }
}
