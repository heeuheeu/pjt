package user.ctrl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import Service.UserServiceImpl;
import model.domain.vo.EmployeeDepartmentVO;
import model.domain.vo.EmployeeVO;
import model.domain.vo.FavoriteVO;
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
	
/*	
	@RequestMapping(value="/login.inc", method=RequestMethod.POST)
	public String login(MemberVO member, Model model) { // member�� ������ �ɰ� ���� ��� 1. model �߰�
		System.out.println("UserCtrl login");
		MemberVO user = service.login(member);
		model.addAttribute("login", user);	// 2. attribute �߰�.
		
		return "redirect:/main.inc"; // ������ ���� ���� sendredirect ���
	}
	*/
	@RequestMapping(value="/login.inc", method=RequestMethod.POST)
	public String login(MemberVO member, Model model) { 
		System.out.println("UserCtrl login");
		MemberVO user = service.login(member);
		model.addAttribute("login", user);	
		
		return "list";
	}
	
/*	
	@RequestMapping("/pagemove.inc")
	public String move(Model model) {
		System.out.println("AndroidCtrl move");
		ArrayList<AndroidVO> list = new ArrayList<AndroidVO>();
		list.add(new AndroidVO(1, "����ʹ�", "������"));
		list.add(new AndroidVO(1, "���̷�", "������"));
		list.add(new AndroidVO(1, "�氡", "������"));
		model.addAttribute("lists", list);
		
		return "ch02/table-sample";
	}
	
	*/
	
	@RequestMapping(value="/logout.inc", method=RequestMethod.GET) // logout�� ��Ŀ �������� ����. -> get ���.
	public String logout(SessionStatus status, HttpSession session) { //logout�� �Ű������� �� �ʿ� ����
		System.out.println("UserCtrl logout");
		System.out.println(status);
		session.invalidate();
		status.setComplete(); // ���� ������ ���ؼ� ���� ���� �޾ƿ�
		
		return "redirect:/main.inc"; 

	}

	@RequestMapping(value="/joinForm.inc", method=RequestMethod.GET) 
	public String joinForm(MemberVO member) {
		System.out.println("UserCtrl joinForm");
		return "joinForm"; 
	}
	
	@RequestMapping(value="/join.inc", method=RequestMethod.POST) 
	public String join(MemberVO member, Model model) {
		System.out.println("UserCtrl join");
		service.join(member);
		
		// ���� �߰�
		model.addAttribute("login", member);	

		return "redirect:/main.inc"; 
	}
	
	@RequestMapping(value="/updateForm.inc", method=RequestMethod.GET) 
	public String updateForm(MemberVO member, Model model) {
		System.out.println("UserCtrl updateForm");
		return "updateForm"; 
	}

	@RequestMapping(value="/update.inc", method=RequestMethod.POST) 
	public String update(MemberVO member, Model model) {
		System.out.println("UserCtrl update");
		int flag = service.update(member);
		
		// ���� �߰�
		model.addAttribute("login", member);	

		return "redirect:/main.inc"; 
	}
	
	
	
	
	
	
	
	
	
	
	//플러스 버튼 누르면 인사팀 리스트 보여주기
	@RequestMapping(value = "/list.inc", method = RequestMethod.GET)
	public String boardList(Model model) {
		System.out.println("UserCtrl boardList");
		List<EmployeeDepartmentVO> list = service.list();		
		model.addAttribute("lists", list);
		return "orgachart";
	}
	
	
	//즐겨찾기 check한 사람 id와  받아오기 +  +뿌려주기
		@RequestMapping(value = "/favorite.inc")		
		public String testCheck(@RequestParam(value="valueArrTest[]") List<String> valueArr, HttpSession session,Model model){
			System.out.println("UserCtrl testcheck");
			EmployeeVO member = (EmployeeVO)session.getAttribute("loginSession");
			System.out.println(valueArr.size()); //check된 것 갯수			
			
			for(int i=0 ; i<valueArr.size() ; i++){
				System.out.println(valueArr.get(i)); //check된 사람 이름 뿌려보기
			}
			
			ArrayList<FavoriteVO> list = new ArrayList<FavoriteVO>();
			
			for(int i=0 ; i<valueArr.size() ; i++){
				int flag = service.addFav(member.getEmpid(), valueArr.get(i));
				System.out.println("insert flag >>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+flag);
			}
			
			/*for(int i=0 ; i<valueArr.size() ; i++){
				MemberVO m = service.getFav(member.getId(), valueArr.get(i));
				list.add(m);
			}			
			model.addAttribute("favorite", list);
			return "redirect:/main.inc?empid="+member.getId();
			List<MemberVO>
			*/
			return "orgachart";
		}
			
	
	
	
}

