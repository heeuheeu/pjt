package join.ctrl;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
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
		List<DeptDivisionVO> list = service.selectdeptdiv();
		
		model.addAttribute("divlist", divList);
		model.addAttribute("lists", list);
		return "join";
	}

	
	@RequestMapping(value = "/join.inc")
	public String join(EmployeeDeptDivVO emp, HttpServletRequest request) throws Exception {
		System.out.println("UserCtrl join");

		MultipartFile f = emp.getFile();

		if ( f.getOriginalFilename() == null || f.getOriginalFilename() == "" ) {
			emp.setEmpimg("default-img.png");
		} else  {
			emp.setEmpimg(f.getOriginalFilename());
			String path = request.getSession().getServletContext().getRealPath("/resources");
			/*System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> path : " + path);*/

			byte[] data = f.getBytes();

			FileOutputStream out = new FileOutputStream(path + "/" + emp.getEmpimg());
			out.write(data);
		}
		
		service.join(emp);

		return "redirect:/main.inc";
	}


	// idchk
	@RequestMapping(value = "/idchk.inc")
	@ResponseBody
	public boolean idchk(@RequestParam(value = "id") String empid) {
		System.out.println("UserCtrl idchk");
		
		EmployeeDeptVO idchk = service.idCheck(empid);
		System.out.println("ID ม฿บน chk" + idchk);
		
		boolean flag = false;
		if (idchk == null) {
			flag = true;
		}
		return flag;
	}

	

}
