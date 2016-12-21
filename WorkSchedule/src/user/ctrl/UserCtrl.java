package user.ctrl;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import model.domain.vo.LocationVO;
import model.domain.vo.NfcVO;

@Controller
@SessionAttributes({ "login", "myinfo", "locList", "lists", "myfavid", "myfav", "mydeptdev" })

/*
 * model 종류 정리
 * 
 * 1. "login" - EmployeeVO       			// login.inc, 로그인시 데이터 심음 
 * 2. "myinfo" - EmployeeWorkDeptVO    		// user.inc, 내 정보와 work 정보 심음
 * 3. "myfav" - List<EmployeeFavWorkDeptVO>	// user.inc, 내 정보와 favorite의 work 정보 심음
 * 4. "locList" - locationVO     			// user.inc, 근무지 종류 가져옴
 * 5. "day" - String       					// calDay.inc, requestparam-day 
 * 6. "view" - String       				// calDay.inc, requestparam-view
 * 7. "lists" - List<EmployeeDeptDivVO>   	// searchview.inc, favorite 추가 목록에 뿌릴 직원 list 가져옴
 * 8. "myfavid" - List<EmpIdVO>     		// searchview.inc, favorite에 있는지 없는지 체크할 id 가져오기 
 * 9. "works" - List<EmployeeWorkDeptVO>    // calendar.inc, calendar에 뿌릴 내 정보
 * 10. "divlist" - List<DeptDivisionVO>   	// updateview.inc, divlist 조회 
 * 11. "deptlist" - DeptDivisionVO     		// updateview.inc, deptlist 조회
 * 12. "mydeptdiv" - EmployeeDeptDivVO    	// updateview.inc, join/update 페이지 정보 가져옴 (divname 포함)
 * 
 * */


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
	
		if (user == null) { // login info 
			model.addAttribute("chk", idchk);
			return "intro";
		}
		
		else { // login 
			// dashboard - hr
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
		System.out.println("UserCtrl user.inc");

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
		
		//mylist = service.mylist2(mylist);
		mylist.setCurrdate(currdate);
		System.out.println(mylist.toString());

		List<EmployeeFavWorkDeptVO> favlist = service.selectempfav(mylist);
		List<LocationVO> locList = service.selectloc();

		model.addAttribute("myinfo", mylist); ////////////////////////////////////////// * EmployeeWorkDeptVO *		
		model.addAttribute("myfav", favlist); ////////////////////////////////////////// * List<EmployeeFavWorkDeptVO> *
		model.addAttribute("locList", locList); //////////////////////////////////////// * LocationVO *
			
		return "redirect:/view.jsp";	
		//return "redirect:/favorite.inc";
	}

	
	///////////////////////////////////////////////////////////////////////////////////////// viewOtherDay.jsp
	

	@RequestMapping(value = "/calDay.inc")
	public String userlist2(@RequestParam(value = "currDate") String currdate,
			@RequestParam(value = "view") String view, HttpSession session, Model model) {
		System.out.println("================================== PAGE CHANGE =====================================");
		System.out.println("UserCtrl userlist2 calDay.inc / " + currdate);

		// mylist reload
		EmployeeVO user = (EmployeeVO) session.getAttribute("login");
		EmployeeWorkDeptVO useremp = new EmployeeWorkDeptVO();
		useremp.setEmpid(user.getEmpid());
		useremp.setCurrdate(currdate);

		//useremp = service.mylist2(useremp);
		
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
		
		//EmployeeWorkDeptVO list = new EmployeeWorkDeptVO();
		//useremp.setEmpid(user.getEmpid());
		useremp.setCurrdate(currdate);

		List<EmployeeFavWorkDeptVO> favlist = new ArrayList<EmployeeFavWorkDeptVO>();
		favlist = service.selectempfav(useremp);

		model.addAttribute("myinfo", useremp); // EmployeeWorkDeptVO 
		model.addAttribute("myfav", favlist); // List<EmployeeFavWorkDeptVO> - fav 
		model.addAttribute("day", currdate); /////////////////////////////////////////////////////// * String currdate *
		model.addAttribute("view", view); ////////////////////////////////////////////////////////// * String view * 
		
		return "viewOtherDay";
	}

	

	//////////////////////////////////////////////////////////////////////////// searchview.jsp, favorite add/delete

	// show searchForm
	@RequestMapping(value = "/searchview.inc")
	public String searchForm(EmployeeDeptDivVO useremp, HttpSession session, Model model) {
		System.out.println("UserCtrl searchview.inc");

		EmployeeVO user = (EmployeeVO) session.getAttribute("login");
		useremp.setEmpid(user.getEmpid());
		useremp.setDeptid(user.getDeptid());

		List<EmployeeDeptDivVO> list = service.list(useremp);
		List<EmpIdVO> favid = service.selectFavId(user.getEmpid());

		model.addAttribute("lists", list); ////////////////////////////////// *List<EmployeeDeptDivVO>*
		model.addAttribute("myfavid", favid); /////////////////////////////// *List<EmpIdVO>*
		return "redirect:/searchView.jsp";
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
		
		model.addAttribute("myfavid", favid); // List<EmpIdVO> 
		model.addAttribute("lists", list); // List<EmployeeDeptDivVO> 

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
		System.out.println("Userctrl calMove.inc");
		return "redirect:/calendar.jsp";
	}

	// requestMapping to calendar.jsp
	@RequestMapping("/calendar.inc")
	@ResponseBody
	public List<CalendarNoteVO> main(Model model, HttpSession session) {
		System.out.println("calendar");
		
		
		EmployeeVO user = (EmployeeVO) session.getAttribute("login");
		EmployeeWorkDeptVO cal = new EmployeeWorkDeptVO();
		cal.setEmpid(user.getEmpid());

		List<EmployeeWorkDeptVO> work = service.selectMyWork(cal);
		List<CalendarNoteVO> list = new ArrayList<CalendarNoteVO>();

		for (int i = 0; i < work.size(); i++) {
			if (work.get(i).getEmploc().equals(work.get(i).getAmloc())
					&& work.get(i).getEmploc().equals(work.get(i).getPmloc())) {
				// // default lo와 ampm loc가 같을 때는 list에 저장 안함
			} else {
				String date = work.get(i).getWorkdate().substring(0, 10);
				String note = work.get(i).getAmloc()+ "/" +work.get(i).getPmloc();

				/*System.out.println("default : " + work.get(i).getEmploc() + " / am : " + work.get(i).getAmloc()
						+ " / pm : " + work.get(i).getPmloc());*/

				list.add(new CalendarNoteVO(date, note));
			}
		}

		model.addAttribute("works", work); ///////////////////////////////////////// * List<EmployeeWorkDeptVO> *
		
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
		List<DeptDivisionVO> deptlist = service.selecteddept(mylist);

		/*List<String> deptlist = new ArrayList<String>();
		deptlist = service.selectboxDept(divname);*/////////////////////////////////////////////////// 고치기 !!!

		model.addAttribute("mydeptdiv", mylist); //////////////////////////////////////////// * EmployeeDeptDivVO *
		model.addAttribute("divlist", divList); ///////////////////////////////////////////// * DeptDivisionVO *
		model.addAttribute("deptlist", deptlist); /////////////////////////////////////////// * List<DeptDivisionVO> *


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

		// filename set
		if ( f.getOriginalFilename() != null && f.getOriginalFilename() != "") {
			member.setEmpimg(f.getOriginalFilename());
			String path = request.getSession().getServletContext().getRealPath("/resources");
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> path : " + path);

			byte[] data = f.getBytes();

			FileOutputStream out = new FileOutputStream(path + "/" + member.getEmpimg());
			out.write(data);
		}
		
		service.update(member);
		service.updateWork(member);				

		EmployeeVO user = new EmployeeVO(member.getEmpimg(), member.getEmpid(),member.getEmppwd(), member.getEmpname(),  
					member.getEmpphone(), member.getEmpmail(),member.getEmploc(), member.getDeptid());
		user = service.loginEmp(user);

		model.addAttribute("login", user); // login 
		
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
		mylist.setDeptid(user.getDeptid());
		
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
		
		//mylist = service.mylist2(mylist);
		mylist.setCurrdate(currdate);
		System.out.println(mylist.toString());

		List<EmployeeWorkDeptVO> favlist = new ArrayList<EmployeeWorkDeptVO>();
		favlist = service.selectDashEmp(mylist);

	
		Date date = new Date();
		
		model.addAttribute("myinfo", mylist); ////////////////////////////////////////// * EmployeeWorkDeptVO *
		model.addAttribute("date", date);
		model.addAttribute("myfav", favlist);/////////// * fav work - EmployeeFavWorkDeptVO *
		
		
		
		//////////////////////// Work Table 
		
		List<String> emplist = new ArrayList<String>();
		emplist = service.selectEmpList();
		
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, 31);
		int month  = calendar.get(Calendar.MONTH) + 1 ; 
		int day  = calendar.get(Calendar.DAY_OF_MONTH) ;
		String mm = "";
		String dd = "";
		if(month <=9 ) {
			mm += "0"+month ; 
		}else {
			mm = String.valueOf(month) ; 
		}
		
		if(day < 10 ) {
			dd += "0"+day ; 
		}else {
			dd = String.valueOf(day) ; 
		}
		
		String workdate = String.valueOf(calendar.get(Calendar.YEAR)) + mm + dd;
		mylist.setCurrdate(workdate);
		
		for(int i=0;i<emplist.size();i++){

			mylist.setEmpid(emplist.get(i)); 
			int cnt = service.selectwork(mylist);
			
			if(cnt == 0){
				try {
					Thread.sleep(200);
					service.insertWork(mylist);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		//////////////////////// Work Table 
		
		return "dashboard";
	}

	
	////////////////////////////////////////////////////////////////////////////////////////////// dashboardOtherDay.jsp
	
	@RequestMapping(value = "/calDayDash.inc")
	public String favoritedash2(@RequestParam(value = "currDate") String currdate, HttpSession session, Model model) {
		System.out.println("=============================== page change ===================================");
		System.out.println("UserCtrl favoritedash2 calDayDash.inc");
		System.out.println(currdate);

		// login session (empid)
		EmployeeVO user = (EmployeeVO) session.getAttribute("login");
		EmployeeWorkDeptVO mylist = new EmployeeWorkDeptVO();
		mylist.setEmpid(user.getEmpid());
		mylist.setDeptid(user.getDeptid());
		mylist.setCurrdate(currdate);

		List<EmployeeWorkDeptVO> favlist = new ArrayList<EmployeeWorkDeptVO>();
		favlist = service.selectDashEmp(mylist);
		
		
		Date date = new Date();

		model.addAttribute("day", currdate); 
		model.addAttribute("date", date);
		model.addAttribute("myfav", favlist);

		
		Calendar cal2 = Calendar.getInstance();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String currdate2 = sdf1.format(cal2.getTime());
		
		System.out.println(currdate2+"?????????????????????????????????????");
		
		if(currdate.equals(currdate2)){
			return "redirect:dashboard.inc";
		}else{
			return "dashboardOtherDay";
		}
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////////// NFC Ctrl

	@RequestMapping(value = "/nfc.inc")
	@ResponseBody
	public String nfc(@RequestParam(value = "loc") String loc, @RequestParam(value = "empid") String empid) {
		System.out.println("UserCtrl nfc");
		System.out.println("NFC tag !!!!");
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

	@RequestMapping(value = "/delete.inc")
	 public String delete(EmployeeDeptDivVO member) {
	  System.out.println("=================================== delete emp ====================================");
	  System.out.println("UserCtrl delete.inc");
	  
	  service.deletework(member);
	  service.deleteemp(member);
	  
	  return "redirect:/main.inc";
	 }


}

