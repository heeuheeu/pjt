package user.ctrl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import model.domain.vo.EmployeeWorkDeptVO;
import model.domain.vo.FavoriteVO;
import model.domain.vo.MemberVO;


@Controller
@SessionAttributes({"login", "myinfo"}) // 세션 작업이 필요한 경우 추가

public class UserCtrl { // 업무 모듈에 메소드를 심음

	@Resource(name="UserService")
	private UserServiceImpl service;
	

	@RequestMapping("/main.inc")
	public String main() {
		System.out.println("MainCrl main");
		return "intro"; 
	}

	/*@RequestMapping(value="/login.inc", method=RequestMethod.POST)
	public String login(MemberVO member, Model model) { 
		System.out.println("UserCtrl login");
		MemberVO user = service.login(member);
		model.addAttribute("login", user);	
		
		return "list";
	}*/
	
	@RequestMapping(value="/login.inc", method=RequestMethod.POST)
	public String loginEmp(EmployeeVO employee, Model model) { 
		System.out.println("UserCtrl loginEmp");
		EmployeeVO user = service.loginEmp(employee);
		model.addAttribute("login", user);	
		
		return "redirect:/user.inc";
	}
	
	
	@RequestMapping(value="/user.inc", method=RequestMethod.GET)
	public String list(EmployeeWorkDeptVO useremp, HttpSession session, Model model) {
		System.out.println("UserCtrl list");

		// login session (empid) 붙이기
		EmployeeVO user = (EmployeeVO)session.getAttribute("login");
		System.out.println(user.getEmpid()); 		
		EmployeeWorkDeptVO mylist = new EmployeeWorkDeptVO();
		mylist.setEmpid(user.getEmpid());
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy:MM:dd");
		String currdate = sdf1.format(cal.getTime());
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<currdate>>>>>>>>>>>>>>>>>>>>>"+currdate);
		
		mylist.setCurrdate(currdate);
		
		// work table validation, 있는지 없는지 확인
		int work = service.selectwork(mylist);
		System.out.println("select work table count: "+work);

		// 1.  table empty, 근무 일정 비어있을 때
		if(work==0) {
			mylist = service.mylist1(mylist);
			mylist.setAmloc(mylist.getEmploc()); // emploc 가져와서 ampoc, pmloc 채우기
			mylist.setPmloc(mylist.getEmploc());
			System.out.println("amloc, pmloc : "+mylist.getAmloc()+mylist.getPmloc());
		}
		
		// 2. work table 입력된 경우 
		else {
			mylist = service.mylist2(mylist);
			System.out.println("amloc, pmloc : "+mylist.getAmloc()+mylist.getPmloc());
		}	
		
		model.addAttribute("myinfo", mylist);
		
		return "view";
	}
	
	
	//////////////////////////////////////////////////////////// 
	@RequestMapping(value="/calDay.inc", method=RequestMethod.POST) 
	public String prevDay(@RequestParam(value="currDate") String currdate, EmployeeWorkDeptVO useremp, HttpSession session, Model model) {
		System.out.println("calDay Ctrl");
		
		// login session (empid) 붙이기
				EmployeeVO user = (EmployeeVO)session.getAttribute("login");
				System.out.println(user.getEmpid()); 		
				EmployeeWorkDeptVO mylist = new EmployeeWorkDeptVO();
				mylist.setEmpid(user.getEmpid());
				
				mylist.setCurrdate(currdate);
				
				// work table validation, 있는지 없는지 확인
				int work = service.selectwork(mylist);
				System.out.println("select work table count: "+work);

				// 1.  table empty, 근무 일정 비어있을 때
				if(work==0) {
					mylist = service.mylist1(mylist);
					mylist.setAmloc(mylist.getEmploc()); // emploc 가져와서 ampoc, pmloc 채우기
					mylist.setPmloc(mylist.getEmploc());
					System.out.println("amloc, pmloc : "+mylist.getAmloc()+mylist.getPmloc());
				}
				
				// 2. work table 입력된 경우 
				else {
					mylist = service.mylist2(mylist);
					System.out.println("amloc, pmloc : "+mylist.getAmloc()+mylist.getPmloc());
				}	
				
				model.addAttribute("myinfo", mylist);
				model.addAttribute("day", currdate);
		
		// 현재 페이지 날짜 심기
		System.out.println("현재 페이지 날짜 :"+currdate);
		return "viewOtherDay";
	}
	////////////////////////////////////////////////////////////////
	
	
	
	@RequestMapping(value="/logout.inc", method=RequestMethod.GET) // logout은 앵커 형식으로 받음. -> get 방식.
	public String logout(SessionStatus status, HttpSession session) { //logout은 매개변수를 줄 필요 없음
		System.out.println("UserCtrl logout");
		System.out.println(status);
		session.invalidate();
		status.setComplete(); // 세션 끝내기 위해서 현재 세션 받아옴
		
		return "redirect:/main.inc"; 

	}

	/*
	@RequestMapping(value="/joinForm.inc", method=RequestMethod.GET) 
	public String joinForm(MemberVO member) {
		System.out.println("UserCtrl joinForm");
		return "joinForm"; 
	}
	
	@RequestMapping(value="/join.inc", method=RequestMethod.POST) 
	public String join(MemberVO member, Model model) {
		System.out.println("UserCtrl join");
		service.join(member);
		
		// 세션 추가
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
		
		// 세션 추가
		model.addAttribute("login", member);	

		return "redirect:/main.inc"; 
	}
	*/
	

	//�뵆�윭�뒪 踰꾪듉 �늻瑜대㈃ �씤�궗�� 由ъ뒪�듃 蹂댁뿬二쇨린
	@RequestMapping(value = "/list.inc", method = RequestMethod.GET)
	public String boardList(Model model) {
		System.out.println("UserCtrl boardList");
		List<EmployeeDepartmentVO> list = service.list();		
		model.addAttribute("lists", list);
		return "orgachart";
	}
	
	
	//利먭꺼李얘린 check�븳 �궗�엺 id��  諛쏆븘�삤湲� +  +肉뚮젮二쇨린
		@RequestMapping(value = "/favorite.inc")		
		public String testCheck(@RequestParam(value="valueArrTest[]") List<String> valueArr, HttpSession session,Model model){
			System.out.println("UserCtrl testcheck");
			EmployeeVO member = (EmployeeVO)session.getAttribute("loginSession");
			System.out.println(valueArr.size()); //check�맂 寃� 媛��닔			
			
			for(int i=0 ; i<valueArr.size() ; i++){
				System.out.println(valueArr.get(i)); //check�맂 �궗�엺 �씠由� 肉뚮젮蹂닿린
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

