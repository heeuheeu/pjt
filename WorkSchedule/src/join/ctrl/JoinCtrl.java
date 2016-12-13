package join.ctrl;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import Service.UserServiceImpl;
import model.domain.vo.DeptDivisionVO;
import model.domain.vo.EmployeeDeptDivVO;
import model.domain.vo.EmployeeDeptVO;

@Controller
public class JoinCtrl {

	@Resource(name = "UserService")
	private UserServiceImpl service;

	// joinForm
	@RequestMapping(value = "/joinForm.inc", method = RequestMethod.GET)
	public String joinForm(Model model) {
		System.out.println("UserCtrl joinForm");
		
		 List<DeptDivisionVO> divList = service.selectdiv(); 
		 // System.out.println(">>>>>>>>>>>>>>>>>>>"+divList.get.getDivname());
		  model.addAttribute("divlist", divList);
		  
		  
		List<DeptDivisionVO> list = service.selectdeptdiv();
		model.addAttribute("lists", list);
		return "join";
	}

	// join
	/*
	@RequestMapping(value = "/join.inc")
	public String join(EmployeeDeptDivVO emp) throws Exception {
		System.out.println("UserCtrl join");
		
		MultipartFile f = emp.getFile();
		
		if (!f.isEmpty()) {
			String orgname = f.getOriginalFilename(); 
			String newname = orgname + System.currentTimeMillis() + f.getSize(); 
			String path = servletContext.getRealPath("./resources");
			System.out.println("path : "+path); 
			
			File file = new File(path + File.separator + newname); 
			emp.setEmpimg(newname);
			f.transferTo(file);
			
		}
		service.join(emp);
		
		return "redirect:/main.inc"; 
	}
	*/
	@RequestMapping(value = "/join.inc")
	public String join(EmployeeDeptDivVO emp , HttpServletRequest request) throws Exception {
		System.out.println("UserCtrl join");
		
		MultipartFile f = emp.getFile();
		
		
		System.out.println(">>>>>>>>>>>>.... file name : "+f.getOriginalFilename() );
		emp.setEmpimg(f.getOriginalFilename());
		if( f != null ) {
			 String path = request.getSession().getServletContext().getRealPath("/resources");
			 System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> path : "+path); 
			 
			byte [] data = f.getBytes() ; 
			
			//FileOutputStream out = new FileOutputStream("C:\\Users\\KOSTA\\workspace\\jqueryWEB\\webapps\\upload\\"+file.getOriginalFilename()) ;
			FileOutputStream out = new FileOutputStream(path+"/"+f.getOriginalFilename()) ;
			out.write(data) ; 
		}
		service.join(emp);
		
		return "redirect:/main.inc"; 
	}
	
/*
	@Override
	public void setServletContext(ServletContext arg0) {
		this.servletContext = servletContext;	
	}
	
*/
	// idchk
	@RequestMapping(value = "/idchk.inc")
	@ResponseBody
	public boolean idchk(@RequestParam(value = "id") String empid) {
		System.out.println("UserCtrl idchk");
		EmployeeDeptVO idchk = service.idCheck(empid);
		System.out.println("IDÁßº¹chk!!!!!!!!!!!!!!!!" + idchk);
		boolean flag = false;
		if (idchk == null) {
			flag = true;
		}
		return flag;
	}

	@RequestMapping(value = "/deptSelect.inc")
	 @ResponseBody
	 public List<String> deptSelect(@RequestParam(value = "div") String divname) {
	  System.out.println("Join Ctrl deptSelect");
	  System.out.println("divName!!!"+divname);
	  
	  List<String> deptlist = new ArrayList<String>();
	  deptlist = service.selectboxDept(divname); 

	  for (int i = 0; i < deptlist.size(); i++) {
	   System.out.println(deptlist.get(i));
	  }
	  
	  return deptlist;
	 }


}
