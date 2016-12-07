package join.ctrl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Service.UserServiceImpl;
import model.domain.vo.DeptDivisionVO;
import model.domain.vo.EmployeeDeptVO;


 
@Controller
public class JoinCtrl {

	@Resource(name="UserService")
	private UserServiceImpl service;
	
		//joinForm
		@RequestMapping(value="/joinForm.inc", method=RequestMethod.GET) 
		public String joinForm(Model model) {
			System.out.println("UserCtrl joinForm");
			///////empdept뿌려주기//////
			List<DeptDivisionVO> list = service.selectdeptdiv(); 
			model.addAttribute("lists", list);
			return "join"; 
		}
		
		// join 
		@RequestMapping(value="/join.inc")  
		public String join(EmployeeDeptVO emp) {
			System.out.println("UserCtrl join");
			System.out.println("dept name : "+emp.getDeptname());
					
			service.join(emp);
			return "redirect:/main.inc"; 
		}

		
}
