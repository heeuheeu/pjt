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
@SessionAttributes({"login", "cart"}) // ���� �۾��� �ʿ��� ��� �߰�

public class UserCtrl { // ���� ��⿡ �޼ҵ带 ����

	@Resource(name="UserService")
	private UserServiceImpl service;
	

	@RequestMapping("/main.inc")
	public String main() {
		System.out.println("MainCrl main");
		return "main"; 
	}
	
	
	@RequestMapping(value="/login.inc", method=RequestMethod.POST)
	public String login(MemberVO member, Model model) { // member�� ������ �ɰ� ���� ��� 1. model �߰�
		System.out.println("UserCtrl login");
		MemberVO user = service.login(member);
		model.addAttribute("login", user);	// 2. attribute �߰�.
		
		return "redirect:/main.inc"; // ������ ���� ���� sendredirect ���
	}
	
	@RequestMapping(value="/logout.inc", method=RequestMethod.GET) // logout�� ��Ŀ �������� ����. -> get ���.
	public String logout(SessionStatus status, HttpSession session) { //logout�� �Ű������� �� �ʿ� ����
		System.out.println("UserCtrl logout");
		System.out.println(status);
		session.invalidate();
		status.setComplete(); // ���� ������ ���ؼ� ���� ���� �޾ƿ�
		
		return "redirect:/main.inc"; 

	}

	@RequestMapping(value="/joinForm.inc", method=RequestMethod.GET) // logout�� ��Ŀ �������� ����. -> get ���.
	public String joinForm(MemberVO member) {
		System.out.println("UserCtrl joinForm");
		return "joinForm"; 
	}
	
	@RequestMapping(value="/join.inc", method=RequestMethod.POST) // logout�� ��Ŀ �������� ����. -> get ���.
	public String join(MemberVO member, Model model) {
		System.out.println("UserCtrl join");
		service.join(member);
		
		// ���� �߰�
		model.addAttribute("login", member);	

		return "redirect:/main.inc"; 
	}
	
	@RequestMapping(value="/updateForm.inc", method=RequestMethod.GET) // logout�� ��Ŀ �������� ����. -> get ���.
	public String updateForm(MemberVO member, Model model) {
		System.out.println("UserCtrl updateForm");
		return "updateForm"; 
	}

	@RequestMapping(value="/update.inc", method=RequestMethod.POST) // logout�� ��Ŀ �������� ����. -> get ���.
	public String update(MemberVO member, Model model) {
		System.out.println("UserCtrl update");
		int flag = service.update(member);
		
		// ���� �߰�
		model.addAttribute("login", member);	

		return "redirect:/main.inc"; 
	}
}

