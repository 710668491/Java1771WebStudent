package FileUpload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;


public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("FileUploadServlet.doPost()");
		//1.DiskFileItemFactory：磁盘文件项工厂--一些相关的配置的设置 : 缓存的大小 ,临时目录的位置
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 1M=1024KB  1KB=1024B
		factory.setSizeThreshold(10*1024*1024);//设置缓存大小
		String tempPath = getServletContext().getRealPath("temp");
		factory.setRepository(new File(tempPath));//设置临时文件的目录
		
		//2.ServletFileUplaod：文件上传的一个核心类
		ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
		//设置上传的文件名的编码方式
		servletFileUpload.setHeaderEncoding("UTF-8");
		
		//1、通过request获取请求体的内容。
		if (servletFileUpload.isMultipartContent(req)) {
			//2、解析请求体：根据分隔符boundary将请求体的内容分割后放在一个数组中，数组中每一项是一个表单项FileItem。
			try {
				List<FileItem> list = servletFileUpload.parseRequest(req);
				//3、数组遍历，区分哪些是普通表单项，哪些是上传的表单项。
				if (list != null) {
					for (FileItem fileItem : list) {
						//3.1普通表单：name=zhangsan
						if (fileItem.isFormField()) {//普通表单
							String fieldName = fileItem.getFieldName();
							String fieldValue = fileItem.getString("UTF-8");
							System.out.println("fieldName: " + fieldName + ", fieldValue:" + fieldValue);
						} else {//3.2文件上传表单项：
							String fileName = fileItem.getName();
							//获得上传文件存档的位置
							String uploadPath = getServletContext().getRealPath("upload");
							InputStream inputStream = fileItem.getInputStream();
							OutputStream outputStream = new FileOutputStream(uploadPath + "/" + fileName);
							IOUtils.copy(inputStream, outputStream);
							outputStream.close();
							inputStream.close();
							
							//删除临时文件(删除临时存放temp下的文件)
							fileItem.delete();
						}
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
		}
	}
}
