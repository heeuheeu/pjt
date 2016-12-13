package user.ctrl;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
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
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import Service.UserServiceImpl;
import model.domain.vo.CalendarNoteVO;
import model.domain.vo.CalendarVO;
import model.domain.vo.DeptDivisionVO;
import model.domain.vo.EmpIdVO;
import model.domain.vo.EmployeeDeptDivVO;
import model.domain.vo.EmployeeDeptVO;
import model.domain.vo.EmployeeFavWorkDeptVO;
import model.domain.vo.EmployeeVO;
import model.domain.vo.EmployeeWorkDeptVO;
import model.domain.vo.FavoriteVO;
import model.domain.vo.NfcVO;

@Controller
@SessionAttributes({ "login", "myinfo" })

public class UserCtrl {

	@Resource(name = "UserService")
	private UserServiceImpl service;

	@RequestMapping("/main.inc")
	public String main() {
		System.out.println("MainCrl main");
		return "intro";
	}

	//////////// login + id pw validation////////////////////
	@RequestMapping(value = "/login.inc")
	public String loginEmp(EmployeeVO employee, Model model, Model model2) {
		System.out.println("UserCtrl loginEmp");
		EmployeeVO user = service.loginEmp(employee);

		String idchk = "noneId";

		/////////// 로그인 정보 불일치시///////////
		if (user == null) {
			model2.addAttribute("chk", idchk);
			return "intro";
		}

		//// 로그인 정보 일치시////
		else {
			////////////////////// 인사팀 로그인////////////////////////
			if (user.getEmpid().equals("hr")) {
				System.out.println("인사팀" + user.getEmpid());
				model.addAttribute("login", user);
				return "redirect:/dashboard.inc";
			}
			/////////// 로그인 정보 일치시///////////
			else {
				System.out.println("인사팀 아니에요" + user.getEmpid());
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

	///////////////////////////////////////////////////////////////////////////////////////// main
	///////////////////////////////////////////////////////////////////////////////////////// view

	@RequestMapping(value = "/user.inc")
	public String userlist(EmployeeWorkDeptVO useremp, HttpSession session, Model model) {
		System.out.println("UserCtrl list");

		// login session (empid)
		EmployeeVO user = (EmployeeVO) session.getAttribute("login");
		EmployeeWorkDeptVO mylist = new EmployeeWorkDeptVO();
		mylist.setEmpid(user.getEmpid());

		// Date setting
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String currdate = sdf1.format(cal.getTime());
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Curdate : " + currdate);
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

		System.out.println(mylist.getWorkdate() + " >>>>>>>>>>>>>" + mylist.getEmpmail() + " " + mylist.getEmpphone());

		model.addAttribute("myinfo", mylist);

		return "redirect:/favorite.inc";
	}

	@RequestMapping(value = "/favorite.inc")
	public String favoritelist(ArrayList<EmployeeFavWorkDeptVO> favemp, HttpSession session, Model model) {
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

		for (int i = 0; i < favlist.size(); i++) {
			System.out.println(favlist.get(i));
		}

		model.addAttribute("myfav", favlist);
		return "view";
	}

	// 날짜 변경하기
	@RequestMapping(value = "/calDay.inc")
	public String userlist2(@RequestParam(value = "currDate") String currdate,
			@RequestParam(value = "view") String view, EmployeeWorkDeptVO useremp,
			ArrayList<EmployeeFavWorkDeptVO> favemp, HttpSession session, Model model) {
		System.out.println("===================================페이지 변경=====================================");
		System.out.println("UserCtrl userlist2 calDay.inc / " + currdate);

		// mylist reload
		EmployeeVO user = (EmployeeVO) session.getAttribute("login");
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

	////////////////////////////////////////////////////////////////////////////////// favorite
	////////////////////////////////////////////////////////////////////////////////// /
	////////////////////////////////////////////////////////////////////////////////// search

	// show searchForm
	@RequestMapping(value = "/searchview.inc")
	public String searchForm(EmployeeDeptDivVO useremp, HttpSession session, Model model) {
		System.out.println("UserCtrl searchForm");

		EmployeeVO user = (EmployeeVO) session.getAttribute("login");
		useremp.setEmpid(user.getEmpid());
		useremp.setDeptid(user.getDeptid());

		List<EmployeeDeptDivVO> list = service.list(useremp);
		List<EmpIdVO> favid = service.selectFavId(user.getEmpid());

		for(int i=0 ; i<list.size() ; i++) {
			System.out.println(list.get(i));
		}
		model.addAttribute("lists", list);
		model.addAttribute("myfav", favid);
		return "searchView";
	}

	/*
	 * @RequestMapping(value = "/addfavorite.inc")
	 * 
	 * @ResponseBody public String addFavorite(@RequestParam(value =
	 * "valueArrTest[]") List<String> valueArr, HttpSession session, Model
	 * model) { System.out.println("UserCtrl addFavorite"); EmployeeVO user =
	 * (EmployeeVO) session.getAttribute("login");
	 * 
	 * 
	 * // check된 사람의 empid받아 왔음 for (int i = 0; i < valueArr.size(); i++) {
	 * System.out.println(valueArr.get(i)); }
	 * 
	 * // (empid,empidfav) favorite table DB insert ArrayList<FavoriteVO> list =
	 * new ArrayList<FavoriteVO>(); for (int i = 0; i < valueArr.size(); i++) {
	 * int flag = service.addFav(user.getEmpid(), valueArr.get(i));
	 * System.out.println("insert flag >>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + flag);
	 * }
	 * 
	 * /////////// 서희꺼 가져오기 - favorite list 카드뷰 보여주기////////
	 * 
	 * return "true" ;
	 * 
	 * }
	 */

	@RequestMapping(value = "/addfavorite.inc")
	@ResponseBody
	public List<EmpIdVO> addFavorite(@RequestParam(value = "checkid") String chkid, HttpSession session, Model model) {
		System.out.println("UserCtrl addFavorite");

		EmployeeVO user = (EmployeeVO) session.getAttribute("login");

		int flag = service.addFav(user.getEmpid(), chkid);
		System.out.println("insert flag >>>>>>>>>>>>>>>>>>>>>>>>>>>>>> for i " + flag);

		List<EmpIdVO> favid = service.selectFavId(user.getEmpid());

		return favid;
	}

	@RequestMapping(value = "/deletefavorite.inc")
	@ResponseBody
	public List<EmpIdVO> deleteFavorite(@RequestParam(value = "checkid") String chkid, HttpSession session,
			Model model) {
		System.out.println("UserCtrl deleteFavorite");

		EmployeeVO user = (EmployeeVO) session.getAttribute("login");

		int flag = service.deleteFav(user.getEmpid(), chkid);
		System.out.println("delete flag >>>>>>>>>>>>>>>>>>>>>>>>>>>>>> for i,chk " + flag + chkid);

		List<EmpIdVO> favid = service.selectFavId(user.getEmpid());

		for (int i = 0; i < favid.size(); i++) {
			System.out.println("favid >>> size, get i >> " + favid.size() + favid.get(i));
		}

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

	@RequestMapping(value = "/searchdept.inc")
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
	}

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

		model.addAttribute("works", work);
		model.addAttribute("lists", list);
		return list;
	}

	///////////////////////////////////////////////// update form
	@RequestMapping(value = "/updateview.inc")
	public String update(EmployeeWorkDeptVO useremp, HttpSession session, Model model, Model model2) {
		System.out.println("UserCtrl update");

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
			System.out.println("amloc, pmloc : " + mylist.getAmloc() + mylist.getPmloc());
		}
		
		List<DeptDivisionVO> divList = service.selectdiv(); 	
		List<DeptDivisionVO> list = service.selectdeptdiv();
		
		model.addAttribute("divlist", divList);
		model2.addAttribute("lists", list);
		model.addAttribute("myinfo", mylist);

		return "update";
	}

	///////////////////////////////////////////////////// my info db update///////////////////////////////////////////
	@RequestMapping(value = "/modify.inc", method = RequestMethod.POST ) 
	public String modify(EmployeeDeptDivVO member, HttpServletRequest request, Model model) throws Exception {
		System.out.println("userctrl modify");
		System.out.println("deptid================"+member.getEmpimg());
		
		MultipartFile f = member.getFile();

		System.out.println(">>>>>>>>>>>>.... file name : "+f.getOriginalFilename() );
		
		if(f.getOriginalFilename() != null) {
			member.setEmpimg(f.getOriginalFilename());
		}
			
		if( f != null ) {
			String path = request.getSession().getServletContext().getRealPath("/resources");
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> path : "+path); 
				 
			byte [] data = f.getBytes() ; 
				
			FileOutputStream out = new FileOutputStream(path+"/"+f.getOriginalFilename()) ;
			out.write(data) ; 
		}
		
		service.update(member);
		System.out.println("update complete!");
		service.updateWork(member);
				
	
		EmployeeVO user = new EmployeeVO(member.getEmpimg(), member.getEmpid(),member.getEmppwd(), member.getEmpname(),  
					member.getEmpphone(), member.getEmpmail(),member.getEmploc(), member.getDeptid());

		user = service.loginEmp(user);

		System.out.println("empimg================"+user.getEmpimg());
		System.out.println(user);
		model.addAttribute("login", user);
		
		return "redirect:/user.inc";
	}

	/////////////////// modal content update --sukjin
	@RequestMapping("/update.inc")
	public String update(EmployeeWorkDeptVO myinfo) {

		System.out.println("update Ctrl 실행");
		String workDateForm = myinfo.getWorkdate();
		String year = workDateForm.substring(0, 4);
		String month = workDateForm.substring(6, 8);
		String day = workDateForm.substring(10, 12);
		String workDateFormChange = year + "-" + month + "-" + day;

		myinfo.setWorkdate(workDateFormChange);

		service.update(myinfo);

		return "redirect:/user.inc";
	}

	/////////////////// modal content update --sukjin
	@RequestMapping("/updateCal.inc")
	public String updateCal(EmployeeWorkDeptVO myinfo) {
		System.out.println("update Ctrl 실행");
		String workDateForm = myinfo.getWorkdate();
		String year = workDateForm.substring(0, 4);
		String month = workDateForm.substring(6, 8);
		String day = workDateForm.substring(10, 12);
		String workDateFormChange = year + "-" + month + "-" + day;
		myinfo.setWorkdate(workDateFormChange);
		service.update(myinfo);
		return "redirect:/calMove.inc";
	}

	/////////////////////////// calendar.jsp modal
	@RequestMapping("/calModal.inc")
	@ResponseBody
	public EmployeeWorkDeptVO calModal(EmployeeWorkDeptVO myinfo, HttpServletRequest request) {

		System.out.println("calModal Ctrl 실행");

		String clickday = request.getParameter("clickday");
		String empid = request.getParameter("empid");

		myinfo.setCurrdate(clickday);
		myinfo.setEmpid(empid);

		EmployeeWorkDeptVO list = service.selectCalModal(myinfo);

		return list;
	}

	@RequestMapping(value = "/dashboard.inc")
	public String dashboard(EmployeeWorkDeptVO useremp, HttpSession session, Model model) {
		System.out.println("UserCtrl dashboard");

		// login session (empid)
		EmployeeVO user = (EmployeeVO) session.getAttribute("login");
		EmployeeWorkDeptVO mylist = new EmployeeWorkDeptVO();
		mylist.setEmpid(user.getEmpid());

		// Date setting
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String currdate = sdf1.format(cal.getTime());
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<currdate>>>>>>>>>>>>>>>>>>>>>" + currdate);
		mylist.setCurrdate(currdate);

		// work table validation
		int work = service.selectwork(mylist);
		System.out.println("select work table count: " + work);

		// 1. table empty,
		if (work == 0) {
			mylist = service.mylist1(mylist);
			mylist.setAmloc(mylist.getEmploc());
			mylist.setPmloc(mylist.getEmploc());
		}

		// 2. work table exist
		else {
			mylist = service.mylist2(mylist);
			System.out.println("amloc, pmloc : " + mylist.getAmloc() + mylist.getPmloc());
		}

		model.addAttribute("myinfo", mylist);

		return "redirect:/favoritedash.inc";
	}

	@RequestMapping(value = "/favoritedash.inc")
	public String favoritedash(ArrayList<EmployeeFavWorkDeptVO> favemp, HttpSession session, Model model) {
		System.out.println("UserCtrl favoritedash");

		// login session (empid)
		EmployeeVO user = (EmployeeVO) session.getAttribute("login");
		EmployeeWorkDeptVO list = new EmployeeWorkDeptVO();
		list.setEmpid(user.getEmpid());
		list.setDeptid(user.getDeptid());
		System.out.println(list.getDeptid()+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>favoritedash");

		// Date setting
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String currdate = sdf1.format(cal.getTime());
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<currdate>>>>>>>>>>>>>>>>>>>>>" + currdate);
		list.setCurrdate(currdate);

		List<EmployeeWorkDeptVO> favlist = new ArrayList<EmployeeWorkDeptVO>();
		favlist = service.selectDashEmp(list);

		for (int i = 0; i < favlist.size(); i++) {
			System.out.println(favlist.get(i));
		}

		model.addAttribute("myfav", favlist);

		/// 현재 시간 가져오기///
		Date date = new Date();
		model.addAttribute("date", date);
		return "dashboard";
	}

	@RequestMapping(value = "/calDayDash.inc")
	public String favoritedash2(@RequestParam(value = "currDate") String currdate, EmployeeWorkDeptVO useremp,
			ArrayList<EmployeeFavWorkDeptVO> favemp, HttpSession session, Model model) {
		System.out.println("===================================페이지 변경=====================================");
		System.out.println("UserCtrl favoritedash2 calDayDash.inc");
		System.out.println(currdate);

		EmployeeVO user = (EmployeeVO) session.getAttribute("login");
		System.out.println(user.getEmpid());

		//////////////////////////////////////////////////////////////////////////////// mylist
		//////////////////////////////////////////////////////////////////////////////// reload
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

		///////////////////////////////////////////////////////////////////////////////// favorite
		///////////////////////////////////////////////////////////////////////////////// reload
		EmployeeWorkDeptVO list = new EmployeeWorkDeptVO();
		list.setEmpid(user.getEmpid());
		list.setDeptid(user.getDeptid());
		list.setCurrdate(currdate);

		List<EmployeeWorkDeptVO> favlist = new ArrayList<EmployeeWorkDeptVO>();
		favlist = service.selectDashEmp(list);

		for (int i = 0; i < favlist.size(); i++) {
			System.out.println(favlist.get(i));
		}

		/// 현재 시간 가져오기///
		Date date = new Date();
		model.addAttribute("date", date);

		model.addAttribute("myfav", favlist);
		model.addAttribute("myinfo", mylist);
		model.addAttribute("day", currdate);

		return "dashboardOtherDay";
	}

	////////////////////////////// NFC Ctrl

	@RequestMapping(value = "/nfc.inc")
	@ResponseBody
	public String nfc(@RequestParam(value = "loc") String loc, @RequestParam(value = "empid") String empid) {
		System.out.println("UserCtrl nfc");

		System.out.println("!!!!!!!!!!!!!!!!! NFC 태그  !!!!!!!!!!!!!!!!!");

		System.out.println("loc : " + loc);
		System.out.println("empid : " + empid);

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
