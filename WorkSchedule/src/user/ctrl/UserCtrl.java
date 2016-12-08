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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import Service.UserServiceImpl;
import model.domain.vo.CalendarNoteVO;
import model.domain.vo.CalendarVO;
import model.domain.vo.EmpIdVO;
import model.domain.vo.EmployeeDepartmentVO;
import model.domain.vo.EmployeeDeptVO;
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
	
	@RequestMapping(value="/login.inc")
	public String loginEmp(EmployeeVO employee, Model model) { 
		System.out.println("UserCtrl loginEmp");
		EmployeeVO user = service.loginEmp(employee);
		model.addAttribute("login", user);	
		
		return "redirect:/user.inc";
	}
		
	@RequestMapping(value="/user.inc")
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
	
	@RequestMapping(value="/favorite.inc")
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
	public String userlist2(@RequestParam(value="currDate") String currdate, EmployeeWorkDeptVO useremp, ArrayList<EmployeeFavWorkDeptVO> favemp, HttpSession session, Model model) {
		System.out.println("===================================페이지 변경=====================================");
		System.out.println("UserCtrl userlist2 calDay.inc");
		System.out.println(currdate);
	
		EmployeeVO user = (EmployeeVO)session.getAttribute("login");	
		System.out.println(user.getEmpid());
		
		//////////////////////////////////////////////////////////////////////////////// mylist reload
		EmployeeWorkDeptVO mylist = new EmployeeWorkDeptVO();	
		mylist.setEmpid(user.getEmpid());
		mylist.setCurrdate(currdate);	

		int work = service.selectwork(mylist); // work table validation

		if(work==0) { // 1.  table empty, 
			mylist = service.mylist1(mylist);
			mylist.setAmloc(mylist.getEmploc());
			mylist.setPmloc(mylist.getEmploc());
		}
				
		else { // 2. work table exist
			mylist = service.mylist2(mylist);
		}	
					
		///////////////////////////////////////////////////////////////////////////////// favorite reload		
		EmployeeWorkDeptVO list = new EmployeeWorkDeptVO();
		list.setEmpid(user.getEmpid());
		list.setCurrdate(currdate);
		
		List<EmployeeFavWorkDeptVO> favlist = new ArrayList<EmployeeFavWorkDeptVO>();
		favlist = service.selectempfav(list);

		model.addAttribute("myfav", favlist);
		model.addAttribute("myinfo", mylist);
		model.addAttribute("day", currdate);

		return "viewOtherDay";
	}

	////////////////////////////// eunbi eunbi//////////////////////////////

	// show searchForm
	@RequestMapping(value = "/searchview.inc")
	public String searchForm(Model model) {
		System.out.println("UserCtrl searchForm");
		List<EmployeeDeptVO> list = service.list(); 
		model.addAttribute("lists", list);
		return "searchView";
	} 

	// orgachart check
	/*
	@RequestMapping(value = "/addfavorite.inc")
	@ResponseBody
	public String addFavorite(@RequestParam(value = "valueArrTest[]") List<String> valueArr, HttpSession session,
			Model model) {
		System.out.println("UserCtrl addFavorite");
		EmployeeVO user = (EmployeeVO) session.getAttribute("login");

		
		// check된 사람의 empid받아 왔음
		for (int i = 0; i < valueArr.size(); i++) {
			System.out.println(valueArr.get(i));
		}

		// (empid,empidfav) favorite table DB insert
		ArrayList<FavoriteVO> list = new ArrayList<FavoriteVO>();
		for (int i = 0; i < valueArr.size(); i++) {
			int flag = service.addFav(user.getEmpid(), valueArr.get(i));
			System.out.println("insert flag >>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + flag);
		}

		/////////// 서희꺼 가져오기 - favorite list 카드뷰 보여주기////////

		return "true" ;

	}
	*/
	@RequestMapping(value = "/addfavorite.inc")
	public String addFavorite(EmpIdVO eivo, HttpSession session,Model model) {
		System.out.println("UserCtrl addFavorite");
		EmployeeVO user = (EmployeeVO) session.getAttribute("login");

		
		// check된 사람의 empid받아 왔음
		for (int i = 0; i < eivo.getChk().size(); i++) {
			System.out.println(">>>>>>>>>>>>>>>>>>>>> size "+eivo.getChk().get(i));
		}

		// (empid,empidfav) favorite table DB insert
		ArrayList<FavoriteVO> list = new ArrayList<FavoriteVO>();
		for (int i = 0; i < eivo.getChk().size(); i++) {
			int flag = service.addFav(user.getEmpid(), eivo.getChk().get(i));
			System.out.println("insert flag >>>>>>>>>>>>>>>>>>>>>>>>>>>>>> for i " + flag);
		}

		/////////// 서희꺼 가져오기 - favorite list 카드뷰 보여주기////////

		return "redirect:/user.inc" ;

	}

	@RequestMapping(value = "/search.inc")
	  public String search(EmployeeDeptVO member, Model model) {
	   System.out.println("UserCtrl search");
	   List<EmployeeDeptVO> list = service.searchEmp(member);
	   model.addAttribute("lists", list);
	   return "searchView";
	  }
	
	@RequestMapping(value="/logout.inc") 
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
	
/*
	@RequestMapping(value = "/list.inc", method = RequestMethod.GET)
	public String boardList(Model model) {
		System.out.println("UserCtrl boardList");
		List<EmployeeDepartmentVO> list = service.list();		
		model.addAttribute("lists", list);
		return "orgachart";
	}
	
	*/
	
	////////////////////////////////////// to calendar.jsp
	@RequestMapping("/calMove.inc")
	public String calendarMove() {
		System.out.println("calendarMove 호출");
		return "calendar";
	}

	////////////////////////////////////// requestMapping to calendar.jsp
	@RequestMapping("/calendar.inc")
	@ResponseBody
	public List<CalendarNoteVO> main(Model model, HttpSession session) {
		System.out.println("calendar에 VO 받아온 후 호출");
		// 나의 일정 전체 select
		EmployeeVO user = (EmployeeVO) session.getAttribute("login");
		CalendarVO cal = new CalendarVO();
		cal.setEmpid(user.getEmpid());

		List<CalendarVO> work = service.selectMyWork(cal);
		List<CalendarNoteVO> list = new ArrayList<CalendarNoteVO>();

		for (int i = 0; i < work.size(); i++) {
			if (work.get(i).getEmploc().equals(work.get(i).getAmloc())
					&& work.get(i).getEmploc().equals(work.get(i).getPmloc())) {
				// default loc와 ampm loc가 같을 때는 list에 저장 안함
			} else {
				String date = work.get(i).getWorkdate().substring(0, 10);
				String note = "AM : " + work.get(i).getAmloc() + " / PM : " + work.get(i).getPmloc();

				System.out.println("default : " + work.get(i).getEmploc() + " / am : " + work.get(i).getAmloc()
						+ " / pm : " + work.get(i).getPmloc());

				list.add(new CalendarNoteVO(date, note));
			}
		}
		model.addAttribute("myinfo", user);
		model.addAttribute("works", work);
		model.addAttribute("lists", list);
		return list;
	}
}

