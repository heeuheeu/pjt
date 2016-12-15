package admin.ctrl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Service.UserServiceImpl;
import model.domain.vo.DeptDivisionVO;
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
		List<DeptDivisionVO> divList = service.selectdiv(); 
		model.addAttribute("divlist", divList);
		  
		//dept  
		List<DeptDivisionVO> depList = service.selectdeptdiv();
		model.addAttribute("depList", depList);
		
		//loc
		List<LocationVO> locList = service.selectloc();
		model.addAttribute("locList", locList);
		
		
		return "admin";
	}
	
	
	
	//div add 
	@RequestMapping(value = "/divAdd.inc")
	public String addDiv(DivisionVO div) {
		System.out.println("UserCtrl divAdd");
		System.out.println("div name : " + div.getDivname());		
		service.addDiv(div);
		return "redirect:/adminForm.inc"; 
	}
	
	//dept add 
	@RequestMapping(value = "/deptAdd.inc")
	public String deptAdd(DeptDivisionVO div) {
		System.out.println("UserCtrl deptAdd");
		System.out.println("dept name : " + div.getDeptname());
		System.out.println("div name : "+div.getDivname());
		service.addDept(div);
		return "redirect:/adminForm.inc"; 
	}
	
	//loc add 
	@RequestMapping(value = "/locAdd.inc")
	public String addLoc(LocationVO div) {
		System.out.println("UserCtrl divAdd");
		System.out.println("loc name : " + div.getLocname());
		service.addLoc(div);
		return "redirect:/adminForm.inc"; 
	}
	
	
	
}
