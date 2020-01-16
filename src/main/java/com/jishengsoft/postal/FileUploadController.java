package com.jishengsoft.postal;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jishengsoft.mapper.SendApplyMapper;
import com.jishengsoft.pojo.StringResult;
import com.jishengsoft.util.CCRDFile;
import com.jishengsoft.util.DateUtil;

@RestController
public class FileUploadController {
	
	@Autowired
	private SendApplyMapper sam;
	
	private final ResourceLoader resourceLoader; 
	 @Autowired
	 public FileUploadController(ResourceLoader resourceLoader) { 
	 this.resourceLoader = resourceLoader; 
	 } 

	@RequestMapping(value = "/testUploadFile", method = RequestMethod.POST)
	  public StringResult testUploadFile(HttpServletRequest req,
	      MultipartHttpServletRequest multiReq) {
	    // 获取上传文件的路径
		StringResult sr= new StringResult();
	    String uploadFilePath = multiReq.getFile("interPDF").getOriginalFilename();
	    System.out.println("uploadFlePath:" + uploadFilePath);
	    // 截取上传文件的文件名
	    String uploadFileName = uploadFilePath.substring(
	        uploadFilePath.lastIndexOf('\\') + 1, uploadFilePath.indexOf('.'));
	    System.out.println("multiReq.getFile()" + uploadFileName);
	    
	    uploadFileName = DateUtil.getNow("yyyyMMddhhmmss")+(int)(Math.random() * 10000);
	    // 截取上传文件的后缀
	    String uploadFileSuffix = uploadFilePath.substring(
	        uploadFilePath.indexOf('.') + 1, uploadFilePath.length());
	    System.out.println("uploadFileSuffix:" + uploadFileSuffix);
	    FileOutputStream fos = null;
	    FileInputStream fis = null;
	    try {
	      fis = (FileInputStream) multiReq.getFile("interPDF").getInputStream();
	      String realPath = "src\\main\\resources\\static\\uploadFiles\\";
	      CCRDFile.createDir(realPath);
	      
	      fos = new FileOutputStream(new File(realPath + uploadFileName
	          + ".")
	          + uploadFileSuffix);
	      sr.setResult(uploadFileName+"."+uploadFileSuffix);
	      byte[] temp = new byte[1024];
	      int i = fis.read(temp);
	      while (i != -1){
	        fos.write(temp,0,temp.length);
	        fos.flush();
	        i = fis.read(temp);
	      }
	    } catch (IOException e) {
	      e.printStackTrace();
	    } finally {
	      if (fis != null) {
	        try {
	          fis.close();
	        } catch (IOException e) {
	          e.printStackTrace();
	        }
	      }
	      if (fos != null) {
	        try {
	          fos.close();
	        } catch (IOException e) {
	          e.printStackTrace();
	        }
	      }
	    }
	    return sr;
	  }
	
	@RequestMapping(value = "/testDownload/{id}", method = RequestMethod.GET)
	  public void testDownload(HttpServletResponse res,HttpServletRequest req,@PathVariable int id) {
		
		String filename = sam.getFilenameById(id);
	    res.setHeader("content-type", "application/octet-stream");
	    res.setContentType("application/octet-stream");
	    res.setHeader("Content-Disposition", "attachment;filename=" + filename);
	    byte[] buff = new byte[1024];
	    BufferedInputStream bis = null;
	    OutputStream os = null;
	    try {
	      os = res.getOutputStream();
	      String realPath = "src\\main\\resources\\static\\uploadFiles\\";
	      bis = new BufferedInputStream(new FileInputStream(new File(realPath
	          + filename)));
	      int i = bis.read(buff);
	      while (i != -1) {
	        os.write(buff, 0, buff.length);
	        os.flush();
	        i = bis.read(buff);
	      }
	    } catch (IOException e) {
	      e.printStackTrace();
	    } finally {
	      if (bis != null) {
	        try {
	          bis.close();
	        } catch (IOException e) {
	          e.printStackTrace();
	        }
	      }
	    }
	    System.out.println("success");
	  }
	
	//显示图片的方法关键 匹配路径像 localhost:8080/b7c76eb3-5a67-4d41-ae5c-1642af3f8746.png 
	 @RequestMapping(method = RequestMethod.GET, value = "/getfile/{filename:.+}") 
	 @ResponseBody
	 public ResponseEntity<?> getFile(@PathVariable String filename) { 
	  
	 try { 
	  return ResponseEntity.ok(resourceLoader.getResource("file:" + "src\\main\\resources\\static\\uploadFiles\\"+filename)); 
	 } catch (Exception e) { 
	  return ResponseEntity.notFound().build(); 
	 } 
	 } 
	

}