package admin.ctrl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Service.UserServiceImpl;
import model.domain.vo.DeptDivisionVO;
import model.domain.vo.DivDeptCheckVO;
import model.domain.vo.DivisionCheckYnVO;
import model.domain.vo.DivisionVO;
import model.domain.vo.LocationVO;

@Controller
public class AdminCtrl {

	@Resource(name = "UserService")
	private UserServiceImpl service;
	

	
	//admin page
	@RequestMapping(value = "/adminForm.inc", method = RequestMethod.GET)
	public String adminForm(Model model) {
		System.out.println("UserCtrl adminForm");
		//div
		List<DivisionCheckYnVO> divList = service.admSelectDiv(); 
		model.addAttribute("divlist", divList);
		  
		//dept  
		List<DivDeptCheckVO> deptList = service.admSelectDeptDiv();
		model.addAttribute("deptList", deptList);
		
		//div - 'Y'
		List<DivisionCheckYnVO> divListY = service.admSelectDivY(); 
		model.addAttribute("divlistY", divListY);
		
		
		//loc
		List<LocationVO> locList = service.admSelectLoc();
		model.addAttribute("locList", locList);
	
		

		
		return "admin";
	}
	
	
	
	//div add 
	@RequestMapping(value = "/divAdd.inc")
	public String addDiv(DivisionVO div) {
		System.out.println("UserCtrl divAdd");

		service.addDiv(div);

		return "redirect:/adminForm.inc"; 
	}
	
	//dept add 
	@RequestMapping(value = "/deptAdd.inc")
	public String deptAdd(DeptDivisionVO div) {
		System.out.println("UserCtrl deptAdd");	
		service.addDept(div);
	
		return "redirect:/adminForm.inc"; 
	}
	
	//loc add 
	@RequestMapping(value = "/locAdd.inc")
	public String addLoc(LocationVO loc) {
		System.out.println("UserCtrl addLoc");

		service.addLoc(loc);

		return "redirect:/adminForm.inc"; 
	}
	
	//div modify
	@RequestMapping(value = "/adminDivModify.inc")
	@ResponseBody
	public boolean adminDivModify(@RequestParam(value = "div") String divid) {
		System.out.println("userCtrl adminDivModify");
				
		String checkyn = service.admDivCheckYN(divid);
		if(checkyn.equals("Y")){
			service.adminDivDel(divid);
		}else{
			service.adminDivAdd(divid);
		}
		
		return true;
	}
	
	//loc modify
	@RequestMapping(value = "/adminLocModify.inc")
	@ResponseBody
	public boolean adminLocModify(@RequestParam(value = "loc") String locid) {
		System.out.println("userCtrl adminLocModify");		
		String checkyn = service.admLocCheckYN(locid);		
				
		if(checkyn.equals("Y")){
			service.adminLocDel(locid);
		}else{
			service.adminLocAdd(locid);
		}
		
		
		return true;
	}

	//dept modify
		@RequestMapping(value = "/adminDeptModify.inc")
		@ResponseBody
		public boolean adminDeptModify(@RequestParam(value = "dept") String deptid) {
			System.out.println("userCtrl adminDeptModify");	
			
			String checkyn = service.admDeptCheckYN(deptid);
					
			if(checkyn.equals("Y")){
				service.adminDeptDel(deptid);
			}else{
				service.adminDeptAdd(deptid);
			}				
			return true;
		}
	
	
}
