package user.ctrl;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import Service.UserServiceImpl;
import model.domain.vo.CalendarNoteVO;
import model.domain.vo.CalendarVO;
import model.domain.vo.DeptDivisionVO;
import model.domain.vo.EmpIdVO;
import model.domain.vo.EmployeeDeptDivVO;
import model.domain.vo.EmployeeFavWorkDeptVO;
import model.domain.vo.EmployeeVO;
import model.domain.vo.EmployeeWorkDeptVO;
import model.domain.vo.NfcVO;

@Controller
@SessionAttributes({ "login", "myinfo" })

public class UserCtrl {

	@Resource(name = "UserService")
	private UserServiceImpl service;

	
	
	///////////////////////////////////////////////////////////////////////////////////////// main
	
	@RequestMapping("/main.inc")
	public String main() {
		System.out.println("MainCrl main");
		return "intro";
	}
	
	// login + id/PW validation
	@RequestMapping(value = "/login.inc")
	public String loginEmp(EmployeeVO employee, Model model) {
		System.out.println("UserCtrl loginEmp");
		
		EmployeeVO user = service.loginEmp(employee);
		String idchk = "noneId";
	
		if (user == null) { // 로그인 정보 불일치시
			model.addAttribute("chk", idchk);
			return "intro";
		}
		
		
		   
		else { // 로그인 정보 일치시
			// dashboard - 인사팀 로그인
			if (user.getEmpid().equals("hr")) {
				model.addAttribute("login", user);
				return "redirect:/dashboard.inc";
			}
			else if (user.getEmpid().equals("admin")) {
				System.out.println("admin" + user.getEmpid());
				model.addAttribute("login", user);
				return "redirect:/adminForm.inc";
			}
			else {
				model.addAttribute("login", user);
				return "redirect:/user.inc";
			}
		}
	}

	@RequestMapping(value = "/logout.inc")
	public String logout(SessionStatus status, HttpSession session) {
		System.out.println("UserCtrl logout");
		System.out.println(status);
		session.invalidate();
		status.setComplete();

		return "redirect:/main.inc";
	}

	

	///////////////////////////////////////////////////////////////////////////////////////// view.jsp

	@RequestMapping(value = "/user.inc")
	public String userlist(HttpSession session, Model model) {
		System.out.println("UserCtrl list");

		// login session (empid)
		EmployeeVO user = (EmployeeVO) session.getAttribute("login");
		EmployeeWorkDeptVO mylist = new EmployeeWorkDeptVO();
		mylist.setEmpid(user.getEmpid());

		// Date setting
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String currdate = sdf1.format(cal.getTime());
		mylist.setCurrdate(currdate);

		// work table validation
		int work = service.selectwork(mylist);

		if (work == 0) { // 1. table empty,
			mylist = service.mylist1(mylist);
			mylist.setWorkdate(currdate);
			mylist.setAmloc(mylist.getEmploc());
			mylist.setPmloc(mylist.getEmploc());
		} else { // 2. work table exist
			mylist = service.mylist2(mylist);
		}
		
		model.addAttribute("myinfo", mylist); ////////////////////////////////////////// * my work -EmployeeWorkDeptVO *

		return "redirect:/favorite.inc";
	}

	
	@RequestMapping(value = "/favorite.inc")
	public String favoritelist(HttpSession session, Model model) {
		System.out.println("UserCtrl favoritelist");

		EmployeeVO user = (EmployeeVO) session.getAttribute("login");
		EmployeeWorkDeptVO list = new EmployeeWorkDeptVO();
		list.setEmpid(user.getEmpid());
		list.setDeptid(user.getDeptid());

		// Date setting
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String currdate = sdf1.format(cal.getTime());
		list.setCurrdate(currdate);

		List<EmployeeFavWorkDeptVO> favlist = new ArrayList<EmployeeFavWorkDeptVO>();
		favlist = service.selectempfav(list);

		model.addAttribute("myfav", favlist); // fav work - EmployeeFavWorkDeptVO

		return "view";
	}

	
	
	///////////////////////////////////////////////////////////////////////////////////////// viewOtherDay.jsp
	
	// 날짜 변경하기
	@RequestMapping(value = "/calDay.inc")
	public String userlist2(@RequestParam(value = "currDate") String currdate,
			@RequestParam(value = "view") String view, HttpSession session, Model model) {
		System.out.println("===================================페이지 변경=====================================");
		System.out.println("UserCtrl userlist2 calDay.inc / " + currdate);

		// mylist reload
		EmployeeVO user = (EmployeeVO) session.getAttribute("login");
		EmployeeWorkDeptVO useremp = new EmployeeWorkDeptVO();
		useremp.setEmpid(user.getEmpid());
		useremp.setCurrdate(currdate);

		int work = service.selectwork(useremp); // work table validation

		if (work == 0) { // 1. table empty,
			useremp = service.mylist1(useremp);
			useremp.setWorkdate(currdate);
			useremp.setAmloc(useremp.getEmploc());
			useremp.setPmloc(useremp.getEmploc());
		} else { // 2. work table exist
			useremp = service.mylist2(useremp);
		}

		// favorite reload
		EmployeeWorkDeptVO list = new EmployeeWorkDeptVO();
		list.setEmpid(user.getEmpid());
		list.setCurrdate(currdate);

		List<EmployeeFavWorkDeptVO> favlist = new ArrayList<EmployeeFavWorkDeptVO>();
		favlist = service.selectempfav(list);

		model.addAttribute("myfav", favlist);
		model.addAttribute("myinfo", useremp);
		model.addAttribute("day", currdate);
		model.addAttribute("view", view);

		return "viewOtherDay";
	}

	

	//////////////////////////////////////////////////////////////////////////// searchview.jsp, favorite add/delete

	// show searchForm
	@RequestMapping(value = "/searchview.inc")
	public String searchForm(EmployeeDeptDivVO useremp, HttpSession session, Model model) {
		System.out.println("UserCtrl searchForm");

		EmployeeVO user = (EmployeeVO) session.getAttribute("login");
		useremp.setEmpid(user.getEmpid());
		useremp.setDeptid(user.getDeptid());

		List<EmployeeDeptDivVO> list = service.list(useremp);
		List<EmpIdVO> favid = service.selectFavId(user.getEmpid());

		model.addAttribute("lists", list);
		model.addAttribute("myfav", favid);
		return "searchView";
	}
	
	@RequestMapping(value = "/addfavorite.inc")
	@ResponseBody
	public List<EmpIdVO> addFavorite(@RequestParam(value = "checkid") String chkid, HttpSession session, Model model) {
		System.out.println("UserCtrl addFavorite");

		EmployeeVO user = (EmployeeVO) session.getAttribute("login");
		service.addFav(user.getEmpid(), chkid);
		List<EmpIdVO> favid = service.selectFavId(user.getEmpid());

		return favid;
	}

	@RequestMapping(value = "/deletefavorite.inc")
	@ResponseBody
	public List<EmpIdVO> deleteFavorite(@RequestParam(value = "checkid") String chkid, HttpSession session, Model model) {
		System.out.println("UserCtrl deleteFavorite");

		EmployeeVO user = (EmployeeVO) session.getAttribute("login");
		service.deleteFav(user.getEmpid(), chkid);
		List<EmpIdVO> favid = service.selectFavId(user.getEmpid());

		return favid;
	}

	
	
	////////////////////////////////////////////////////////////////////////////////////////////// search

	@RequestMapping(value = "/search.inc")
	public String search(EmployeeDeptDivVO member, HttpSession session, Model model) {
		System.out.println("UserCtrl search");

		EmployeeVO user = (EmployeeVO) session.getAttribute("login");
		member.setEmpid(user.getEmpid());
		List<EmployeeDeptDivVO> list = service.searchEmp(member);
		List<EmpIdVO> favid = service.selectFavId(user.getEmpid());
		
		for(int i=0 ; i<list.size() ; i++) {
			System.out.println(list.get(i));
		}
		
		model.addAttribute("myfav", favid);
		model.addAttribute("lists", list);

		return "searchView";
	}

	/*@RequestMapping(value = "/searchdept.inc")
	public String searchdept(HttpSession session, Model model) {
		System.out.println("UserCtrl search");
		EmployeeVO user = (EmployeeVO) session.getAttribute("login");
		EmployeeDeptDivVO member = new EmployeeDeptDivVO();
		member.setDeptid(user.getDeptid());
		member.setEmpid(user.getEmpid());

		List<EmployeeDeptDivVO> list = service.searchDept(member);
		List<EmpIdVO> favid = service.selectFavId(user.getEmpid());

		model.addAttribute("myfav", favid);
		model.addAttribute("lists", list);

		return "searchView";
	}

	@RequestMapping(value = "/searchteam.inc")
	public String searchteam(HttpSession session, Model model) {
		System.out.println("UserCtrl searchteam");
		EmployeeVO user = (EmployeeVO) session.getAttribute("login");
		EmployeeDeptDivVO member = new EmployeeDeptDivVO();
		member.setDeptid(user.getDeptid());
		member.setEmpid(user.getEmpid());

		List<EmployeeDeptDivVO> list = service.searchDiv(member);
		List<EmpIdVO> favid = service.selectFavId(user.getEmpid());

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getDivid() + list.get(i).getDivname());
		}
		model.addAttribute("myfav", favid);
		model.addAttribute("lists", list);

		return "searchView";
	}*/

	
	
	////////////////////////////////////////////////////////////////////////////////////////////// calendar.jsp
	
	// to calendar.jsp
	@RequestMapping("/calMove.inc")
	public String calendarMove() {
		System.out.println("calendarMove 호출");
		return "calendar";
	}

	// requestMapping to calendar.jsp
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

				/*System.out.println("default : " + work.get(i).getEmploc() + " / am : " + work.get(i).getAmloc()
						+ " / pm : " + work.get(i).getPmloc());*/

				list.add(new CalendarNoteVO(date, note));
			}
		}

		model.addAttribute("works", work);
		model.addAttribute("lists", list);
		return list;
	}

	
	
	////////////////////////////////////////////////////////////////////////////////////////////// updateform.jsp
	
	@RequestMapping(value = "/updateview.inc")
	public String update(HttpSession session, Model model) {
		System.out.println("UserCtrl update");

		EmployeeVO user = (EmployeeVO) session.getAttribute("login"); 
		
		EmployeeDeptDivVO mylist = new EmployeeDeptDivVO();
		mylist.setEmpid(user.getEmpid());
		mylist = service.selectEmpInfo(mylist);
		
		List<DeptDivisionVO> divList = service.selectdiv(); 	
		List<DeptDivisionVO> deptlist = service.selectdeptdiv();

		/*List<String> deptlist = new ArrayList<String>();
		deptlist = service.selectboxDept(divname);*//////////////////////////////////////////////////// 고치기


		model.addAttribute("divlist", divList); // divlist 조회
		model.addAttribute("deptlist", deptlist); // deptlist 조회
		model.addAttribute("mydeptdiv", mylist); // EmployeeDeptDivVO 

		return "update";
	}

	@RequestMapping(value = "/deptSelect.inc")
	@ResponseBody
	public List<String> deptSelect(@RequestParam(value = "div") String divname) {
		System.out.println("userCtrl deptSelect");

		List<String> deptlist = new ArrayList<String>();
		deptlist = service.selectboxDept(divname);

		return deptlist;
	}
	
	// my info db update
	@RequestMapping(value = "/modify.inc", method = RequestMethod.POST ) 
	public String modify(EmployeeDeptDivVO member, HttpServletRequest request, Model model) throws Exception {
		System.out.println("userctrl modify");

		MultipartFile f = member.getFile();

		// 수정된 사항 있을 때만 filename set		
		if ( f.getOriginalFilename() != null && f.getOriginalFilename() != "") {
			member.setEmpimg(f.getOriginalFilename());
			String path = request.getSession().getServletContext().getRealPath("/resources");
			/*System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> path : " + path);*/

			byte[] data = f.getBytes();

			FileOutputStream out = new FileOutputStream(path + "/" + member.getEmpimg());
			out.write(data);
		}
		
		service.update(member);
		service.updateWork(member);				

		EmployeeVO user = new EmployeeVO(member.getEmpimg(), member.getEmpid(),member.getEmppwd(), member.getEmpname(),  
					member.getEmpphone(), member.getEmpmail(),member.getEmploc(), member.getDeptid());
		user = service.loginEmp(user);

		model.addAttribute("login", user);
		
		return "redirect:/user.inc";
	}

	
	
	////////////////////////////////////////////////////////////////////////////////////////////// modal update
	
	// modal content update --sukjin
	@RequestMapping("/update.inc")
	public String update(EmployeeWorkDeptVO myinfo) {
		System.out.println("update Ctrl");
		
		String workDateForm = myinfo.getWorkdate();
		String year = workDateForm.substring(0, 4);
		String month = workDateForm.substring(6, 8);
		String day = workDateForm.substring(10, 12);
		String workDateFormChange = year + "-" + month + "-" + day;

		myinfo.setWorkdate(workDateFormChange);
		service.update(myinfo);

		return "redirect:/user.inc";
	}

	// modal content update --sukjin
	@RequestMapping("/updateCal.inc")
	public String updateCal(EmployeeWorkDeptVO myinfo) {
		System.out.println("updateCal Ctrl");
		
		String workDateForm = myinfo.getWorkdate();
		String year = workDateForm.substring(0, 4);
		String month = workDateForm.substring(6, 8);
		String day = workDateForm.substring(10, 12);
		String workDateFormChange = year + "-" + month + "-" + day;
		
		myinfo.setWorkdate(workDateFormChange);
		service.update(myinfo);
		
		return "redirect:/calMove.inc";
	}

	
	
	////////////////////////////////////////////////////////////////////////////////////////////// calendar.jsp modal
	
	@RequestMapping("/calModal.inc")
	@ResponseBody
	public EmployeeWorkDeptVO calModal(EmployeeWorkDeptVO myinfo, HttpServletRequest request) {

		System.out.println("calModal Ctrl");

		String clickday = request.getParameter("clickday");
		String empid = request.getParameter("empid");

		myinfo.setCurrdate(clickday);
		myinfo.setEmpid(empid);

		EmployeeWorkDeptVO list = service.selectCalModal(myinfo);

		return list;
	}

	
	
	////////////////////////////////////////////////////////////////////////////////////////////// dashboard.jsp
	
	@RequestMapping(value = "/dashboard.inc")
	public String dashboard(HttpSession session, Model model) {
		System.out.println("UserCtrl dashboard");

		// login session (empid)
		EmployeeVO user = (EmployeeVO) session.getAttribute("login");
		EmployeeWorkDeptVO mylist = new EmployeeWorkDeptVO();
		mylist.setEmpid(user.getEmpid());

		// Date setting
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String currdate = sdf1.format(cal.getTime());
		mylist.setCurrdate(currdate);

		// work table validation
		int work = service.selectwork(mylist);

		// 1. table empty,
		if (work == 0) {
			mylist = service.mylist1(mylist);
			mylist.setAmloc(mylist.getEmploc());
			mylist.setPmloc(mylist.getEmploc());
		}
		// 2. work table exist
		else {
			mylist = service.mylist2(mylist);
		}

		model.addAttribute("myinfo", mylist);

		return "redirect:/favoritedash.inc";
	}

	
	@RequestMapping(value = "/favoritedash.inc")
	public String favoritedash(HttpSession session, Model model) {
		System.out.println("UserCtrl favoritedash");

		// login session (empid)
		EmployeeVO user = (EmployeeVO) session.getAttribute("login");
		EmployeeWorkDeptVO list = new EmployeeWorkDeptVO();
		list.setEmpid(user.getEmpid());
		list.setDeptid(user.getDeptid());

		// Date setting
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String currdate = sdf1.format(cal.getTime());
		list.setCurrdate(currdate);

		List<EmployeeWorkDeptVO> favlist = new ArrayList<EmployeeWorkDeptVO>();
		favlist = service.selectDashEmp(list);

		// 현재 시간 가져오기
		Date date = new Date();
		
		model.addAttribute("date", date);
		model.addAttribute("myfav", favlist);
		
		return "dashboard";
	}

	
	
	////////////////////////////////////////////////////////////////////////////////////////////// dashboardOtherDay.jsp
	
	@RequestMapping(value = "/calDayDash.inc")
	public String favoritedash2(@RequestParam(value = "currDate") String currdate, HttpSession session, Model model) {
		System.out.println("===================================페이지 변경=====================================");
		System.out.println("UserCtrl favoritedash2 calDayDash.inc");
		System.out.println(currdate);

		EmployeeVO user = (EmployeeVO) session.getAttribute("login");
		
		//mylist reload
		EmployeeWorkDeptVO mylist = new EmployeeWorkDeptVO();
		mylist.setEmpid(user.getEmpid());
		mylist.setCurrdate(currdate);

		int work = service.selectwork(mylist); // work table validation

		if (work == 0) { // 1. table empty,
			mylist = service.mylist1(mylist);
			mylist.setAmloc(mylist.getEmploc());
			mylist.setPmloc(mylist.getEmploc());
		}
		else { // 2. work table exist
			mylist = service.mylist2(mylist);
		}

		// favorite reload
		EmployeeWorkDeptVO list = new EmployeeWorkDeptVO();
		list.setEmpid(user.getEmpid());
		list.setDeptid(user.getDeptid());
		list.setCurrdate(currdate);

		List<EmployeeWorkDeptVO> favlist = new ArrayList<EmployeeWorkDeptVO>();
		favlist = service.selectDashEmp(list);

		/// 현재 시간 가져오기///
		Date date = new Date();
		
		model.addAttribute("date", date);
		model.addAttribute("myfav", favlist);
		model.addAttribute("myinfo", mylist);
		model.addAttribute("day", currdate);

		return "dashboardOtherDay";
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////////// NFC Ctrl

	@RequestMapping(value = "/nfc.inc")
	@ResponseBody
	public String nfc(@RequestParam(value = "loc") String loc, @RequestParam(value = "empid") String empid) {
		System.out.println("UserCtrl nfc");

		System.out.println("NFC 태그  !!!!!!!!!!!!!!!!!");
		System.out.println("loc : " + loc + "empid : " + empid);

		NfcVO nfc = new NfcVO();
		nfc.setLoc(loc);
		nfc.setEmpid(empid);

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("kk");

		String currentHour = sdf.format(date).toString();
		int Hour = Integer.parseInt(currentHour);

		if (Hour < 12) {
			service.nfcUpdateAm(nfc);
		} else {
			service.nfcUpdatePm(nfc);
		}

		return null;
	}

}

