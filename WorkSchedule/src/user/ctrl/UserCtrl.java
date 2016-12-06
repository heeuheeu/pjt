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
import model.domain.vo.EmployeeFavWorkDeptVO;
import model.domain.vo.EmployeeVO;
import model.domain.vo.EmployeeWorkDeptVO;
import model.domain.vo.FavoriteVO;


@Controller
@SessionAttributes({"login", "myinfo"})

public class UserCtrl { 

	@Resource(name="UserService")
	private UserServiceImpl service;
	

	@RequestMapping("/main.inc")
	public String main() {
		System.out.println("MainCrl main");
		return "intro"; 
	}
	
	@RequestMapping(value="/login.inc", method=RequestMethod.POST)
	public String loginEmp(EmployeeVO employee, Model model) { 
		System.out.println("UserCtrl loginEmp");
		EmployeeVO user = service.loginEmp(employee);
		model.addAttribute("login", user);	
		
		return "redirect:/user.inc";
	}
		
	@RequestMapping(value="/user.inc", method=RequestMethod.GET)
	public String userlist(EmployeeWorkDeptVO useremp, HttpSession session, Model model) {
		System.out.println("UserCtrl list");

		// login session (empid)
		EmployeeVO user = (EmployeeVO)session.getAttribute("login");		
		EmployeeWorkDeptVO mylist = new EmployeeWorkDeptVO();
		mylist.setEmpid(user.getEmpid());
		
		// Date setting
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String currdate = sdf1.format(cal.getTime());
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<currdate>>>>>>>>>>>>>>>>>>>>>"+currdate);
		mylist.setCurrdate(currdate);
		
		// work table validation
		int work = service.selectwork(mylist);
		System.out.println("select work table count: "+work);

		// 1.  table empty, 
		if(work==0) {
			mylist = service.mylist1(mylist);
			mylist.setAmloc(mylist.getEmploc()); 
			mylist.setPmloc(mylist.getEmploc());
		}
		
		// 2. work table exist
		else {
			mylist = service.mylist2(mylist);
			System.out.println("amloc, pmloc : "+mylist.getAmloc()+mylist.getPmloc());
		}	
		
		model.addAttribute("myinfo", mylist);
		
		return "redirect:/favorite.inc";
	}
	
	@RequestMapping(value="/favorite.inc", method=RequestMethod.GET)
	public String favoritelist(ArrayList<EmployeeFavWorkDeptVO> favemp, HttpSession session, Model model) {
		System.out.println("UserCtrl favoritelist");

		// login session (empid) 
		EmployeeVO user = (EmployeeVO)session.getAttribute("login");		
		EmployeeWorkDeptVO list = new EmployeeWorkDeptVO();
		list.setEmpid(user.getEmpid());
		System.out.println(list.getEmpid());
		
		// Date setting
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String currdate = sdf1.format(cal.getTime());
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<currdate>>>>>>>>>>>>>>>>>>>>>"+currdate);
		list.setCurrdate(currdate);
		
		List<EmployeeFavWorkDeptVO> favlist = new ArrayList<EmployeeFavWorkDeptVO>();
		favlist = service.selectempfav(list);
		
		for(int i=0 ; i<favlist.size() ; i++) {
			System.out.println(favlist.get(i));			
		}
		
		model.addAttribute("myfav", favlist);
		return "view";
	}
		
	
	//////////////////////////////////////////////////////////// 
	@RequestMapping(value="/calDay.inc") 
	public String userlist2(@RequestParam(value="currDate") String currdate, EmployeeWorkDeptVO useremp, HttpSession session, Model model) {
		System.out.println("UserCtrl userlist2 calDay.inc");
		System.out.println(currdate);
		// login session (empid) 
		EmployeeVO user = (EmployeeVO)session.getAttribute("login");						
		EmployeeWorkDeptVO mylist = new EmployeeWorkDeptVO();
				
		//////////////////////////////////////////////////////////////////////////////// mylist reload
		mylist.setEmpid(user.getEmpid());
		mylist.setCurrdate(currdate);
				
		///////////////////////////////////////////////////////////////////////////////// 수정 필요
		// work table validation
		int work = service.selectwork(mylist);

		// 1.  table empty, 
		if(work==0) {
			mylist = service.mylist1(mylist);
			mylist.setAmloc(mylist.getEmploc());
			mylist.setPmloc(mylist.getEmploc());
		}
				
		// 2. work table exist
		else {
			mylist = service.mylist2(mylist);
		}	
					
		model.addAttribute("myinfo", mylist);
		model.addAttribute("day", currdate);

		return "viewOtherDay";
	}
	
	
	@RequestMapping(value="/calDayfav.inc") 
	public String favoritelist2(@RequestParam(value="currDate") String currdate, ArrayList<EmployeeFavWorkDeptVO> favemp, HttpSession session, Model model) {
		System.out.println("UserCtrl favoritelist2");

		// login session (empid) 
		EmployeeVO user = (EmployeeVO)session.getAttribute("login");		
		EmployeeWorkDeptVO list = new EmployeeWorkDeptVO();
		list.setEmpid(user.getEmpid());
		list.setCurrdate(currdate);
		
		List<EmployeeFavWorkDeptVO> favlist = new ArrayList<EmployeeFavWorkDeptVO>();
		favlist = service.selectempfav(list); 
		
		for(int i=0 ; i<favlist.size() ; i++) {
			System.out.println(favlist.get(i));			
		}
		
		model.addAttribute("myfav", favlist);
		return "viewOtherDay";
	}
	
	
	@RequestMapping(value="/logout.inc", method=RequestMethod.GET) 
	public String logout(SessionStatus status, HttpSession session) { 
		System.out.println("UserCtrl logout");
		System.out.println(status);
		session.invalidate();
		status.setComplete(); 
		
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
		
		// �꽭�뀡 異붽�
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
		
		// �꽭�뀡 異붽�
		model.addAttribute("login", member);	

		return "redirect:/main.inc"; 
	}
	*/
	

	@RequestMapping(value = "/list.inc", method = RequestMethod.GET)
	public String boardList(Model model) {
		System.out.println("UserCtrl boardList");
		List<EmployeeDepartmentVO> list = service.list();		
		model.addAttribute("lists", list);
		return "orgachart";
	}
	
	//筌앸Þ爰쇽㎕�뼐由� check占쎈립 占쎄텢占쎌뿺 id占쏙옙  獄쏆룇釉섓옙�궎疫뀐옙 +  +�굢�슢�젻雅뚯눊由�
		@RequestMapping(value = "/addfavorite.inc")		
		public String testCheck(@RequestParam(value="valueArrTest[]") List<String> valueArr, HttpSession session,Model model){
			System.out.println("UserCtrl testcheck");
			EmployeeVO member = (EmployeeVO)session.getAttribute("loginSession");
			System.out.println(valueArr.size()); //check占쎈쭆 野껓옙 揶쏉옙占쎈땾			
			
			for(int i=0 ; i<valueArr.size() ; i++){
				System.out.println(valueArr.get(i)); //check占쎈쭆 占쎄텢占쎌뿺 占쎌뵠�뵳占� �굢�슢�젻癰귣떯由�
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

