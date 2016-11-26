package user.ctrl;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import Service.UserServiceImpl;
import model.domain.vo.MemberVO;

@Controller
@SessionAttributes({"login", "cart"}) // 세션 작업이 필요한 경우 추가

public class UserCtrl { // 업무 모듈에 메소드를 심음

	@Resource(name="UserService")
	private UserServiceImpl service;
	

	@RequestMapping("/main.inc")
	public String main() {
		System.out.println("MainCrl main");
		return "main"; 
	}
	
	
	@RequestMapping(value="/login.inc", method=RequestMethod.POST)
	public String login(MemberVO member, Model model) { // member에 세션을 심고 싶을 경우 1. model 추가
		System.out.println("UserCtrl login");
		MemberVO user = service.login(member);
		model.addAttribute("login", user);	// 2. attribute 추가.
		
		return "redirect:/main.inc"; // 세션을 날릴 때는 sendredirect 사용
	}
	
	@RequestMapping(value="/logout.inc", method=RequestMethod.GET) // logout은 앵커 형식으로 받음. -> get 방식.
	public String logout(SessionStatus status, HttpSession session) { //logout은 매개변수를 줄 필요 없음
		System.out.println("UserCtrl logout");
		System.out.println(status);
		session.invalidate();
		status.setComplete(); // 세션 끝내기 위해서 현재 세션 받아옴
		
		return "redirect:/main.inc"; 

	}

	@RequestMapping(value="/joinForm.inc", method=RequestMethod.GET) // logout은 앵커 형식으로 받음. -> get 방식.
	public String joinForm(MemberVO member) {
		System.out.println("UserCtrl joinForm");
		return "joinForm"; 
	}
	
	@RequestMapping(value="/join.inc", method=RequestMethod.POST) // logout은 앵커 형식으로 받음. -> get 방식.
	public String join(MemberVO member, Model model) {
		System.out.println("UserCtrl join");
		service.join(member);
		
		// 세션 추가
		model.addAttribute("login", member);	

		return "redirect:/main.inc"; 
	}
	
	@RequestMapping(value="/updateForm.inc", method=RequestMethod.GET) // logout은 앵커 형식으로 받음. -> get 방식.
	public String updateForm(MemberVO member, Model model) {
		System.out.println("UserCtrl updateForm");
		return "updateForm"; 
	}

	@RequestMapping(value="/update.inc", method=RequestMethod.POST) // logout은 앵커 형식으로 받음. -> get 방식.
	public String update(MemberVO member, Model model) {
		System.out.println("UserCtrl update");
		int flag = service.update(member);
		
		// 세션 추가
		model.addAttribute("login", member);	

		return "redirect:/main.inc"; 
	}
}

